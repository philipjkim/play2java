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

create sequence archive_seq;

create sequence task_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists archive;

drop table if exists task;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists archive_seq;

drop sequence if exists task_seq;

