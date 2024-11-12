package com.example.demo.websocket.dto;

/**
 * packageName    : com.example.demo.websocket.dto
 * fileName       : ChatRoomCreateResponse
 * author         : JAEIK
 * date           : 11/12/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 11/12/24       JAEIK       최초 생성
 */
public record ChatRoomResponse(
        String roomId,
        String roomName
) {
}
