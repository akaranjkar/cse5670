drop database clinicsys;
create database clinicsys;

use clinicsys;

CREATE TABLE employee (
    employeeID int not null AUTO_INCREMENT,
    role int,
    dob Date,
    age int,
    lastname varchar(25),
    firstname varchar(25),
    address varchar(30),
    phone varchar(17),
    salary double,
    specialization varchar(20) ,
    PRIMARY KEY (employeeID)
)AUTO_INCREMENT=100000;


CREATE TABLE login (
	employeeID int not null,
    username varchar(30) not null,
    passwd varchar(30) not null,
    PRIMARY KEY (employeeID,username),
    FOREIGN KEY (employeeID) REFERENCES employee(employeeID)
    
);



CREATE TABLE policy(
    policyID int not null AUTO_INCREMENT,
    provider varchar(25) not null,
    startdate date,
    enddate date,
        
    PRIMARY KEY (policyID)
    #FOREIGN KEY (policyID) REFERENCES patient(policyID)
    
)AUTO_INCREMENT = 20170000;

    
    



CREATE TABLE patient (
    patientID int not null  AUTO_INCREMENT,
    dob Date,
    age int,
    lastname varchar(25),
    firstname varchar(25),
    address varchar(30),
    phone varchar(17),
    policyID int,
    PRIMARY KEY (patientID),
    FOREIGN KEY (policyID) REFERENCES policy(policyID)
    
) AUTO_INCREMENT= 1000000;


CREATE TABLE condition_ (
    conditionID int not null AUTO_INCREMENT,
    patientID int not null,
    maindiagnosis varchar(25),
    closed boolean,
    
    PRIMARY KEY (conditionID),
    FOREIGN KEY (patientID) REFERENCES patient(patientID)
    
)AUTO_INCREMENT= 10000;



CREATE TABLE sessions (
    sessionID int not null AUTO_INCREMENT,
    conditionID int not null,
    visitdate date,
    assessment varchar(100),
    doctorID int,
    nurseID int,
    
    symptoms varchar(50),
    diagnosis varchar(50), 
    recommendation varchar(50),
    
    height DOUBLE (5,2),
    weight DOUBLE (5,2),
    bodytemp DOUBLE (5,2),
    bphigh int,
    bplow int,
    pulse int,
    
    
    PRIMARY KEY (sessionID,conditionID),
    FOREIGN KEY (conditionID) REFERENCES condition_(conditionID),
);
