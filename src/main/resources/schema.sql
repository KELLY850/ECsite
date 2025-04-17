-- dialect: PostgreSQL
--テーブルが存在したら削除する
DROP TABLE IF EXISTS users;

--テーブルの作成
CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    status INTEGER DEFAULT 1,
    tel VARCHAR(30) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    delete_flg INTEGER DEFAULT 0
);

CREATE TABLE products(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price INTEGER NOT NULL,
    stock INTEGER NOT NULL,
    description VARCHAR(1000),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    delete_flg INTEGER DEFAULT 0
);

CREATE TABLE product_images(
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    image_url VARCHAR(500) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    delete_flg INTEGER DEFAULT 0,
    CONSTRAINT fk_product_image
        FOREIGN KEY(product_id)
        REFERENCES products(id)
);

 CREATE TABLE reviews(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    rating INTEGER NOT NULL,
    comment VARCHAR(1000),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    delete_flg INTEGER DEFAULT 0,
    CONSTRAINT fk_review_user FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT fk_review_product FOREIGN KEY(product_id) REFERENCES products(id)
 );

 CREATE TABLE carts(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_carts_user
        FOREIGN KEY(user_id)
        REFERENCES users(id)
 );

 CREATE TABLE cart_items(
    id BIGSERIAL PRIMARY KEY,
    cart_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,

    CONSTRAINT fk_cart_items_cart FOREIGN KEY(cart_id) REFERENCES carts(id),
    CONSTRAINT fk_cart_items_product FOREIGN KEY(product_id) REFERENCES products(id)
 );

 CREATE TABLE orders(

 );