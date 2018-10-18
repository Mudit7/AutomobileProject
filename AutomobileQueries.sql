-----insurance----
create table insurancem(vehicleNo varchar2(20),service varchar(30),amount Number);
drop table insurancem;
insert into insurancem values('13','polishing',3000);
insert into insurancem values('12','wheelBalancing',100);
insert into insurancem values('23','decors',300);
insert into insurancem values('12','polishing',200);
select * from insurancem;
  
----employee database-------------
create table empm(empId varchar2(10),empName varchar2(20),empPass VARCHAR2(20)); 
drop table empm;
insert into empm values(seqm_empid.nextval,'Mudit','Malpani');
commit;
select * from empm;
select * from empm where empId='1';

select empName from empm where empId='1';
select amount from insurancem where vehicleNo='12345' and service='polishing';
drop SEQUENCE seqm_empid;
create sequence seqm_empid
minvalue 1
start with 1
increment by 1;

------------customer stuff-------------------------------------------------------------------
create table customersm(customerId number,customerName varchar(20), mobileNumber varchar(20));
alter table customersm add constraint customersmId_pk primary key(customerId);
alter table customersm add constraint customermName_uc unique(customerName);
select * from customersm;
create sequence seqm_customerId
minvalue 1
start with 1
increment by 1
cache 100;


create table vehiclem(customerId number, vehicleNo varchar(20));
alter table vehiclem add constraint vehiclemId_pk primary key(vehicleNo);
alter table vehiclem drop constraint customermId_fk;
alter table vehiclem add constraint customermId_fk foreign key(customerId) references customersm(customerId) on delete cascade;
select * from vehiclem;



create table vehicleServicesm(serviceId number, vehicleNo varchar(20),serviceName varchar(20));
alter table vehicleServicesm add constraint serviceIdm_pk primary key(serviceId);
alter table vehicleServicesm add constraint vehicleNom_fk foreign key(vehicleNo) references vehiclem(vehicleNo) on delete cascade;
alter table VehicleServicesm add constraint serviceNamem_fk foreign key(serviceName) references servicem(serviceName) on delete cascade;
select * from VehicleServicesm;



create table servicem(serviceName varchar(20), servicePrice number);
alter table servicem add constraint serviceNamem_pk primary key(serviceName);
select * from servicem;
create sequence seqm_serviceId
minvalue 1
start with 1
increment by 1
cache 100;

--------------service and its cost-----------------
insert into servicem values ('polishing',2000);
insert into servicem values ('wheelBalancing',3000);
insert into servicem values ('decors',1000);
commit;



