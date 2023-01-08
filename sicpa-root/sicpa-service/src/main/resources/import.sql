INSERT INTO enterprises (created_by, created_date, created_host, status, address, "name", phone) VALUES('admin', '2023-01-06 00:00:00', '127.0.0.1', true, 'Avenida 12 de Octubre Quito', 'Sicpa', '022979605');
INSERT INTO enterprises (created_by, created_date, created_host, status, address, "name", phone) VALUES('admin', '2023-01-06 00:00:00', '127.0.0.1', true, 'Avenida Amazónas', 'Banco Pichincha', '022979600');

INSERT INTO departments (created_by, created_date, created_host, status, description, "name", phone, id_enterprise) VALUES('admin', '2023-01-06 00:00:00', '127.0.0.1', true, 'Departamento de Tecnología', 'Tecnología', '+593 22 979600', 1);
INSERT INTO departments (created_by, created_date, created_host, status, description, "name", phone, id_enterprise) VALUES('admin', '2023-01-06 00:00:00', '127.0.0.1', true, 'Departamento de Talento Humano', 'Talento Humano', '+593 22 979600', 1);

INSERT INTO employees (created_date, created_host, created_by, status, email, surname, "name", age, "position") VALUES ('2022-03-10 00:00:00', '127.0.0.1', 'admin', true, 'rene.travez@hotmail.com', 'Trávez', 'René', 33, 'Desarrollador');
INSERT INTO employees (created_date, created_host, created_by, status, email, surname, "name", age, "position") VALUES ('2022-03-10 00:00:00', '127.0.0.1', 'admin', true, 'anita.moya@hotmail.com', 'Moya', 'Anita', 30, 'Recepcionista');
INSERT INTO employees (created_date, created_host, created_by, status, email, surname, "name", age, "position") VALUES ('2022-03-10 00:00:00', '127.0.0.1', 'admin', true, 'felipe.travez@hotmail.com', 'Trávez', 'Felipe', 18, 'Asistente');

INSERT INTO users (created_date, created_host, created_by, status, "password", username, id_employee) VALUES ('2022-03-10 00:00:00', '127.0.0.1', 'admin', true, '$2a$10$RCvzvqq/TymJ2Gmpm5uzouTuyZK0WKkEn1nvL25rpfYz0kCWkDcAq', 'rene', 1);
INSERT INTO users (created_date, created_host, created_by, status, "password", username, id_employee) VALUES ('2022-03-10 00:00:00', '127.0.0.1', 'admin', true, '$2a$10$d6K2M94xCYJSIxa0VJNVTOEiE0jJIEvocP/xmp6dZ6N8.o9DJGnam', 'anita', 2);
INSERT INTO users (created_date, created_host, created_by, status, "password", username, id_employee) VALUES ('2022-03-10 00:00:00', '127.0.0.1', 'admin', true, '$2a$10$nncmFubyO.BK9YKxbxqVGuufZGu7R/RE9Twre0LCMoSA6QE/V6G5O', 'felipe', 3);

INSERT INTO roles (created_date, created_host, created_by, name, status) VALUES ('2022-03-10 00:00:00', '127.0.0.1', 'admin', 'ROLE_ADMIN', true);
INSERT INTO roles (created_date, created_host, created_by, name, status) VALUES ('2022-03-10 00:00:00', '127.0.0.1', 'admin', 'ROLE_OPERATOR', true);
INSERT INTO roles (created_date, created_host, created_by, name, status) VALUES ('2022-03-10 00:00:00', '127.0.0.1', 'admin', 'ROLE_USER', true);

INSERT INTO role_users (created_date, created_host, created_by, id_user, id_role, status) VALUES ('2022-03-10 00:00:00', '127.0.0.1', 'admin', 1, 1, true);
INSERT INTO role_users (created_date, created_host, created_by, id_user, id_role, status) VALUES ('2022-03-10 00:00:00', '127.0.0.1', 'admin', 2, 2, true);
INSERT INTO role_users (created_date, created_host, created_by, id_user, id_role, status) VALUES ('2022-03-10 00:00:00', '127.0.0.1', 'admin', 3, 3, true);
INSERT INTO role_users (created_date, created_host, created_by, id_user, id_role, status) VALUES ('2022-03-10 00:00:00', '127.0.0.1', 'admin', 1, 2, true);

INSERT INTO departments_employees (created_by, created_date, created_host, status, id_department, id_employee) VALUES('admin', '2023-01-06 00:00:00', '127.0.0.1', true, 1, 1);