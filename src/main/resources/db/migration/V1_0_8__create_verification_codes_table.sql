CREATE TABLE IF NOT EXISTS auth.verification_codes
(
    id          BIGSERIAL       PRIMARY KEY,
    email       VARCHAR(255),
    code        VARCHAR(255),
    expires_time TIMESTAMP

);
COMMENT ON COLUMN auth.verification_codes.id is '일렬번호';
COMMENT ON COLUMN auth.verification_codes.email is '이메일';
COMMENT ON COLUMN auth.verification_codes.code is '인증코드';
COMMENT ON COLUMN auth.verification_codes.expires_time is '만료시간';
