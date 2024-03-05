create database if not exists blog_system charset=utf8;

use blog_system;

drop table if exists blog;
drop table if exists user;

create table user (
    id int primary key auto_increment,
    userName varchar(20) unique,
    passWord varchar(20)
);

create table blog (
    blogId int primary key auto_increment,
    title varchar(30),
    content varchar(1024),
    postTime datetime,
    userId int,
    foreign key(userId) references user(id)
);

-- 插入一些测试数据
insert into user values (1,"zhangsan","0000");
insert into user values (2,"lisi","9999");

insert into blog values (null,"Java炒粉","我爱学习",now(),1);
insert into blog values (null,"Python炒饭","我爱学习",now(),1);
insert into blog values (null,"论资本论","我爱学习",now(),1);