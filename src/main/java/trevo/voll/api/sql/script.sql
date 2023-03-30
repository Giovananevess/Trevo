create database trevo;

create table tb_area (
       id bigserial not null,
        name varchar(90) not null,
        primary key (id)
    );

create table tb_culture (
       id bigserial not null,
        name varchar(90) not null,
        primary key (id)
    );

create table tb_image (
       id bigserial not null,
        image oid,
        name varchar(255),
        type varchar(255),
        primary key (id)
    );

create table tb_order (
       id bigserial not null,
        email varchar(255),
        name varchar(90) not null,
        telefone varchar(255),
        primary key (id)
    );

 create table tb_order_product (
       id_order bigint not null,
        id_product bigint not null
    );

 create table tb_product (
        id bigserial not null,
         data date,
         descricao Text not null,
         name varchar(255) not null,
         status boolean,
         primary key (id)
     );

  create table tb_product_area (
        id_product bigint not null,
         id_area bigint not null
     );

   create table tb_product_culture (
         id_product bigint not null,
          id_culture bigint not null
       );

 create table tb_product_image (
        id_product bigint not null,
         id_image bigint not null
     );

 alter table if exists tb_area
        add constraint UK_3hxiy06cy88wvmeedv7wfomjq unique (name);

 alter table if exists tb_culture
        add constraint UK_4m89i9d34k8pf6oyri79fipyg unique (name);

 alter table if exists tb_product
        add constraint UK_lovy3681ry0dl5ox28r6679x6 unique (name);

 alter table if exists tb_product_image
        add constraint UK_ek1oe9yludtdjteeeafiy1v7x unique (id_image);

 alter table if exists tb_order_product
        add constraint FKp15fqa211ed8xo7ni8l7rrti8
        foreign key (id_product)
        references tb_product;

 alter table if exists tb_order_product
        add constraint FKo7w8vo4dxycojk9yh58nx3bch
        foreign key (id_order)
        references tb_order;

 alter table if exists tb_product_area
        add constraint FK6oa681uaff9m9l1tptudy1dts
        foreign key (id_area)
        references tb_area;

 alter table if exists tb_product_area
        add constraint FK60ntvp2pjq4p1axv09hfuxvyu
        foreign key (id_product)
        references tb_product;

 alter table if exists tb_product_culture
        add constraint FKmasava0p0slid8f4nbo23ymqo
        foreign key (id_culture)
        references tb_culture;

 alter table if exists tb_product_culture
        add constraint FK42y1h9l539aktfeuhj1oypsga
        foreign key (id_product)
        references tb_product;

 alter table if exists tb_product_image
        add constraint FK9h0yesm8tq9kh1vhgcvd8r1rh
        foreign key (id_image)
        references tb_image;

 alter table if exists tb_product_image
        add constraint FK62n6ct4rynix6eta3myf4nva1
        foreign key (id_product)
        references tb_product;