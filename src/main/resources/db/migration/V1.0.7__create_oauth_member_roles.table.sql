CREATE TABLE IF NOT EXISTS auth.oauth_member_roles
(
    oauth_id UUID,
    role_id BIGINT,
    PRIMARY KEY (oauth_id, role_id),
    FOREIGN KEY (oauth_id) REFERENCES auth.oauth_member ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES auth.roles ON DELETE CASCADE
);