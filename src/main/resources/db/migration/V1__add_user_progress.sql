--    create database IF NOT EXISTS user_progress;

    drop table if exists hibernate_sequence;

    drop table if exists user_progress;

    drop table if exists users;

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB;

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    create table IF NOT EXISTS users (
         id bigint not null AUTO_INCREMENT,
         country varchar(50) not null,
         name varchar(50) not null,
         primary key (id)
     ) engine=InnoDB;

    create table IF NOT EXISTS user_progress (
       id bigint not null AUTO_INCREMENT,
        level integer not null,
        score float not null,
        user_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    alter table user_progress
       add constraint uk_user_progress unique (user_id);

    alter table users
       add constraint uk_user unique (name);

    alter table user_progress
       add constraint fk_user_progress
       foreign key (user_id)
       references users (id);

