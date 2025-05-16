create database fsd_hex_may_25;
 use fsd_hex_may_25;
create table book(
id int primary key auto_increment,
title varchar(255) not null,
price double not null,
author varchar(255),
publication_house enum('Mcgraw Hill','DreamFolks','Warner Bros') not null,
category enum('Fiction','War','Comedy','Sports') not null,
book_count int,
status enum('In stock','Out_of_stock') not null);

insert into book(title, price, author, publication_house, category, book_count, status) 
values
('FunJungle', 455.6, 'David', 'DreamFolks', 'COMEDY', 6, 'IN STOCK'),
('Warrior Tales', 299.9, 'A. K. Singh', 'Mcgraw Hill', 'WAR', 10, 'IN STOCK'),
('Laugh Out Loud', 199.0, 'Sally Greene', 'DreamFolks', 'COMEDY', 4, 'OUT_OF_STOCK'),
('Match Day', 349.5, 'Tom Carter', 'Warner Bros', 'SPORTS', 8, 'IN STOCK');


-- 1 Fetch Books with price is less than given price
delimiter $$
create procedure proc_fetch_books(in book_price double)
begin
select * from book where status = 'In stock' and price<book_price;
end;
call proc_fetch_books(500);



-- 2 Delete books that are from given publication house
delimiter $$
create procedure proc_delete_books(in p_house varchar(255))
begin
 declare num_rows int default 0;
 declare i int default 0;
 declare p_id int default 0;
 select count(id) into num_rows from book where publication_house=p_house;
 while i<num_rows do
 select id into p_id from book where publication_house=p_house limit i,1;
 delete from book where id=p_id;
 set i=i+1;
 end while;
 end;
 
call proc_delete_books('Warner Bros');
drop procedure proc_delete_books;


-- 3 Update the price of books
delimiter $$
create procedure proc_update_price(in percent double,in categ varchar(255))
begin
 declare num_rows int default 0;
 declare i int default 0;
 declare book_id int default 0;
 select count(id) into num_rows from book where category=categ;
 while i<num_rows do
 select id into book_id from book where category=categ limit i,1;
update book set price=price+(price*percent/100) where id=book_id;
set i=i+1;
end while;
end;


drop procedure proc_update_price;
call proc_update_price(5,'Sports');

select * from book;