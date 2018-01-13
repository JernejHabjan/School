/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2014                    */
/* Created on:     4. 01. 2018 09:07:38                         */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Flight') and o.name = 'FK_FLIGHT_AIRPLANE__PLANE')
alter table Flight
   drop constraint FK_FLIGHT_AIRPLANE__PLANE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Flight') and o.name = 'FK_FLIGHT_FROM_LOCATION')
alter table Flight
   drop constraint FK_FLIGHT_FROM_LOCATION
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Flight') and o.name = 'FK_FLIGHT_TO_LOCATION')
alter table Flight
   drop constraint FK_FLIGHT_TO_LOCATION
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('"Order"') and o.name = 'FK_ORDER_INITIAL_F_FLIGHT')
alter table "Order"
   drop constraint FK_ORDER_INITIAL_F_FLIGHT
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('"Order"') and o.name = 'FK_ORDER_RETURN_FL_FLIGHT')
alter table "Order"
   drop constraint FK_ORDER_RETURN_FL_FLIGHT
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('"Order"') and o.name = 'FK_ORDER_USER_ORDE_USER')
alter table "Order"
   drop constraint FK_ORDER_USER_ORDE_USER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Passenger') and o.name = 'FK_PASSENGE_PASSENGER_ORDER')
alter table Passenger
   drop constraint FK_PASSENGE_PASSENGER_ORDER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('"User"') and o.name = 'FK_USER_USER_ROLE_ROLE')
alter table "User"
   drop constraint FK_USER_USER_ROLE_ROLE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Flight')
            and   name  = 'to_FK'
            and   indid > 0
            and   indid < 255)
   drop index Flight.to_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Flight')
            and   name  = 'airplane_used_FK'
            and   indid > 0
            and   indid < 255)
   drop index Flight.airplane_used_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Flight')
            and   name  = 'from_FK'
            and   indid > 0
            and   indid < 255)
   drop index Flight.from_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Flight')
            and   type = 'U')
   drop table Flight
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Location')
            and   type = 'U')
   drop table Location
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('"Order"')
            and   name  = 'return_flight_FK'
            and   indid > 0
            and   indid < 255)
   drop index "Order".return_flight_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('"Order"')
            and   name  = 'initial_flight_FK'
            and   indid > 0
            and   indid < 255)
   drop index "Order".initial_flight_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('"Order"')
            and   name  = 'user_ordering_FK'
            and   indid > 0
            and   indid < 255)
   drop index "Order".user_ordering_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('"Order"')
            and   type = 'U')
   drop table "Order"
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Passenger')
            and   name  = 'passengers_FK'
            and   indid > 0
            and   indid < 255)
   drop index Passenger.passengers_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Passenger')
            and   type = 'U')
   drop table Passenger
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Plane')
            and   type = 'U')
   drop table Plane
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Role')
            and   type = 'U')
   drop table Role
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('"User"')
            and   name  = 'user_role_FK'
            and   indid > 0
            and   indid < 255)
   drop index "User".user_role_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('"User"')
            and   type = 'U')
   drop table "User"
go

/*==============================================================*/
/* Table: Flight                                                */
/*==============================================================*/
create table Flight (
   flightID             int                  not null identity,
   fromLocationID       int                  not null,
   planeID              int                  not null,
   toLocationID         int                  not null,
   date                 datetime             not null,
   discount             float                null,
   price                float                not null,
   constraint PK_FLIGHT primary key (flightID)
)
go

/*==============================================================*/
/* Index: from_FK                                               */
/*==============================================================*/




create nonclustered index from_FK on Flight (fromLocationID ASC)
go

/*==============================================================*/
/* Index: airplane_used_FK                                      */
/*==============================================================*/




create nonclustered index airplane_used_FK on Flight (planeID ASC)
go

/*==============================================================*/
/* Index: to_FK                                                 */
/*==============================================================*/




create nonclustered index to_FK on Flight (toLocationID ASC)
go

/*==============================================================*/
/* Table: Location                                              */
/*==============================================================*/
create table Location (
   locationID           int                  not null identity,
   name                 varchar(150)         not null,
   constraint PK_LOCATION primary key (locationID)
)
go

/*==============================================================*/
/* Table: "Order"                                               */
/*==============================================================*/
create table "Order" (
   orderID              int                  not null identity,
   userID               int                  not null,
   initialFlightID      int                  not null,
   returnFlightID       int                  null,
   constraint PK_ORDER primary key (orderID)
)
go

/*==============================================================*/
/* Index: user_ordering_FK                                      */
/*==============================================================*/




create nonclustered index user_ordering_FK on "Order" (userID ASC)
go

/*==============================================================*/
/* Index: initial_flight_FK                                     */
/*==============================================================*/




create nonclustered index initial_flight_FK on "Order" (initialFlightID ASC)
go

/*==============================================================*/
/* Index: return_flight_FK                                      */
/*==============================================================*/




create nonclustered index return_flight_FK on "Order" (returnFlightID ASC)
go

/*==============================================================*/
/* Table: Passenger                                             */
/*==============================================================*/
create table Passenger (
   passengerID          int                  not null identity,
   orderID              int                  null,
   name                 varchar(150)         null,
   surname              varchar(50)          null,
   gender               varchar(10)          null,
   age                  int                  null,
   constraint PK_PASSENGER primary key (passengerID)
)
go

/*==============================================================*/
/* Index: passengers_FK                                         */
/*==============================================================*/




create nonclustered index passengers_FK on Passenger (orderID ASC)
go

/*==============================================================*/
/* Table: Plane                                                 */
/*==============================================================*/
create table Plane (
   planeID              int                  not null identity,
   name                 varchar(150)         not null,
   company              varchar(50)          not null,
   constraint PK_PLANE primary key (planeID)
)
go

/*==============================================================*/
/* Table: Role                                                  */
/*==============================================================*/
create table Role (
   roleID               int                  not null identity,
   description          varchar(30)          null,
   constraint PK_ROLE primary key (roleID)
)
go

/*==============================================================*/
/* Table: "User"                                                */
/*==============================================================*/
create table "User" (
   userID               int                  not null identity,
   roleID               int                  not null,
   googleID             varchar(30)          not null,
   name                 varchar(150)         not null,
   email                varchar(255)         not null,
   constraint PK_USER primary key (userID)
)
go

/*==============================================================*/
/* Index: user_role_FK                                          */
/*==============================================================*/




create nonclustered index user_role_FK on "User" (roleID ASC)
go

alter table Flight
   add constraint FK_FLIGHT_AIRPLANE__PLANE foreign key (planeID)
      references Plane (planeID)
go

alter table Flight
   add constraint FK_FLIGHT_FROM_LOCATION foreign key (fromLocationID)
      references Location (locationID)
go

alter table Flight
   add constraint FK_FLIGHT_TO_LOCATION foreign key (toLocationID)
      references Location (locationID)
go

alter table "Order"
   add constraint FK_ORDER_INITIAL_F_FLIGHT foreign key (initialFlightID)
      references Flight (flightID)
go

alter table "Order"
   add constraint FK_ORDER_RETURN_FL_FLIGHT foreign key (returnFlightID)
      references Flight (flightID)
go

alter table "Order"
   add constraint FK_ORDER_USER_ORDE_USER foreign key (userID)
      references "User" (userID)
go

alter table Passenger
   add constraint FK_PASSENGE_PASSENGER_ORDER foreign key (orderID)
      references "Order" (orderID)
go

alter table "User"
   add constraint FK_USER_USER_ROLE_ROLE foreign key (roleID)
      references Role (roleID)
go

