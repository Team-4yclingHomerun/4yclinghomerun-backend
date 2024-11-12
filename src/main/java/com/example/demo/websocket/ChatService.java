package com.example.demo.websocket;

import com.example.demo.websocket.dto.ChatRoom;
import com.example.demo.websocket.dto.ChatRoomCreateRequest;
import com.example.demo.websocket.dto.ChatRoomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomResponse createRoom(ChatRoomCreateRequest roomName) {
          ChatRoom chatRoom = chatRoomRepository.createChatRoom(roomName);
          return new ChatRoomResponse(chatRoom.getRoomId(), chatRoom.getRoomName());
    }

    public ChatRoomResponse searchRoomById(String roomId) {
        ChatRoom chatRoom = chatRoomRepository.findRoomById(roomId);
        return new ChatRoomResponse(chatRoom.getRoomId(), chatRoom.getRoomName());
    }

    public List<ChatRoomResponse> searchAllRoom() {
        List<ChatRoom> chatRoom = chatRoomRepository.findAllRoom();
        return chatRoom.stream()
                .map(room -> new ChatRoomResponse(room.getRoomId(),room.getRoomName()))
                .collect(Collectors.toList());
    }
}
