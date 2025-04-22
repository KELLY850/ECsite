
--usersテーブルに一旦ダミーデータ
INSERT INTO users (name,email,password,status,tel,created_at)
VALUES
('admin','test@gmail.com','12345',1,'000-0000-0000',CURRENT_TIMESTAMP);

INSERT INTO users (name,email,password,status,tel,created_at)
VALUES
('user','test2@gmail.com','12345',1,'000-0000-0000',CURRENT_TIMESTAMP);

--admin_usersに一旦ダミーデータ
INSERT INTO admin_users(name,email,password,created_at)
VALUES
('admin','test@gmail.com','12345',CURRENT_TIMESTAMP);

INSERT INTO admin_users(name,email,password,created_at)
VALUES
('user','test2@gmail.com','12345',CURRENT_TIMESTAMP);
