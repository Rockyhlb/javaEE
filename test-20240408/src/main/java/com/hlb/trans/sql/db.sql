# 创建数据库
DROP DATABASE IF EXISTS trans_test;
CREATE DATABASE trans_test DEFAULT CHARACTER SET utf8mb4;
use trans_test;
-- 用户表
DROP TABLE IF EXISTS user_info;
CREATE TABLE user_info
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `user_name`   VARCHAR(128) NOT NULL,
    `password`    VARCHAR(128) NOT NULL,
    `create_time` DATETIME DEFAULT now(),
    `update_time` DATETIME DEFAULT now() ON UPDATE now(),
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '用户表';

-- 操作日志表
DROP TABLE IF EXISTS log_info;
CREATE TABLE log_info
(
    `id`          INT PRIMARY KEY auto_increment,
    `user_name`   VARCHAR(128) NOT NULL,
    `op`          VARCHAR(256) NOT NULL,
    `create_time` DATETIME DEFAULT now(),
    `update_time` DATETIME DEFAULT now() ON UPDATE now()
) DEFAULT charset 'utf8mb4';