INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_TELLER');
INSERT INTO users (username, first_name, last_name, password, enabled, role_id)
VALUES (
        'admin',
        'Admin',
        'Last Name',
        '$2a$10$qLawnDP..8UX.KvfKZ9jBe1MZt1KR6bob/rs.111v8FJcPGYQ/qE.',
        TRUE,
        1
    );