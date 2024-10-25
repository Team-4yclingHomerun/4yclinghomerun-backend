CREATE TABLE IF NOT EXISTS auth.member_roles (
    member_id       UUID,
    role_id         BIGINT,
    PRIMARY KEY (member_id, role_id),
    FOREIGN KEY (member_id) REFERENCES auth.member(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id)   REFERENCES auth.roles(id) ON DELETE CASCADE
);