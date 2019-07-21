delete from hibernate_specialities;
delete from hibernate_developers;

drop table HIBERNATE_KNOWLEDGE;
drop table HIBERNATE_PROJECTS;
drop table hibernate_developers;
drop table hibernate_specialities;
drop table hibernate_companies;


select * from HIBERNATE_KNOWLEDGE;
select * from HIBERNATE_PROJECTS;
select * from hibernate_specialities;
select * from hibernate_developers;

create table HIBERNATE_SPECIALITIES(
ID serial primary key,
SPECIALITY VARCHAR(50) unique
);

create table HIBERNATE_COMPANIES(
ID serial primary key,
COMPANY_NAME VARCHAR(50) unique
);


create table HIBERNATE_DEVELOPERS(
   ID serial primary key,
   FIRST_NAME VARCHAR(50) DEFAULT NULL,
   LAST_NAME VARCHAR(50) DEFAULT NULL,
   SPECIALTY int not null references HIBERNATE_SPECIALITIES(ID) ON DELETE CASCADE,
   COMPANY INT NOT NULL,
   EXPERIENCE INT DEFAULT NULL
);

create table HIBERNATE_PROJECTS(
ID serial primary key,
PROJECT_NAME varchar(50),
developerID int null references HIBERNATE_DEVELOPERS(ID) ON DELETE CASCADE
);

create table HIBERNATE_KNOWLEDGE(
ID serial primary key,
KNOWLEDGE_NAME varchar(50),
comment varchar(50),
developerID int null references HIBERNATE_DEVELOPERS(ID) ON DELETE CASCADE
);
