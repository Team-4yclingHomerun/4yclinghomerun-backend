CREATE TABLE IF NOT EXISTS profile.profile
(
    id              BIGSERIAL        PRIMARY KEY,
    nickname        varchar(255),
    phone           VARCHAR(255),
    status          VARCHAR(255),
    created_at      TIMESTAMP,
    updated_at      TIMESTAMP,

    CONSTRAINT uq_profile_nickname UNIQUE (nickname),
    CONSTRAINT uq_profile_phone UNIQUE (phone)
);

COMMENT ON COLUMN profile.profile.id is '프로필일렬번호';
COMMENT ON COLUMN profile.profile.nickname is '닉네임';
COMMENT ON COLUMN profile.profile.phone is '핸드폰번호 (010-1234-1234)';
COMMENT ON COLUMN profile.profile.status is '상태';
COMMENT ON COLUMN profile.profile.created_at is '등록일시';
COMMENT ON COLUMN profile.profile.updated_at is '최근수정일시';