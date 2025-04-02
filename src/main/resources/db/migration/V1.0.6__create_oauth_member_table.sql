CREATE TABLE IF NOT EXISTS auth.oauth_member
(
    id                UUID              PRIMARY KEY DEFAULT uuid_generate_v4(),
    nickname          VARCHAR(255),
    email             VARCHAR(255),
    profile_image_url VARCHAR(255),
    oauth_server_id   VARCHAR(255),
    oauth_server      VARCHAR(255),
    status            VARCHAR(255),
    created_at        TIMESTAMP         DEFAULT NOW(),
    updated_at        TIMESTAMP,
    CONSTRAINT uq_oauth_email UNIQUE (email),
    CONSTRAINT un_oauth_serverId_oauth_server UNIQUE (oauth_server_id, oauth_server)
);

COMMENT ON COLUMN auth.oauth_member.id is 'Oauth 멤버 일렬번호';
COMMENT ON COLUMN auth.oauth_member.nickname is 'Oauth 닉네임';
COMMENT ON COLUMN auth.oauth_member.email is 'Oauth 이메일';
COMMENT ON COLUMN auth.oauth_member.profile_image_url is 'Oauth 프로필 이미지';
COMMENT ON COLUMN auth.oauth_member.oauth_server_id is 'Oauth 서버아이디';
COMMENT ON COLUMN auth.oauth_member.oauth_server is 'Oauth 서버타입';
COMMENT ON COLUMN auth.oauth_member.status is '상태';
COMMENT ON COLUMN auth.oauth_member.created_at is '등록일시';
COMMENT ON COLUMN auth.oauth_member.updated_at is '최근수정일시';
