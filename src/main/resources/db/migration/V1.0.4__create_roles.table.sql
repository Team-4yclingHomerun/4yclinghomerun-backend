CREATE TABLE IF NOT EXISTS auth.roles (
    id         BIGSERIAL  PRIMARY KEY,
    role_name   VARCHAR(255) UNIQUE
);