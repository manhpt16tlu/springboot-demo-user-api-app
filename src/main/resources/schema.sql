create table users
(
    id               int not null primary key auto_increment,
    email            varchar(100),
    first_name       varchar(100),
    last_name        varchar(100),
    encrypt_password varchar(100),
    user_id          varchar(50)
);