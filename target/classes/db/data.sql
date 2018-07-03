
insert into tb_category (name) values ('Comics'),( 'Fantasy'),( 'Comedy'), ('Scary');


insert into tb_book (title, author, publisher, thumbnail, cate_id)
 values ('title', 'author', 'pp class 6th', 'thumbnail', 1);


insert into tb_book (title, author, publisher, thumbnail, cate_id)
 values ('title 2', 'author', 'pp class 6th', 'thumbnail', 2);


insert into tb_category (name) values ('comics'), ('romance'), ('fantasy'), ('comedy');


insert into tb_book (title, author, publisher, thumbnail, cate_id)
values ('title', 'author', 'publisher', 'thumbnail', 1);

--select * from tb_book b inner join tb_category tc on b.cate_id = tc.id;

--select * from tb_book;

--delete from tb_book where id = 4;

insert into tb_book (title) values ('meme');


--truncate table tb_book restart identity;


--select currval('tb_book_id_seq');

--select nextval('tb_book_id_seq');

insert into tb_category (name) values ('me'), ('me1');

--select * from tb_category;


insert into tb_role (role) values ('ADMIN')
  , ('DBA'), ('USER');


insert into tb_user (username, password, profile_img)

values ('somnak', 'somnak', 'somnak.png'),
  ('ladang', 'ladang', 'ladang.png')
  , ('sreypov', 'sreypov', 'sreypov.png'),
  ('sal', 'sal', 'sal.gif');

--select * from tb_user;


--select * from tb_role;


insert into tb_user_role (user_id, role_id) values
  (1, 1), (1, 2), (2, 2), (2, 3), (3, 1), (3, 2), (3, 3),
(4, 3);


--select * from tb_user_role;

--select * from tb_role tr inner join tb_user_role tur on tr.id = tur.role_id where tur.user_id=3;


--update tb_user set password = '$2a$10$brsaYTgt325OJDkeAzI/6e/f.jlQbeV.6TpCumkDihun8GN.I21me' where id=3;

