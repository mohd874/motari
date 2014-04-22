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
  price                     integer,
  count_views               integer,
  manufacturer              varchar(255),
  model                     varchar(255),
  year_made                 integer,
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
  email                     varchar(255),
  description               varchar(255),
  version                   bigint not null,
  constraint pk_SHOW_ROOM primary key (id))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists ADVERTISEMENT;

drop table if exists CAR_TYPE;

drop table if exists SHOW_ROOM;

SET REFERENTIAL_INTEGRITY TRUE;

