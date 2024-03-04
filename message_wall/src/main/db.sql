create database if not exists message_wall charset=utf8;

use message_wall;

drop table if exits message;

create table message(`from` varchar(10),`to` varchar(10),message varchar(1024));

insert into message values ("A","B","我喜欢你");