create table insurance (
    id bigint AUTO_INCREMENT not null,
    inn varchar(12) UNIQUE,
    ogrn varchar(13) UNIQUE,
    name varchar(255) UNIQUE,
    adres varchar(255),
    primary key (id)
);
