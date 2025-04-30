
--usersテーブルに一旦ダミーデータ
INSERT INTO users (name,email,password,status,tel,created_at)
VALUES
('admin','test@gmail.com','$2a$10$BDGzUbL.eSwgdh6QNVr6mOZDUIvB3kqOpVcn3JA2vFn.kMlS8HU0m',1,'000-0000-0000',CURRENT_TIMESTAMP);

INSERT INTO users (name,email,password,status,tel,created_at)
VALUES
('user','test2@gmail.com','$2a$10$BDGzUbL.eSwgdh6QNVr6mOZDUIvB3kqOpVcn3JA2vFn.kMlS8HU0m',1,'000-0000-0000',CURRENT_TIMESTAMP);

--admin_usersに一旦ダミーデータ
INSERT INTO admin_users(name,email,password,created_at,role)
VALUES
('admin','test@gmail.com','$2a$10$BDGzUbL.eSwgdh6QNVr6mOZDUIvB3kqOpVcn3JA2vFn.kMlS8HU0m',CURRENT_TIMESTAMP,'ADMIN');

INSERT INTO admin_users(name,email,password,created_at,role)
VALUES
('user','test2@gmail.com','$2a$10$BDGzUbL.eSwgdh6QNVr6mOZDUIvB3kqOpVcn3JA2vFn.kMlS8HU0m',CURRENT_TIMESTAMP,'USER');
