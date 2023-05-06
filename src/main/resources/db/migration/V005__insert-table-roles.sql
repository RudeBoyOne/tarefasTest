INSERT INTO usuarios (nome, email, senha) VALUES ('Juriscreudo', 'admin@admin.com',
'$2a$10$cLBsr5JgT7EjzknGOK8RO.rt5YbwjyUxlxbzA/0jxT.48uLzefw8.');

INSERT INTO roles (nome) values  ('ADMIN');
INSERT INTO roles (nome) values  ('USER');

INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (1, 1);