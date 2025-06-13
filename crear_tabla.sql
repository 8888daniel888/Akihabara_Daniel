drop database if exists akihabara_db;
create database akihabara_db;
use akihabara_db;

create table productos(
	id int primary key auto_increment,
    nombre varchar(255) not null,
    categoria varchar(100),
    precio decimal(10,2),
    stock int
);

select * from productos;