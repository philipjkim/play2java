# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table archive (
  id                        bigint not null,
  title                     varchar(255),
  path                      varchar(255),
  constraint pk_archive primary key (id))
;

create table task (
  id                        bigint not null,
  label                     varchar(255),
  constraint pk_task primary key (id))
;

create table user (
  email                     varchar(255) not null,
  nickname                  varchar(255),
  password                  varchar(255),
  salt                      varchar(255),
  access_token              varchar(255),
  constraint pk_user primary key (email))
;

create sequence archive_seq;

create sequence task_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists archive;

drop table if exists task;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists archive_seq;

drop sequence if exists task_seq;

drop sequence if exists user_seq;

