-- 创建数据库
# create database if not exists blog_sys_spring charset utf8mb4;

-- 用户表
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `user_name`   VARCHAR(128) NOT NULL,
    `password`    VARCHAR(128) NOT NULL,
    `github_url`  VARCHAR(128) NULL,
    `delete_flag` TINYINT(4)   NULL DEFAULT 0,
    `create_time` DATETIME          DEFAULT now(),
    `update_time` DATETIME          DEFAULT now(),
    PRIMARY KEY (id),
    UNIQUE INDEX user_name_UNIQUE (user_name ASC)
) ENGINE = INNODB
  DEFAULT charset = utf8mb4 COMMENT = '用户表';

-- 创建博客表
drop table if exists blog;
CREATE TABLE blog
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(200) NULL,
    `content`     TEXT         NULL,
    `user_id`     INT(11)      NULL,
    `delete_flag` TINYINT(4)   NULL DEFAULT 0,
    `create_time` DATETIME          DEFAULT now(),
    `update_time` DATETIME          DEFAULT now(),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '博客表';

-- 新增用户信息
insert into user (`user_name`, `password`, `github_url`)
values ('rocky', '000000', 'https://github.com/Rockyhlb');
insert into user (`user_name`, `password`, `github_url`)
values ('user1', '999999', 'https://github.com');
-- 新增博客信息
insert into blog (`title`, `content`, `user_id`)
values ('这是标题1', '这是正文1', 1);
insert into blog (`title`, `content`, `user_id`)
values ('这是标题2', '这是正文2', 1);
insert into blog (`title`, `content`, `user_id`)
values ('这是标题3', '这是正文3', 2);
