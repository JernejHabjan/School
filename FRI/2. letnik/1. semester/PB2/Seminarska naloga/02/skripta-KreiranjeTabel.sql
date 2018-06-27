/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     5. 01. 2017 22:36:15                         */
/*==============================================================*/


alter table CISCENJE_PREBIVALISCA
   drop constraint ciscenje_v;

alter table CISCENJE_PREBIVALISCA
   drop constraint je_ocistil;

alter table GOVORI
   drop constraint osebje_govori;

alter table GOVORI
   drop constraint jezik_govori;

alter table HRANJENJE
   drop constraint oseba_hrani;

alter table HRANJENJE
   drop constraint vzame_iz_zaloge;

alter table HRANJENJE
   drop constraint se_prehranjuje_z;

alter table OGLED
   drop constraint ogled_vodi;

alter table OGLED
   drop constraint voden_v;

alter table OGLEDAM_SI
   drop constraint ogledam_si_vrsto;

alter table OGLEDAM_SI
   drop constraint ogledam_si_ogled;

alter table PREBIVALISCE
   drop constraint osebje_oskrbuje;

alter table VSTOPNICA
   drop constraint izda_vstopnico;

alter table VSTOPNICA
   drop constraint omogoca;

alter table VZOREC
   drop constraint vzorec_vzame_oseba;

alter table VZOREC
   drop constraint vzorec_odvzame_zivali;

alter table ZALOGA_HRANE
   drop constraint zaloga_v;

alter table ZALOGA_NAROCILO
   drop constraint zaloga_hrane_narocilo;

alter table ZALOGA_NAROCILO
   drop constraint narocilo_zaloga_hrane;

alter table ZIVAL
   drop constraint mati_zivali;

alter table ZIVAL
   drop constraint oce_zivali;

alter table ZIVAL
   drop constraint prebiva_v;

alter table ZIVAL
   drop constraint pripada;

drop index "Izveden ciscenje_FK";

drop index "Je ocistil_FK";

drop table CISCENJE_PREBIVALISCA cascade constraints;

drop index Goviri2_FK;

drop index Goviri_FK;

drop table GOVORI cascade constraints;

drop index "Oseba hrani_FK";

drop index "Vzame iz zaloge_FK";

drop index "se prehranjuje z_FK";

drop table HRANJENJE cascade constraints;

drop table JEZIKI cascade constraints;

drop table NAROCILO_HRANE cascade constraints;

drop index "se vodi v izbranem jeziku_FK";

drop index "Ogled vodi oseba / vodic_FK";

drop table OGLED cascade constraints;

drop index "Ogledami si2_FK";

drop index "Ogledami si_FK";

drop table OGLEDAM_SI cascade constraints;

drop table OSEBJE cascade constraints;

drop index "Oskrbuje / je zadolžen_FK";

drop table PREBIVALISCE cascade constraints;

drop table SKLADISCE cascade constraints;

drop index "Izda vstopnico_FK";

drop index Omogoca_FK;

drop table VSTOPNICA cascade constraints;

drop index "vzorec odvzame oseba_FK";

drop index "vzorec odvzame zivali_FK";

drop table VZOREC cascade constraints;

drop index "zaloga se nahaja v_FK";

drop table ZALOGA_HRANE cascade constraints;

drop index "narocilo hrane2_FK";

drop index "narocilo hrane_FK";

drop table ZALOGA_NAROCILO cascade constraints;

drop index Mati_FK;

drop index "prebiv v / vsebije zival_FK";

drop index pripada_FK;

drop index Oce_FK;

drop table ZIVAL cascade constraints;

drop table ZIVALSKA_VRSTA cascade constraints;

/*==============================================================*/
/* Table: CISCENJE_PREBIVALISCA                                 */
/*==============================================================*/
create table CISCENJE_PREBIVALISCA  (
   EMSO                 INTEGER                         not null,
   Ime_prebivalisca     VARCHAR(100)                    not null,
   datum                DATE                            not null,
   Komentar             VARCHAR(200),
   constraint PK_CISCENJE_PREBIVALISCA primary key (EMSO, Ime_prebivalisca, datum)
);

/*==============================================================*/
/* Index: "Je ocistil_FK"                                       */
/*==============================================================*/
create index "Je ocistil_FK" on CISCENJE_PREBIVALISCA (
   EMSO ASC
);

/*==============================================================*/
/* Index: "Izveden ciscenje_FK"                                 */
/*==============================================================*/
create index "Izveden ciscenje_FK" on CISCENJE_PREBIVALISCA (
   Ime_prebivalisca ASC
);

/*==============================================================*/
/* Table: GOVORI                                                */
/*==============================================================*/
create table GOVORI  (
   EMSO                 INTEGER                         not null,
   Naziv_jezika         VARCHAR2(100)                   not null,
   constraint PK_GOVORI primary key (EMSO, Naziv_jezika)
);

/*==============================================================*/
/* Index: Goviri_FK                                             */
/*==============================================================*/
create index Goviri_FK on GOVORI (
   EMSO ASC
);

/*==============================================================*/
/* Index: Goviri2_FK                                            */
/*==============================================================*/
create index Goviri2_FK on GOVORI (
   Naziv_jezika ASC
);

/*==============================================================*/
/* Table: HRANJENJE                                             */
/*==============================================================*/
create table HRANJENJE  (
   Ime_vrste            CHAR(20)                        not null,
   Naziv_hrane          CHAR(20)                        not null,
   EMSO                 INTEGER                         not null,
   Datum                DATE                            not null,
   Stevilka_obroka      INTEGER                         not null,
   kolicina             NUMBER                          not null,
   constraint PK_HRANJENJE primary key (Ime_vrste, Naziv_hrane, EMSO, Datum, Stevilka_obroka)
);

/*==============================================================*/
/* Index: "se prehranjuje z_FK"                                 */
/*==============================================================*/
create index "se prehranjuje z_FK" on HRANJENJE (
   Ime_vrste ASC
);

/*==============================================================*/
/* Index: "Vzame iz zaloge_FK"                                  */
/*==============================================================*/
create index "Vzame iz zaloge_FK" on HRANJENJE (
   Naziv_hrane ASC
);

/*==============================================================*/
/* Index: "Oseba hrani_FK"                                      */
/*==============================================================*/
create index "Oseba hrani_FK" on HRANJENJE (
   EMSO ASC
);

/*==============================================================*/
/* Table: JEZIKI                                                */
/*==============================================================*/
create table JEZIKI  (
   Naziv_jezika         VARCHAR(100)                    not null,
   constraint PK_JEZIKI primary key (Naziv_jezika)
);

/*==============================================================*/
/* Table: NAROCILO_HRANE                                        */
/*==============================================================*/
create table NAROCILO_HRANE  (
   st_narocila          INTEGER                         not null,
   kolicina             NUMBER                          not null,
   datum_narocila       DATE                            not null,
   datum_dobave         DATE,
   Cena                 NUMBER                          not null,
   Dobavitelj           CHAR(30)                        not null,
   constraint PK_NAROCILO_HRANE primary key (st_narocila)
);

/*==============================================================*/
/* Table: OGLED                                                 */
/*==============================================================*/
create table OGLED  (
   Id_ogleda            INTEGER                         not null,
   Naziv_jezika         VARCHAR(100)                    not null,
   EMSO                 INTEGER                         not null,
   datum_ogleda         DATE                            not null,
   zacetek              DATE                            not null,
   konec                DATE,
   constraint PK_OGLED primary key (Id_ogleda)
);

/*==============================================================*/
/* Index: "Ogled vodi oseba / vodic_FK"                         */
/*==============================================================*/
create index "Ogled vodi oseba / vodic_FK" on OGLED (
   EMSO ASC
);

/*==============================================================*/
/* Index: "se vodi v izbranem jeziku_FK"                        */
/*==============================================================*/
create index "se vodi v izbranem jeziku_FK" on OGLED (
   Naziv_jezika ASC
);

/*==============================================================*/
/* Table: OGLEDAM_SI                                            */
/*==============================================================*/
create table OGLEDAM_SI  (
   Ime_vrste            CHAR(20)                        not null,
   Id_ogleda            INTEGER                         not null,
   constraint PK_OGLEDAM_SI primary key (Ime_vrste, Id_ogleda)
);

/*==============================================================*/
/* Index: "Ogledami si_FK"                                      */
/*==============================================================*/
create index "Ogledami si_FK" on OGLEDAM_SI (
   Ime_vrste ASC
);

/*==============================================================*/
/* Index: "Ogledami si2_FK"                                     */
/*==============================================================*/
create index "Ogledami si2_FK" on OGLEDAM_SI (
   Id_ogleda ASC
);

/*==============================================================*/
/* Table: OSEBJE                                                */
/*==============================================================*/
create table OSEBJE  (
   EMSO                 INTEGER                         not null,
   Ime                  VARCHAR(10)                     not null,
   Priimek              VARCHAR(10)                     not null,
   spol                 CHAR(1)                        
      constraint CKC_SPOL_OSEBJE check (spol is null or (spol in ('m','z'))),
   Tel_st               INTEGER                         not null,
   placa                NUMBER                          not null,
   naslov               VARCHAR(100)                    not null,
   tip_osebja           VARCHAR(10)                     not null,
   constraint PK_OSEBJE primary key (EMSO)
);

/*==============================================================*/
/* Table: PREBIVALISCE                                          */
/*==============================================================*/
create table PREBIVALISCE  (
   Ime_prebivalisca     VARCHAR(100)                    not null,
   EMSO                 INTEGER                         not null,
   dimenzija            VARCHAR(10),
   lokacija_prebivalisca VARCHAR(30)                     not null,
   constraint PK_PREBIVALISCE primary key (Ime_prebivalisca)
);

/*==============================================================*/
/* Index: "Oskrbuje / je zadolžen_FK"                           */
/*==============================================================*/
create index "Oskrbuje / je zadolžen_FK" on PREBIVALISCE (
   EMSO ASC
);

/*==============================================================*/
/* Table: SKLADISCE                                             */
/*==============================================================*/
create table SKLADISCE  (
   Ime_skaldisca        VARCHAR(200)                    not null,
   constraint PK_SKLADISCE primary key (Ime_skaldisca)
);

/*==============================================================*/
/* Table: VSTOPNICA                                             */
/*==============================================================*/
create table VSTOPNICA  (
   IDV                  INTEGER                         not null,
   Id_ogleda            INTEGER                         not null,
   EMSO                 INTEGER                         not null,
   Datum                DATE                            not null,
   Cena                 NUMBER                          not null,
   constraint PK_VSTOPNICA primary key (IDV)
);

/*==============================================================*/
/* Index: Omogoca_FK                                            */
/*==============================================================*/
create index Omogoca_FK on VSTOPNICA (
   Id_ogleda ASC
);

/*==============================================================*/
/* Index: "Izda vstopnico_FK"                                   */
/*==============================================================*/
create index "Izda vstopnico_FK" on VSTOPNICA (
   EMSO ASC
);

/*==============================================================*/
/* Table: VZOREC                                                */
/*==============================================================*/
create table VZOREC  (
   Id_zivali            NUMBER                          not null,
   EMSO                 INTEGER                         not null,
   Datum_vzorca         DATE                            not null,
   Komentar             VARCHAR(200),
   constraint PK_VZOREC primary key (Id_zivali, EMSO, Datum_vzorca)
);

/*==============================================================*/
/* Index: "vzorec odvzame zivali_FK"                            */
/*==============================================================*/
create index "vzorec odvzame zivali_FK" on VZOREC (
   Id_zivali ASC
);

/*==============================================================*/
/* Index: "vzorec odvzame oseba_FK"                             */
/*==============================================================*/
create index "vzorec odvzame oseba_FK" on VZOREC (
   EMSO ASC
);

/*==============================================================*/
/* Table: ZALOGA_HRANE                                          */
/*==============================================================*/
create table ZALOGA_HRANE  (
   Naziv_hrane          CHAR(20)                        not null,
   Ime_skaldisca        VARCHAR(200)                    not null,
   Zaloga               NUMBER(30)                      not null,
   constraint PK_ZALOGA_HRANE primary key (Naziv_hrane)
);

/*==============================================================*/
/* Index: "zaloga se nahaja v_FK"                               */
/*==============================================================*/
create index "zaloga se nahaja v_FK" on ZALOGA_HRANE (
   Ime_skaldisca ASC
);

/*==============================================================*/
/* Table: ZALOGA_NAROCILO                                       */
/*==============================================================*/
create table ZALOGA_NAROCILO  (
   Naziv_hrane          CHAR(20)                        not null,
   st_narocila          INTEGER                         not null,
   constraint PK_ZALOGA_NAROCILO primary key (Naziv_hrane, st_narocila)
);

/*==============================================================*/
/* Index: "narocilo hrane_FK"                                   */
/*==============================================================*/
create index "narocilo hrane_FK" on ZALOGA_NAROCILO (
   Naziv_hrane ASC
);

/*==============================================================*/
/* Index: "narocilo hrane2_FK"                                  */
/*==============================================================*/
create index "narocilo hrane2_FK" on ZALOGA_NAROCILO (
   st_narocila ASC
);

/*==============================================================*/
/* Table: ZIVAL                                                 */
/*==============================================================*/
create table ZIVAL  (
   Id_zivali            NUMBER                          not null,
   Id_oceta             NUMBER,
   Ime_prebivalisca     VARCHAR(100)                    not null,
   Id_matere            NUMBER,
   Ime_vrste            CHAR(20)                        not null,
   Ime                  CHAR(10),
   spol                 CHAR(1)                        
      constraint CKC_SPOL_ZIVAL check (spol is null or (spol in ('m','z'))),
   datum_rojstva        DATE,
   teza                 NUMBER,
   stanje               VARCHAR(100)                    not null,
   datum_prihoda        DATE                            not null,
   constraint PK_ZIVAL primary key (Id_zivali)
);

/*==============================================================*/
/* Index: Oce_FK                                                */
/*==============================================================*/
create index Oce_FK on ZIVAL (
   Id_oceta ASC
);

/*==============================================================*/
/* Index: pripada_FK                                            */
/*==============================================================*/
create index pripada_FK on ZIVAL (
   Ime_vrste ASC
);

/*==============================================================*/
/* Index: "prebiv v / vsebije zival_FK"                         */
/*==============================================================*/
create index "prebiv v / vsebije zival_FK" on ZIVAL (
   Ime_prebivalisca ASC
);

/*==============================================================*/
/* Index: Mati_FK                                               */
/*==============================================================*/
create index Mati_FK on ZIVAL (
   Id_matere ASC
);

/*==============================================================*/
/* Table: ZIVALSKA_VRSTA                                        */
/*==============================================================*/
create table ZIVALSKA_VRSTA  (
   Ime_vrste            CHAR(20)                        not null,
   dnevna_kolic_hrane   CHAR(256),
   zivljenska_doba      NUMBER                          not null,
   prvo_parjenje        NUMBER                          not null,
   cas_zanositve        NUMBER                          not null,
   constraint PK_ZIVALSKA_VRSTA primary key (Ime_vrste)
);

alter table CISCENJE_PREBIVALISCA
   add constraint ciscenje_v foreign key (Ime_prebivalisca)
      references PREBIVALISCE (Ime_prebivalisca);

alter table CISCENJE_PREBIVALISCA
   add constraint je_ocistil foreign key (EMSO)
      references OSEBJE (EMSO);

alter table GOVORI
   add constraint osebje_govori foreign key (EMSO)
      references OSEBJE (EMSO);

alter table GOVORI
   add constraint jezik_govori foreign key (Naziv_jezika)
      references JEZIKI (Naziv_jezika);

alter table HRANJENJE
   add constraint oseba_hrani foreign key (EMSO)
      references OSEBJE (EMSO);

alter table HRANJENJE
   add constraint vzame_iz_zaloge foreign key (Naziv_hrane)
      references ZALOGA_HRANE (Naziv_hrane);

alter table HRANJENJE
   add constraint se_prehranjuje_z foreign key (Ime_vrste)
      references ZIVALSKA_VRSTA (Ime_vrste);

alter table OGLED
   add constraint ogled_vodi foreign key (EMSO)
      references OSEBJE (EMSO);

alter table OGLED
   add constraint voden_v foreign key (Naziv_jezika)
      references JEZIKI (Naziv_jezika);

alter table OGLEDAM_SI
   add constraint ogledam_si_vrsto foreign key (Ime_vrste)
      references ZIVALSKA_VRSTA (Ime_vrste);

alter table OGLEDAM_SI
   add constraint ogledam_si_ogled foreign key (Id_ogleda)
      references OGLED (Id_ogleda);

alter table PREBIVALISCE
   add constraint osebje_oskrbuje foreign key (EMSO)
      references OSEBJE (EMSO);

alter table VSTOPNICA
   add constraint izda_vstopnico foreign key (EMSO)
      references OSEBJE (EMSO);

alter table VSTOPNICA
   add constraint omogoca foreign key (Id_ogleda)
      references OGLED (Id_ogleda);

alter table VZOREC
   add constraint vzorec_vzame_oseba foreign key (EMSO)
      references OSEBJE (EMSO);

alter table VZOREC
   add constraint vzorec_odvzame_zivali foreign key (Id_zivali)
      references ZIVAL (Id_zivali);

alter table ZALOGA_HRANE
   add constraint zaloga_v foreign key (Ime_skaldisca)
      references SKLADISCE (Ime_skaldisca);

alter table ZALOGA_NAROCILO
   add constraint zaloga_hrane_narocilo foreign key (Naziv_hrane)
      references ZALOGA_HRANE (Naziv_hrane);

alter table ZALOGA_NAROCILO
   add constraint narocilo_zaloga_hrane foreign key (st_narocila)
      references NAROCILO_HRANE (st_narocila);

alter table ZIVAL
   add constraint mati_zivali foreign key (Id_matere)
      references ZIVAL (Id_zivali);

alter table ZIVAL
   add constraint oce_zivali foreign key (Id_oceta)
      references ZIVAL (Id_zivali);

alter table ZIVAL
   add constraint prebiva_v foreign key (Ime_prebivalisca)
      references PREBIVALISCE (Ime_prebivalisca);

alter table ZIVAL
   add constraint pripada foreign key (Ime_vrste)
      references ZIVALSKA_VRSTA (Ime_vrste);

