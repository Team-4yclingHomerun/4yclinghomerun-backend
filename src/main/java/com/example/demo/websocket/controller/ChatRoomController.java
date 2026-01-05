package com.example.demo.websocket.controller;

import com.example.demo.websocket.dto.ChatMessageResponse;
import com.example.demo.websocket.dto.ChatRoomUserSizeResponse;
import com.example.demo.websocket.service.ChatEventService;
import com.example.demo.websocket.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.example.demo.websocket
 * fileName       : ChatRoomController
 * author         : JAEIK
 * date           : 11/11/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 11/11/24       JAEIK       최초 생성
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "채팅", description = "채팅 API")
@RequestMapping("/api/chat")
public class ChatRoomController {

    private final ChatService chatService;
    private final ChatEventService chatEventService;

    @Operation(summary = "채팅방 메시지 조회")
    @GetMapping("/find-messages")
    public ResponseEntity<?> getChatMessages(HttpServletRequest request, Pageable pageable) {
        Slice<ChatMessageResponse> chatMessageResponseSlice = chatService.findMessages(request,pageable);
        return ResponseEntity.ok().body(chatMessageResponseSlice);
    }

    @Operation(summary = "채팅방 유저 수 조회")
    @GetMapping("/size")
    public ResponseEntity<ChatRoomUserSizeResponse> userTotal() {
         int total = chatEventService.totalUser();
         return ResponseEntity.ok(new ChatRoomUserSizeResponse(total));
    }

//    @Operation(summary = "모든 채팅방 조회")
//    @GetMapping("/rooms")
//    public ResponseEntity<?> getRooms() {
//        List<ChatRoomResponse> chatRoomResponseList = chatService.searchAllRoom();
//        return ResponseEntity.ok().body(chatRoomResponseList);
//    }
//
//    @Operation(summary = "채팅방 생성")
//    @PostMapping("/room")
//    public ResponseEntity<?> createNewRoom(ChatRoomCreateRequest roomName) {
//        ChatRoomResponse roomCreateResponse = chatService.createRoom(roomName);
//        return ResponseEntity.ok().body(roomCreateResponse);
//    }
//
//    @Operation(summary = "특정 채팅방 조회")
//    @GetMapping("/rooms/{roomId}")
//    public ResponseEntity<?> getRoomInfo(@PathVariable String roomId) {
//        ChatRoomResponse chatRoomResponse = chatService.searchRoomById(roomId);
//        return ResponseEntity.ok().body(chatRoomResponse);
//    }

//    @GetMapping("/room")
//    public String rooms(Model model) {
//        return "/chat/room";
//    }

    //    @GetMapping("/room/enter/{roomId}")
//    public String roomDetail(Model model, @PathVariable String roomId) {
//        model.addAttribute("roomId", roomId);
//        return "/chat/roomdetail";
//    }
}
