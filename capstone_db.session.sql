INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_TELLER');
INSERT INTO users (username, password, enabled)
VALUES (
        'admin',
        '$2a$10$qLawnDP..8UX.KvfKZ9jBe1MZt1KR6bob/rs.111v8FJcPGYQ/qE.',
        TRUE
    );
INSERT INTO users_roles (role_id, user_id) VALUES (1, 1)