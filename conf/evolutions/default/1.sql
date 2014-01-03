# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table advertisement (
  id                        BIGINT UNSIGNED auto_increment not null,
  created_at                timestamp,
  created_by                varchar(20),
  updated_at                timestamp,
  updated_by                varchar(20),
  title                     varchar(255),
  description               varchar(255),
  thumbnail                 varchar(255),
  images                    varchar(255),
  version                   bigint not null,
  constraint pk_advertisement primary key (id))
;

create table show_room (
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
  constraint pk_show_room primary key (id))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists advertisement;

drop table if exists show_room;

SET REFERENTIAL_INTEGRITY TRUE;

