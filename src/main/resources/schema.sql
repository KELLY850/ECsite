-- dialect: PostgreSQL
--テーブルが存在したら削除する
DROP TABLE IF EXISTS product_images;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS cart_items;
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS credit_cards;
DROP TABLE IF EXISTS product_categories;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS admin_users;

--テーブルの作成ユーザー
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
--商品テーブル
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
--商品画像
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
--レビュー
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
--カート
 CREATE TABLE carts(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_carts_user
        FOREIGN KEY(user_id)
        REFERENCES users(id)
 );
--カート商品
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
--注文
 CREATE TABLE orders(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    address_id BIGINT NOT NULL,
    payment_id BIGINT NOT NULL,
    status INTEGER DEFAULT 4,
    total_amount INTEGER NOT NULL,
    created_at TIMESTAMP,
    deleted_at TIMESTAMP,
    delete_flg INTEGER DEFAULT 0
--    住所とユーザーID,支払いID入れて
 );

-- 注文商品詳細
CREATE TABLE order_items(
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    price INTEGER NOT NULL,
    quantity INTEGER NOT NULL,

    CONSTRAINT fk_order_items_order FOREIGN KEY(order_id) REFERENCES orders(id),
    CONSTRAINT fk_order_items_product FOREIGN KEY(product_id) REFERENCES products(id)
);

--住所
 CREATE TABLE addresses(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    postal_code VARCHAR(100) NOT NULL,
    prefecture VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    street VARCHAR(100) NOT NULL,
    building VARCHAR(100) DEFAULT NULL,
    is_default INTEGER DEFAULT 0,

    CONSTRAINT fk_address_user FOREIGN KEY(user_id) REFERENCES users(id)
 );

--支払い方法
 CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    payment_method INTEGER NOT NULL,
    status INTEGER NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    delete_flg INTEGER DEFAULT 0,

    CONSTRAINT fk_payment_user FOREIGN KEY(user_id) REFERENCES users(id)
 );

-- クレカ情報
 CREATE TABLE credit_cards(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    card_brand VARCHAR(30) NOT NULL,
    last4 VARCHAR(4) NOT NULL,
    token VARCHAR(1000) NOT NULL,
    expiration VARCHAR(10) NOT NULL,

    CONSTRAINT fk_credit_cards_user FOREIGN KEY(user_id) REFERENCES users(id)
 );

-- カテゴリー
 CREATE TABLE categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    delete_flg INTEGER DEFAULT 0
 );
-- 商品とカテゴリ
 CREATE TABLE product_categories(
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,

    CONSTRAINT fk_product_categories_product FOREIGN KEY(product_id) REFERENCES products(id),
    CONSTRAINT fk_product_categories_categories FOREIGN KEY(category_id) REFERENCES categories(id)
 );

--管理者ユーザー
CREATE TABLE admin_users(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(20) DEFAULT 'USER',
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    delete_flg INTEGER DEFAULT 0
);