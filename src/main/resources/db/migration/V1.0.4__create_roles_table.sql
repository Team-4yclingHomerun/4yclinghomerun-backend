CREATE TABLE IF NOT EXISTS auth.roles
(
    id        BIGSERIAL PRIMARY KEY,
    role_name VARCHAR(255) UNIQUE
);

COMMENT ON COLUMN auth.roles.id is '권한 일렬번호';
COMMENT ON COLUMN auth.roles.role_name is '권한명';