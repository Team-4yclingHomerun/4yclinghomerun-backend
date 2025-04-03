package com.example.demo.websocket;

import com.example.demo.jwt.JwtParser;
import com.example.demo.websocket.dto.*;
import com.example.demo.websocket.entity.ChatMessage;
import com.example.demo.websocket.repository.ChatMessageRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * packageName    : com.example.demo.websocket
 * fileName       : ChatService
 * author         : JAEIK
 * date           : 10/29/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/29/24        JAEIK       최초 생성
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final JwtParser jwtParser;

    public Slice<ChatMessageResponse> findMessages(HttpServletRequest request, Pageable pageable) {

        String token = jwtParser.extractToken(request);
        UUID userId;

        if (token != null && jwtParser.validateToken(token)) {
            userId = jwtParser.getAuthenticateMember(token).id();
        } else {
            userId = null;
        }

        Slice<ChatMessage> chatMessageSlice = chatMessageRepository.findAllByOrderByCreateAtDesc(pageable);
        List<ChatMessageResponse> chatMessageResponses =
                chatMessageSlice
                        .stream()
                        .map(chatMessage -> new ChatMessageResponse(
                               chatMessage.getType(),
                                chatMessage.getSender(),
                                chatMessage.getMessage(),
                                chatMessage.getCreateAt(),
                                userId != null &&  userId.equals(chatMessage.getSenderId())
                        ))
                        .toList();


        return new SliceImpl<>(chatMessageResponses, pageable, chatMessageSlice.hasNext());
    }

//    public ChatRoomResponse createRoom(ChatRoomCreateRequest roomName) {
//          ChatRoom chatRoom = chatRoomRepository.createChatRoom(roomName);
//          return new ChatRoomResponse(chatRoom.getRoomId(), chatRoom.getRoomName());
//    }
//
//    public ChatRoomResponse searchRoomById(String roomId) {
//        ChatRoom chatRoom = chatRoomRepository.findRoomById(roomId);
//        return new ChatRoomResponse(chatRoom.getRoomId(), chatRoom.getRoomName());
//    }
//
//    public List<ChatRoomResponse> searchAllRoom() {
//        List<ChatRoom> chatRoom = chatRoomRepository.findAllRooms();
//        return chatRoom.stream()
//                .map(room -> new ChatRoomResponse(room.getRoomId(),room.getRoomName()))
//                .collect(Collectors.toList());
//    }
}
