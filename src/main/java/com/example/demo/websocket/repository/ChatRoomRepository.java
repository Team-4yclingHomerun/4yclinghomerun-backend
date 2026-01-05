package com.example.demo.websocket.repository;

import com.example.demo.websocket.dto.ChatRoom;
import com.example.demo.websocket.dto.ChatRoomCreateRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * packageName    : com.example.demo.websocket
 * fileName       : ChatRommRepository
 * author         : JAEIK
 * date           : 11/8/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 11/8/24       JAEIK       최초 생성
 */
public class ChatRoomRepository {

//    private Map<String, ChatRoom> chatRoomMap;
//
//    @PostConstruct
//    private void init() {
//        chatRoomMap = new LinkedHashMap<>();
//    }
//
//    public ChatRoom findRoomById(String roomId) {
//        return chatRoomMap.get(roomId);
//    }
//
//    public List<ChatRoom> findAllRooms() {
//        List<ChatRoom> chatRooms = new ArrayList<>(chatRoomMap.values());
//        Collections.reverse(chatRooms);
//        return chatRooms;
//    }
//
//    public ChatRoom createChatRoom(ChatRoomCreateRequest roomName) {
//        ChatRoom chatRoom = ChatRoom.create(roomName);
//        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
//        return chatRoom;
//    }
}
