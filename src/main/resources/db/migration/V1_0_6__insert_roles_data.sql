INSERT INTO auth.roles (role_name)
VALUES ('USER'), ('ADMIN')
ON CONFLICT (role_name) DO NOTHING ;