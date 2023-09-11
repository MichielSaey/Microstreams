CREATE DATABASE IF NOT EXISTS `TopStarters` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `TopStarters`;
create table topstarter
(
    id      bigint auto_increment
        primary key,
    age     int          null,
    name    varchar(255) null,
    project varchar(255) null
);