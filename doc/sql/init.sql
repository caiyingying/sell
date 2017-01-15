CREATE DATABASE sell DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE DATABASE sell DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

DROP TABLE IF EXISTS sys_user;
DROP TABLE IF EXISTS sys_role;
DROP TABLE IF EXISTS sys_authorities;
DROP TABLE IF EXISTS sys_user_role;
DROP TABLE IF EXISTS sys_role_authorities;
DROP TABLE IF EXISTS business;
DROP TABLE IF EXISTS business_user;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS product;

CREATE TABLE sys_user
(
    ID INT(10) ZEROFILL AUTO_INCREMENT PRIMARY KEY,
    LOGIN_NAME VARCHAR(50) NOT NULL,
    USER_PASSWORD VARCHAR(200),
    USER_NAME VARCHAR(20),
    ENABLED   VARCHAR(1) DEFAULT '1' NOT NULL,
    MOBILE VARCHAR(20),
    WECHAT VARCHAR(50)
);

ALTER TABLE sys_user COMMENT='用户表';
ALTER table sys_user MODIFY 'LOGIN_NAME' COMMENT '登录账号';
ALTER table sys_user MODIFY 'USER_PASSWORD' COMMENT '用户密码';
ALTER table sys_user MODIFY 'USER_NAME' COMMENT '用户姓名';
ALTER table sys_user MODIFY 'ENABLED' COMMENT '是否启用 1:启用 0:停用';
ALTER table sys_user MODIFY 'MOBILE' COMMENT '手机号';
ALTER table sys_user MODIFY 'WECHAT' COMMENT '微信号';


CREATE TABLE sys_role
(
    ID INT(10) ZEROFILL AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL,
    DESCRIPTION VARCHAR(50) NOT NULL, 
    ENABLED   VARCHAR(1) DEFAULT '1' NOT NULL 
);

CREATE TABLE sys_authorities
(
    ID INT(10) ZEROFILL AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL, 
    DESCRIPTION VARCHAR(50) NOT NULL, 
    PERMISSION VARCHAR(50), 
    ENABLED   VARCHAR(1) DEFAULT '1' NOT NULL 
);


CREATE TABLE sys_user_role
(
  ID      INT(10) ZEROFILL AUTO_INCREMENT PRIMARY KEY,
  USER_ID INT(10) NOT NULL,
  ROLE_ID INT(10) NOT NULL
);

CREATE TABLE sys_role_authorities
(
  ID      INT(10) ZEROFILL AUTO_INCREMENT PRIMARY KEY,
  ROLE_ID INT(10) NOT NULL,
  AUTH_ID INT(10) NOT NULL
);

CREATE TABLE dealers
(
    ID INT(10) ZEROFILL AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL, 
    DESCRIPTION VARCHAR(20), 
    CODE INT(5),
    TICKET VARCHAR(200),
    CODE_URL VARCHAR(200),
    ENABLED VARCHAR(1) DEFAULT '1' NOT NULL,
    PHONE VARCHAR(20),
    ADDRESS VARCHAR(200) 
);

CREATE TABLE dealers_user
(
  ID      INT(10) ZEROFILL AUTO_INCREMENT PRIMARY KEY,
  DEALERS_ID INT(10) NOT NULL,
  USER_ID INT(10) NOT NULL
);


CREATE TABLE customer
(
    ID INT(10) ZEROFILL AUTO_INCREMENT PRIMARY KEY,
    WECHAT VARCHAR(100) not null, 
    NICK VARCHAR(50) not null, 
    BUSINESS_ID INT(10) 
);

CREATE TABLE product
(
    ID INT(10) ZEROFILL AUTO_INCREMENT PRIMARY KEY,
    PRODUCT_ID VARCHAR(100) not null, 
    PRODUCT_NAME VARCHAR(200) not null, 
    PRODUCT_PRICE DOUBLE not null,
    DISCOUNT DOUBLE
);
