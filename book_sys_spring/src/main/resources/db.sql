drop database if exists book_sys;

CREATE DATABASE book_sys DEFAULT CHARACTER SET utf8mb4;

-- book_sys: 用户表
DROP TABLE IF EXISTS user_info;
CREATE TABLE user_info (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user_name` VARCHAR ( 128 ) NOT NULL,
    `password` VARCHAR ( 128 ) NOT NULL,
    `delete_flag` TINYINT ( 4 ) NULL DEFAULT 0,
    `create_time` DATETIME DEFAULT now(),
    `update_time` DATETIME DEFAULT now() ON UPDATE now(),
    PRIMARY KEY ( `id` ),
    UNIQUE INDEX `user_name_UNIQUE` ( `user_name` ASC )
) ENGINE = INNODB DEFAULT charset = utf8mb4 COMMENT = '用户表';

-- book_sys: 图书表
DROP TABLE IF EXISTS book_info;
CREATE TABLE `book_info` (
    `id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
    `book_name` VARCHAR ( 127 ) NOT NULL,
    `author` VARCHAR ( 127 ) NOT NULL,
    `count` INT ( 11 ) NOT NULL,
    `price` DECIMAL (7,2 ) NOT NULL,
    `publish` VARCHAR ( 256 ) NOT NULL,
    `status` TINYINT ( 4 ) DEFAULT 1 COMMENT '0-⽆效, 1-正常, 2-不允许借阅',
    `create_time` DATETIME DEFAULT now(),
    `update_time` DATETIME DEFAULT now() ON UPDATE now(),
    PRIMARY KEY ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4;

-- 初始化用户数据
INSERT INTO user_info (`user_name`, `password`) VALUES ( 'admin', 'admin' );
INSERT INTO user_info (`user_name`, `password` ) VALUES ( 'zhangsan', '123456' );
INSERT INTO user_info (`user_name`, `password` ) VALUES ( 'lisi', '000000' );
select * from user_info;

-- 初始化图书数据
INSERT INTO `book_info` (`book_name`, `author`, `count`, `price`, `publish`)
VALUES
    ('红楼梦', '曹雪芹', 20, 59.70, '人民文学出版社'),
    ('三国演义', '罗贯中', 18, 68.40, '人民文学出版社'),
    ('西游记', '吴承恩', 8, 190.00, '人民文学出版社'),
    ('水浒传', '施耐庵', 12, 195.00, '人民文学出版社'),
    ('毛泽东选集 第一卷', '毛泽东', 25, 40.00, '人民出版社'),
    ('活着', '余华', 6, 25.00, '作家出版社'),
    ('我与地坛', '史铁生', 13, 25.00, '人民文学出版社'),
    ('人类简史三部曲', '尤瓦尔·赫拉利', 14, 99.00, '中信出版社'),
    ('失落之城', '安娜丽·纽伊茨', 14, 43.50, '中信出版社'),
    ('瓦尔登湖', '亨利·戴维·梭罗', 9, 85.00, ' 译林出版社')
select * from book_info;