    create table IF NOT EXISTS persons (
        id bigint not null AUTO_INCREMENT,
        name varchar(50) not null,
        address varchar(50) not null,
        primary key (id)
    ) engine=InnoDB;
