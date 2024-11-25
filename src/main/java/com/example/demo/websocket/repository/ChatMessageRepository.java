package com.example.demo.websocket.repository;

import com.example.demo.websocket.entity.ChatMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.example.demo.websocket.repository
 * fileName       : ChatMessageRepository
 * author         : JAEIK
 * date           : 11/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 11/19/24       JAEIK       최초 생성
 */
@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    Slice<ChatMessage> findAllByOrderByCreateAtDesc(Pageable pageable);

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
