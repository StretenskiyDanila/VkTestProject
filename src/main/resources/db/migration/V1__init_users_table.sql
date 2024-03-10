CREATE TABLE users
(
    id serial NOT NULL constraint w_users_pk PRIMARY KEY ,
    username varchar(32) NOT NULL,
    email varchar(32) NOT NULL

);