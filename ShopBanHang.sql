CREATE DATABASE SHOP_QUAN_AO
GO
USE SHOP_QUAN_AO
GO-- Bảng USERS
CREATE TABLE [USERS]
(
    [user_id]      INT PRIMARY KEY IDENTITY ( 1, 1 ),
    [username]     nvarchar(100) UNIQUE NOT NULL,
    [email]        VARCHAR(255) UNIQUE  NOT NULL,
    [password]     VARCHAR(255)         NOT NULL,
    [full_name]    nvarchar(255),
    [address]      nvarchar(MAX),
    [phone_number] VARCHAR(15),
    [created_at]   datetime2,
    [updated_at]   datetime2
);

GO-- Bảng ROLES
CREATE TABLE [ROLES]
(
    [role_id]     INT PRIMARY KEY IDENTITY ( 1, 1 ),
    [role_name]   nvarchar(100) UNIQUE NOT NULL,
    [description] nvarchar(MAX),
    [created_at]  datetime2,
    [updated_at]  datetime2
);

GO-- Bảng USER_ROLES
CREATE TABLE [USER_ROLES]
(
    [user_id]    INT NOT NULL,
    [role_id]    INT NOT NULL,
    [created_at] datetime2,
    PRIMARY KEY ([user_id], [role_id])
);

GO-- Bảng TOKENS
CREATE TABLE [TOKENS]
(
    [token_id]   INT PRIMARY KEY IDENTITY ( 1, 1 ),
    [user_id]    INT                  NOT NULL,
    [token]      nvarchar(255) UNIQUE NOT NULL,
    [expires_at] datetime2            NOT NULL,
    [created_at] datetime2,
    [updated_at] datetime2
);

GO-- Bảng CATEGORIES
CREATE TABLE [CATEGORIES]
(
    [category_id]   INT PRIMARY KEY IDENTITY ( 1, 1 ),
    [category_name] nvarchar(255) NOT NULL,
    [created_at]    datetime2,
    [updated_at]    datetime2
);

GO-- Bảng PRODUCTS
CREATE TABLE [PRODUCTS]
(
    [product_id]   INT PRIMARY KEY IDENTITY ( 1, 1 ),
    [product_name] nvarchar(255) NOT NULL,
    [description]  nvarchar(MAX),
    [category_id]  INT           NOT NULL,
    [created_at]   datetime2,
    [updated_at]   datetime2
);

GO-- Bảng SIZE
CREATE TABLE [SIZES]
(
    [size_id]    INT PRIMARY KEY IDENTITY ( 1, 1 ),
    [size_name]  nvarchar(50) NOT NULL,
    [created_at] datetime2,
    [updated_at] datetime2
);

GO-- Bảng PRODUCT_DETAILS
CREATE TABLE [PRODUCT_DETAILS]
(
    [product_detail_id] INT PRIMARY KEY IDENTITY ( 1, 1 ),
    [product_id]        INT   NOT NULL,
    [size_id]           INT   NOT NULL,
    [price]             FLOAT NOT NULL,
    [stock_quantity]    INT   NOT NULL,
    [created_at]        datetime2,
    [updated_at]        datetime2,
    FOREIGN KEY ([product_id]) REFERENCES [PRODUCTS] ([product_id]) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY ([size_id]) REFERENCES [SIZES] ([size_id]) ON DELETE CASCADE ON UPDATE CASCADE
);

GO-- Bảng PRODUCT_IMAGES
CREATE TABLE [PRODUCT_IMAGES]
(
    [image_id]          INT PRIMARY KEY IDENTITY ( 1, 1 ),
    [product_detail_id] INT           NOT NULL,
    [image_url]         nvarchar(255) NOT NULL,
    [created_at]        datetime2,
    [updated_at]        datetime2,
    FOREIGN KEY ([product_detail_id]) REFERENCES [PRODUCT_DETAILS] ([product_detail_id]) ON DELETE CASCADE ON UPDATE CASCADE
);

GO-- Bảng ORDERS
CREATE TABLE [ORDERS]
(
    [order_id]         INT PRIMARY KEY IDENTITY ( 1, 1 ),
    [user_id]          INT         NOT NULL,
    [order_date]       datetime2   NOT NULL,
    [total_amount]     FLOAT       NOT NULL,
    [status]           VARCHAR(50) NOT NULL,
    [shipping_address] nvarchar(MAX),
    [created_at]       datetime2,
    [updated_at]       datetime2,
    FOREIGN KEY ([user_id]) REFERENCES [USERS] ([user_id]) ON DELETE CASCADE ON UPDATE CASCADE
);

GO-- Bảng ORDER_ITEMS
CREATE TABLE [ORDER_ITEMS]
(
    [order_item_id]     INT PRIMARY KEY IDENTITY ( 1, 1 ),
    [order_id]          INT   NOT NULL,
    [product_detail_id] INT   NOT NULL,
    [quantity]          INT   NOT NULL,
    [unit_price]        FLOAT NOT NULL,
    [created_at]        datetime2,
    [updated_at]        datetime2,
    FOREIGN KEY ([order_id]) REFERENCES [ORDERS] ([order_id]),
    FOREIGN KEY ([product_detail_id]) REFERENCES [PRODUCT_DETAILS] ([product_detail_id]) ON DELETE CASCADE ON UPDATE CASCADE
);

GO-- Bảng PAYMENT_METHODS
CREATE TABLE [PAYMENT_METHODS]
(
    [payment_id]   INT PRIMARY KEY IDENTITY ( 1, 1 ),
    [payment_type] nvarchar(255) NOT NULL,
    [created_at]   datetime2,
    [updated_at]   datetime2
);

GO-- Bảng ORDER_PAYMENTS
CREATE TABLE [ORDER_PAYMENTS]
(
    [order_payment_id] INT PRIMARY KEY IDENTITY ( 1, 1 ),
    [order_id]         INT       NOT NULL,
    [payment_id]       INT       NOT NULL,
    [payment_date]     datetime2 NOT NULL,
    [amount_paid]      FLOAT     NOT NULL,
    [created_at]       datetime2,
    [updated_at]       datetime2,
    FOREIGN KEY ([order_id]) REFERENCES [ORDERS] ([order_id]) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY ([payment_id]) REFERENCES [PAYMENT_METHODS] ([payment_id]) ON DELETE CASCADE ON UPDATE CASCADE
);

GO-- Bảng REVIEWS
CREATE TABLE [REVIEWS]
(
    [review_id]         INT PRIMARY KEY IDENTITY ( 1, 1 ),
    [product_detail_id] INT NOT NULL,
    [user_id]           INT NOT NULL,
    [rating]            INT NOT NULL,
    [comment]           nvarchar(MAX),
    [created_at]        datetime2,
    [updated_at]        datetime2,
    FOREIGN KEY ([product_detail_id]) REFERENCES [PRODUCT_DETAILS] ([product_detail_id]) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY ([user_id]) REFERENCES [USERS] ([user_id]) ON DELETE CASCADE ON UPDATE CASCADE
);

GO-- Bảng WISHLIST
CREATE TABLE [WISHLIST]
(
    [wishlist_id]       INT PRIMARY KEY IDENTITY ( 1, 1 ),
    [user_id]           INT NOT NULL,
    [product_detail_id] INT NOT NULL,
    [created_at]        datetime2,
    [updated_at]        datetime2,
    FOREIGN KEY ([user_id]) REFERENCES [USERS] ([user_id]) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY ([product_detail_id]) REFERENCES [PRODUCT_DETAILS] ([product_detail_id]) ON DELETE CASCADE ON UPDATE CASCADE
);

GO
-- Các ràng buộc khóa ngoại còn lại
ALTER TABLE [USER_ROLES]
    ADD FOREIGN KEY ([user_id]) REFERENCES [USERS] ([user_id]) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE [USER_ROLES]
    ADD FOREIGN KEY ([role_id]) REFERENCES [ROLES] ([role_id]) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE [PRODUCTS]
    ADD FOREIGN KEY ([category_id]) REFERENCES [CATEGORIES] ([category_id]) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE [TOKENS]
    ADD FOREIGN KEY ([user_id]) REFERENCES [USERS] ([user_id]) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE USER_ROLES
    ADD updated_at DATETIME2
GO

