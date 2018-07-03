--Start Create table

create table tb_category (
  id   serial primary key,
  name varchar
);


create table tb_book (
  id        serial primary key,
  title     varchar,
  author    varchar,
  publisher varchar,
  thumbnail varchar,
  cate_id   int references tb_category (id)
);


create table tb_user (
  id          serial primary key,
  username    varchar,
  password    varchar,
  status      boolean default true,
  profile_img varchar
);

create table tb_role (
  id          serial primary key,
  role        varchar,
  description varchar
);


create table tb_user_role (
  user_id int not null
    constraint user_id_fk references tb_user (id)
    on delete cascade on update cascade,
  role_id int not null
    constraint role_id_fk references tb_role (id)
    on delete cascade on update cascade,

  primary key (user_id, role_id)
);

-- End create table


























