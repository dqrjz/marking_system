create database marking_system;
use marking_system;

create table document (
	did INT PRIMARY KEY,
    filename_xml VARCHAR(30) not null,
    filename_xls varchar(30) not null,
    document_assignment_status char(1) not null, -- N=unassigned, Y=assigned
    user_id int not null, -- -1=unassigned
    document_marking_status char(1) not null, -- N=unmarked, Y=marked
    case_name varchar(50),
    case_number varchar(30),
    case_reason varchar(50),
    case_category varchar(30)
);

create table fact (
	fid INT PRIMARY KEY,
    content VARCHAR(2000),
    document_id int not null,
    fact_marking_status char(1) not null -- N=unmarked, Y=marked
);

create table law (
	lid INT PRIMARY KEY,
    name varchar(500),
    content VARCHAR(2000),
    document_id int not null
);

create table evidence (
	eid INT PRIMARY KEY,
    content VARCHAR(5000),
    document_id int not null
);

create table mark (
	mid INT PRIMARY KEY,
    value int not null, -- -1=unmarked, 0=unrelated, 1=related
	fact_id int not null,
    evidence_id int not null,
    document_id int not null,
    row_index int not null,
    col_index int not null
);

create table user (
	uid INT PRIMARY KEY,
    username varchar(20) not null,
    password varchar(20) not null,
    privilege varchar(10) not null -- admin, standard
);

insert into user values(0,"admin","123","admin");
insert into user values(1,"user1","123","standard");
insert into user values(2,"user2","123","standard");
insert into user values(3,"user3","123","standard");
insert into user values(4,"user4","123","standard");
insert into user values(5,"user5","123","standard");
insert into user values(6,"user6","123","standard");
insert into user values(7,"user7","123","standard");
insert into user values(8,"user8","123","standard");
insert into user values(9,"user9","123","standard");
insert into user values(10,"user10","123","standard");
insert into user values(11,"user11","123","standard");
insert into user values(12,"user12","123","standard");
insert into user values(13,"user13","123","standard");
insert into user values(14,"user14","123","standard");
insert into user values(15,"user15","123","standard");

select * from document;
select * from fact;
select * from law;
select * from evidence;
select * from mark;
select * from user;


-- 表格初始化
truncate document;
truncate fact;
truncate law;
truncate evidence;
truncate mark;

truncate user;

-- drop表
drop table mark;
drop table fact;
drop table law;
drop table evidence;
drop table document;

drop table user;

select *
from fact f
  inner join mark m on f.fid = m.fact_id
where f.document_id = 0