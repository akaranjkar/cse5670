insert into employee(role,dob,age,lastname,firstname,address,phone,salary,specialization)
  values (1,'1980-07-21', 32,'Dsouza','Moses','298 Topi st', '+1(321)616-2928','100000','anesthesiologist');

insert into employee(role,dob,age,lastname,firstname,address,phone,salary,specialization)
  values (1,'1985-11-01',42,'Elliot','James','227 Query st','+1(381)111-2928','200000','surgeon');

insert into employee(role,dob,age,lastname,firstname,address,phone,salary,specialization)
  values (1,'1975-03-29', 46,'Bourne','Jason','876 Proxy st','+1(987)616-9999','150000','physician');

insert into employee(role,dob,age,lastname,firstname,address,phone,salary,specialization)
  values (3,'1993-01-21',24,'Cyrus','Miley','112 Hack st','+1(535)000-9999','80000','');

insert into employee(role,dob,age,lastname,firstname,address,phone,salary,specialization)
  values (2,'1990-02-05',18,'Clone','Clone','876 Mary st','+1(767)101-1234','50000','');

insert into employee(role,dob,age,lastname,firstname,address,phone,salary,specialization)
  values (0,'1972-02-05',38,'Smith','Will','927 MIB st','+1(876)101-9876','90000','');

insert into login values (100000, 'moses',123456);
insert into login values (100001, 'james',123456);
insert into login values (100002, 'jason',123456);
insert into login values (100003, 'miley',123456);
insert into login values (100004, 'clone',123456);
insert into login values (100005, 'will',123456);

insert into policy(provider,startdate,enddate) values ('ABC', '2017-01-01','2018-01-01');
insert into policy(provider,startdate,enddate) values ('XYZ', '2017-06-01','2018-06-01');
insert into policy(provider,startdate,enddate) values ('MUYB', '2017-09-15','2018-09-15');
insert into policy(provider,startdate,enddate) values ('XYZ', '2017-07-01','2018-07-01');

insert into patient(dob,age,lastname,firstname,address,phone,policyID) values
('1980-07-21', 32,'Hobbs','Matt','111 Time st','+1(321)616-2928',20170002);
insert into patient(dob,age,lastname,firstname,address,phone,policyID) values
('1970-11-01', 42,'Jobs','Steve','Mary star st','+1(321)616-2928',20170001);
insert into patient(dob,age,lastname,firstname,address,phone,policyID) values
('1990-07-12', 25,'Place','Lovely','625 Stark st','+1(321)616-2928',20170003);
insert into patient(dob,age,lastname,firstname,address,phone,policyID) values
('1991-06-17', 24,'','Precious','928 Youre st','+1(321)121-2112',20170000);



insert into condition_ (patientID,maindiagnosis,closed) values(1000001,'Flu',true);
insert into condition_ (patientID,maindiagnosis,closed) values(1000003,'Flu',false);
insert into condition_ (patientID,maindiagnosis,closed) values(1000002,'Fracture',true);


insert into sessions(conditionID,visitdate,assessment,doctorID,nurseID,symptoms,diagnosis,recommendation,height,weight,bodytemp,bphigh,bplow,pulse)
   values(10000, '2017-06-17','Patient has flu', 100000,100004,'inflammation','flu', 'tablets',123,120,102,120,70,76);

insert into sessions(conditionID,visitdate,assessment,doctorID,nurseID,symptoms,diagnosis,recommendation,height,weight,bodytemp,bphigh,bplow,pulse)
   values(10001, '2017-08-21','Patient has flu', 100001,100004,'inflammation','flu', 'tablets',101,90,104,124,87,83);

insert into sessions(conditionID,visitdate,assessment,doctorID,nurseID,symptoms,diagnosis,recommendation,height,weight,bodytemp,bphigh,bplow,pulse)
   values(10000, '2017-07-02','Patient has flu', 100000,100004,'inflammation','flu', 'injection',123,111,101,110,90,82);

insert into sessions(conditionID,visitdate,assessment,doctorID,nurseID,symptoms,diagnosis,recommendation,height,weight,bodytemp,bphigh,bplow,pulse)
   values(10002, '2017-11-11','Twisted ankle', 100002,100004,'Swelling','swelling', 'tablets',101,90,104,121,83,83);

insert into sessions(conditionID,visitdate,assessment,doctorID,nurseID,symptoms,diagnosis,recommendation,height,weight,bodytemp,bphigh,bplow,pulse)
   values(10002, '2017-11-22','Twisted ankle', 100002,100004,'Swelling reduced','swelling', 'tablets',123,111,101,117,82,82);



    
    