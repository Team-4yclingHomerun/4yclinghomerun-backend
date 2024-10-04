CREATE TABLE IF NOT EXISTS auth.member
(
    id          UUID            PRIMARY KEY     DEFAULT uuid_generate_v4(),
    username    VARCHAR(255),
    password    VARCHAR(255),
    nickname    VARCHAR(255),
    status      VARCHAR(255),
    created_at  TIMESTAMP       DEFAULT NOW(),
    updated_at  TIMESTAMP,

    CONSTRAINT uq_member_username UNIQUE (username),
    CONSTRAINT uq_member_nickname UNIQUE (nickname)
);

COMMENT ON COLUMN auth.member.id is '멤버 일렬번호';
COMMENT ON COLUMN auth.member.username is '사용자이름';
COMMENT ON COLUMN auth.member.password is '패스워드';
COMMENT ON COLUMN auth.member.nickname is '닉네임';
COMMENT ON COLUMN auth.member.status is '상태';
COMMENT ON COLUMN auth.member.created_at is '등록일시';
COMMENT ON COLUMN auth.member.updated_at is '최근수정일시';
