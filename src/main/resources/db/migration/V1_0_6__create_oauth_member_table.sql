CREATE TABLE IF NOT EXISTS auth.oauth_member
(
    id                UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nickname          VARCHAR(255),
    email             VARCHAR(255),
    profile_image_url VARCHAR(255),
    oauth_server_id   VARCHAR(255),
    oauth_server      VARCHAR(255),
    status            VARCHAR(255),
    created_at        TIMESTAMP        DEFAULT NOW(),
    updated_at        TIMESTAMP,
    CONSTRAINT un_oauth_serverId_oauth_server UNIQUE (oauth_server_id, oauth_server)
);