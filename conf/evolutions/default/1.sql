# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table CMN_TT_PORTAL (
  ID                        integer not null,
  PORTAL_TYPE               varchar(10) not null,
  PORTAL_TAB_TYPE           varchar(10) not null,
  PORTAL_NAME               varchar(100) not null,
  URL                       varchar(100),
  IMAGE_ICON                varchar(200),
  LAYOUT_TYPE               varchar(10)  not null,
  NOTE                      varchar(2000),
  CREATE_DATE               timestamp,
  CREATE_USER               varchar(10),
  UPDATE_DATE               timestamp,
  UPDATE_USER               varchar(10),
  constraint CMN_TT_PORTAL_PK primary key (id))
;

create table CMN_TT_PORTLET (
  ID                        integer not null,
  PORTAL_TYPE               varchar(10) not null,
  PORTAL_TAB_TYPE           varchar(10) not null,
  DISPLAY_TYPE              varchar(10) not null,
  PORTLET_NAME              varchar(100) not null,
  DISPLAY_NAME              varchar(100),
  ACTIVE_FLG                boolean,
  NOTE                      varchar(2000),
  CREATE_DATE               timestamp,
  CREATE_USER               varchar(10),
  UPDATE_DATE               timestamp,
  UPDATE_USER               varchar(10),
  constraint CMN_TT_PORTLET_PK primary key (id))
;

create table CMN_TT_PORTLET_PARAMETER (
  ID                        integer not null,
  PORTLET_ID                integer not null,
  NAME                      varchar(100) not null,
  VALUE                    varchar(2000),
  NOTE                      varchar(2000),
  CREATE_DATE               timestamp,
  CREATE_USER               varchar(10),
  UPDATE_DATE               timestamp,
  UPDATE_USER               varchar(10),
  constraint CMN_TT_PORTLET_PARAMETER_PK primary key (id))
;

create table CMN_TT_ACCOUNT (
  ID                        integer not null,
  ACCOUNT_ID                varchar(10) not null,
  PASSWORD                  varchar(10) not null,
  ACCOUNT_NAME              varchar(200) not null,
  EMAIL_ADDRESS             varchar(200),
  NOTE                      varchar(2000),
  CREATE_DATE               timestamp,
  CREATE_USER               varchar(10),
  UPDATE_DATE               timestamp,
  UPDATE_USER               varchar(10),
  constraint CMN_TT_ACCOUNT_PK primary key (id))
;

create table CMN_TM_ROLE (
  ID                        integer not null,
  NAME                      varchar(200),
  ROLE_DESC                 varchar(2000),
  NOTE                      varchar(2000),
  CREATE_DATE               timestamp,
  CREATE_USER               varchar(10),
  UPDATE_DATE               timestamp,
  UPDATE_USER               varchar(10),
  constraint CMN_TT_ROLE_PK primary key (id))
;

create table CMN_TM_SELECT (
  ID                        integer not null,
  SELECT_TYPE               varchar(10) not null,
  NAME                      varchar(100) not null,
  VALUE                    varchar(100),
  NOTE                      varchar(2000),
  CREATE_DATE               timestamp,
  CREATE_USER               varchar(10),
  UPDATE_DATE               timestamp,
  UPDATE_USER               varchar(10),
  constraint CMN_TM_SELECT_PK primary key (id))
;

create table CMN_TT_MENU (
  ID                        integer not null,
  PARENT_ID                 integer ,
  MENU_TYPE                 varchar(10) not null,
  ACTION_TYPE               varchar(10) not null,
  NAME                      varchar(200),
  URL                       varchar(200),
  IMAGE_ICON                varchar(200),
  MENU_DESC                 varchar(2000),
  NOTE                      varchar(2000),
  CREATE_DATE               timestamp,
  CREATE_USER               varchar(10),
  UPDATE_DATE               timestamp,
  UPDATE_USER               varchar(10),
  constraint CMN_TT_MENU_PK primary key (id))
;

create table CMN_TT_BOOKMARK (
  ID                        integer not null,
  PARENT_ID                 integer ,
  BOOKMARK_TYPE             varchar(10),
  NAME                      varchar(200) not null,
  URL                       varchar(200) not null,
  IMAGE_ICON                varchar(200),
  BOOKMARK_DESC             varchar(2000),
  NOTE                      varchar(2000),
  CREATE_DATE               timestamp,
  CREATE_USER               varchar(10),
  UPDATE_DATE               timestamp,
  UPDATE_USER               varchar(10),
  constraint CMN_TT_BOOKMARK_PK primary key (id))
;

create table DBX_TM_TABLE (
  ID                        integer not null,
  TABLE_NAME                varchar(10) not null,
  DISPLAY_NAME              varchar(100),
  TABLE_DESC                varchar(2000),
  IMAGE_ICON                varchar(200),
  NOTE                      varchar(2000),
  CREATE_DATE               timestamp,
  CREATE_USER               varchar(10),
  UPDATE_DATE               timestamp,
  UPDATE_USER               varchar(10),
  constraint DBX_TM_TABLE_PK primary key (id))
;

create table DBX_TM_TABLE_DISPLAY (
  ID                        integer not null,
  TABLE_NAME                varchar(10) not null,
  DISPLAY_TYPE              varchar(10) not null,
  DISPLAY_NAME              varchar(100),
  TABLE_DESC                varchar(2000),
  IMAGE_ICON                varchar(200),
  NOTE                      varchar(2000),
  CREATE_DATE               timestamp,
  CREATE_USER               varchar(10),
  UPDATE_DATE               timestamp,
  UPDATE_USER               varchar(10),
  constraint DBX_TM_TABLE_DISPLAY_PK primary key (id))
;

create table DBX_TM_TABLE_COLUMN (
  ID                        integer not null,
  TABLE_NAME                varchar(10) not null,
  TABLE_COL_NAME            varchar(10) not null,
  COLUMN_NAME               varchar(10) not null,
  DISPLAY_TYPE              varchar(10) not null,
  DISPLAY_NAME              varchar(100),
  COLUMN_TYPE               varchar(10) not null,
  COLUMN_LENGTH             integer,
  LIST_FLG                  boolean,
  LIST_ORDER                integer,
  SEARCH_FLG                boolean,
  SEARCH_ORDER              integer,
  NOTE                      varchar(2000),
  CREATE_DATE               timestamp,
  CREATE_USER               varchar(10),
  UPDATE_DATE               timestamp,
  UPDATE_USER               varchar(10),
  constraint DBX_TM_TABLE_COLUMN_PK primary key (id))
;

create table DBX_TT_TABLE_DATA (
  ID                        integer not null,
  TABLE_NAME                varchar(10) not null,
  STR_COL01                 varchar(1000),
  STR_COL02                 varchar(1000),
  STR_COL03                 varchar(1000),
  STR_COL04                 varchar(1000),
  STR_COL05                 varchar(1000),
  STR_COL06                 varchar(1000),
  STR_COL07                 varchar(1000),
  STR_COL08                 varchar(1000),
  STR_COL09                 varchar(1000),
  STR_COL10                 varchar(1000),
  NUM_COL01                 integer,
  NUM_COL02                 integer,
  NUM_COL03                 integer,
  NUM_COL04                 integer,
  NUM_COL05                 integer,
  NUM_COL06                 integer,
  NUM_COL07                 integer,
  NUM_COL08                 integer,
  NUM_COL09                 integer,
  NUM_COL10                 integer,
  DAT_COL01                 timestamp,
  DAT_COL02                 timestamp,
  NOTE                      varchar(1000),
  CREATE_DATE               timestamp,
  CREATE_USER               varchar(10),
  UPDATE_DATE               timestamp,
  UPDATE_USER               varchar(10),
  constraint DBX_TT_TABLE_DATA_PK primary key (id))
;

create table SNS_TT_TIMELINE (
  ID                        integer not null,
  ACCOUNT_ID                varchar(10) not null,
  PARENT_ID                 integer,
  TITLE                     varchar(100),
  MESSAGE                   varchar(2000),
  NOTE                      varchar(2000),
  CREATE_DATE               timestamp,
  CREATE_USER               varchar(10),
  UPDATE_DATE               timestamp,
  UPDATE_USER               varchar(10),
  constraint SNS_TT_TIMELINE_PK primary key (id))
;

create table SNS_TT_MESSAGE (
  ID                        integer not null,
  ACCOUNT_ID                varchar(10) not null,
  MESSAGE                   varchar(2000),
  NOTE                      varchar(2000),
  CREATE_DATE               timestamp,
  CREATE_USER               varchar(10),
  UPDATE_DATE               timestamp,
  UPDATE_USER               varchar(10),
  constraint SNS_TT_MESSAGE_PK primary key (id))
;

create table SYS_TT_LOG (
  ID                        integer not null,
  APP_TYPE                  varchar(10) not null,
  UUID                      varchar(100) not null,
  LOG_TITLE                 varchar(2000) not null,
  LOG_BODY                  varchar(2000),
  CLASS_NAME                varchar(2000),
  METHOD_NAME               varchar(2000),
  NOTE                      varchar(2000),
  CREATE_DATE               timestamp,
  CREATE_USER               varchar(10),
  UPDATE_DATE               timestamp,
  UPDATE_USER               varchar(10),
  constraint SYS_TT_LOG_PK primary key (id))
;

create sequence CMN_TT_PORTAL_SEQ start with 100;

create sequence CMN_TT_PORTLET_SEQ start with 100;

create sequence CMN_TT_PORTLET_PARAMETER_SEQ start with 100;

create sequence CMN_TT_BOOKMARK_SEQ start with 100;

create sequence CMN_TT_MENU_SEQ start with 100;

create sequence CMN_TT_ACCOUNT_SEQ start with 100;

create sequence CMN_TM_ROLE_SEQ start with 100;

create sequence CMN_TM_SELECT_SEQ start with 100;

create sequence DBX_TM_TABLE_SEQ start with 100;

create sequence DBX_TM_TABLE_COLUMN_SEQ start with 100;

create sequence DBX_TT_TABLE_DATA_SEQ start with 100;

create sequence SNS_TT_TIMELINE_SEQ start with 1;

create sequence SNS_TT_MESSAGE_SEQ start with 1;

create sequence SYS_TT_LOG_SEQ start with 1;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists CMN_TT_PORTAL;

drop table if exists CMN_TT_PORTLET;

drop table if exists CMN_TT_PORTLET_PARAMETER;

drop table if exists CMN_TT_ACCOUNT;

drop table if exists CMN_TM_ROLE;

drop table if exists CMN_TM_SELECT;

drop table if exists CMN_TT_MENU;

drop table if exists CMN_TT_BOOKMARK;

drop table if exists DBX_TM_TABLE;

drop table if exists DBX_TM_TABLE_DISPLAY;

drop table if exists DBX_TM_TABLE_COLUMN;

drop table if exists DBX_TT_TABLE_DATA;

drop table if exists SNS_TT_TIMELINE;

drop table if exists SNS_TT_MESSAGE;

drop table if exists SYS_TT_LOG;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists CMN_TT_PORTAL_SEQ;

drop sequence if exists CMN_TT_PORTLET_SEQ;

drop sequence if exists CMN_TT_PORTLET_PARAMETER_SEQ;

drop sequence if exists CMN_TT_MENU_SEQ;

drop sequence if exists CMN_TM_ROLE_SEQ;

drop sequence if exists CMN_TM_SELECT_SEQ;

drop sequence if exists CMN_TT_BOOKMARK_SEQ;

drop sequence if exists CMN_TT_ACCOUNT_SEQ;

drop sequence if exists DBX_TM_TABLE_SEQ;

drop sequence if exists DBX_TMT_TABLE_COLUMN_SEQ;

drop sequence if exists DBX_TT_TABLE_DATA_SEQ;

drop sequence if exists SNS_TT_TIMELINE_SEQ;

drop sequence if exists SNS_TT_MESSAGE_SEQ;

drop sequence if exists SYS_TT_LOG_SEQ;