CREATE TABLE IF NOT EXISTS chat.chat_message
(
 id         BIGSERIAL          PRIMARY KEY,
 type       VARCHAR(255),
 sender     VARCHAR(255),
 message    VARCHAR(255),
 create_at  TIMESTAMP
);

COMMENT ON COLUMN chat.chat_message.id is '일렬번호';
COMMENT ON COLUMN chat.chat_message.type is '메시지타입';
COMMENT ON COLUMN chat.chat_message.sender is '메시지 보낸 유저';
COMMENT ON COLUMN chat.chat_message.message is '메시지 내용';
COMMENT ON COLUMN chat.chat_message.create_at is '등록일시';
