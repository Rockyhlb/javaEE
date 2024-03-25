drop database if exists message_wall;
CREATE DATABASE message_wall DEFAULT CHARACTER SET utf8mb4;

use message_wall;
DROP TABLE IF EXISTS message_info;
CREATE TABLE `message_info` (
    `id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
    `from` VARCHAR ( 127 ) NOT NULL,
    `to` VARCHAR ( 127 ) NOT NULL,
    `message` VARCHAR ( 256 ) NOT NULL,
    `delete_flag` TINYINT ( 4 ) DEFAULT 0 COMMENT '0-正常, 1-删除',
    `create_time` DATETIME DEFAULT now(),
#   ON UPDATE now()：默认每次更新时都会将该字段设置当前时间
    `update_time` DATETIME DEFAULT now() ON UPDATE now(),
    PRIMARY KEY ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4;

insert into message_info(`from`, `to`,`message`) values ("哈哈","嘻嘻","嘿嘿");
select * from message_info;