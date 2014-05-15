# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table ADVERTISEMENT (
  id                        BIGINT UNSIGNED auto_increment not null,
  created_at                timestamp,
  created_by                varchar(20),
  updated_at                timestamp,
  updated_by                varchar(20),
  title                     varchar(255),
  description               varchar(255),
  thumbnail                 varchar(255),
  images                    varchar(255),
  owner_id                  BIGINT UNSIGNED,
  price                     integer,
  count_views               integer,
  manufacturer              varchar(255),
  model                     varchar(255),
  year_made                 integer,
  car_type_id               BIGINT UNSIGNED,
  body_condition            varchar(255),
  car_condition             varchar(255),
  engine_condition          varchar(255),
  drive_train               varchar(255),
  number_of_doors           integer,
  exterior_color            varchar(255),
  interior_color            varchar(255),
  distance_driven           integer,
  specs                     varchar(255),
  number_of_cylinders       integer,
  horse_power               integer,
  fuel_type                 varchar(255),
  warrenty                  varchar(255),
  extras                    varchar(255),
  version                   bigint not null,
  constraint pk_ADVERTISEMENT primary key (id))
;

create table CAR_TYPE (
  id                        BIGINT UNSIGNED auto_increment not null,
  created_at                timestamp,
  created_by                varchar(20),
  updated_at                timestamp,
  updated_by                varchar(20),
  name                      varchar(255),
  version                   bigint not null,
  constraint pk_CAR_TYPE primary key (id))
;

create table SHOW_ROOM (
  id                        BIGINT UNSIGNED auto_increment not null,
  created_at                timestamp,
  created_by                varchar(20),
  updated_at                timestamp,
  updated_by                varchar(20),
  name                      varchar(255),
  location                  varchar(255),
  logo                      varchar(255),
  phone                     varchar(255),
  fax                       varchar(255),
  mobile                    varchar(255),
  po_box                    varchar(255),
  email                     varchar(255),
  website                   varchar(255),
  description               varchar(255),
  version                   bigint not null,
  constraint pk_SHOW_ROOM primary key (id))
;

alter table ADVERTISEMENT add constraint fk_ADVERTISEMENT_owner_1 foreign key (owner_id) references SHOW_ROOM (id) on delete restrict on update restrict;
create index ix_ADVERTISEMENT_owner_1 on ADVERTISEMENT (owner_id);
alter table ADVERTISEMENT add constraint fk_ADVERTISEMENT_carType_2 foreign key (car_type_id) references CAR_TYPE (id) on delete restrict on update restrict;
create index ix_ADVERTISEMENT_carType_2 on ADVERTISEMENT (car_type_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists ADVERTISEMENT;

drop table if exists CAR_TYPE;

drop table if exists SHOW_ROOM;

SET REFERENTIAL_INTEGRITY TRUE;

