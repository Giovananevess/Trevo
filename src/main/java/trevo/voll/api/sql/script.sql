create table tb_area (
       id bigserial not null,
        name varchar(90) not null,
        primary key (id)
    )

create table tb_culture (
       id bigserial not null,
        name varchar(90) not null,
        primary key (id)
    )

create table tb_image (
       id bigserial not null,
        image oid,
        name varchar(255),
        type varchar(255),
        primary key (id)
    )


create table tb_order (
       id bigserial not null,
        email varchar(255),
        name varchar(90) not null,
        telefone varchar(255),
        primary key (id)
    )

 create table tb_order_product (
       id_order bigint not null,
        id_product bigint not null
    )

 create table tb_product (
        id bigserial not null,
         data date,
         descricao Text not null,
         name varchar(255) not null,
         status boolean,
         primary key (id)
     )

  create table tb_product_area (
        id_product bigint not null,
         id_area bigint not null
     )


   create table tb_product_culture (
          id_product bigint not null,
           id_culture bigint not null
       )


 create table tb_product_image (
        id_product bigint not null,
         id_image bigint not null
     )