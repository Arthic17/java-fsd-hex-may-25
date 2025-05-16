 use fsd_hex_may_25;
 
 create table customer(
id int primary key auto_increment,
name varchar(255) not null,
city varchar(255));

 create table category(
id int primary key auto_increment,
name varchar(255) not null);

 create table product(
id int primary key auto_increment,
title varchar(255) not null,
price double,
description varchar(255),
category_id int,
foreign key (category_id) references category(id));

 create table purchase(
id int primary key auto_increment,
date_of_purchase varchar(255) not null,
coupon_used varchar(45),
amount_paid double,
customer_id int,
product_id int,
foreign key (customer_id) references customer(id),
foreign key (product_id) references product(id));

insert into customer(name,city) values("John doe","Mumbai"),
("Jane doe","Chennai");

insert into category(name) values("Mobiles"),("Laptops");

select * from category;
select * from product;
select * from customer;
select * from purchase;
