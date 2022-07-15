create database Project;
use Project;
 -- drop database project;
create table Drivers (
DID INT PRIMARY KEY AUTO_INCREMENT,
PID INT,
Dname VARCHAR(45) not null,
Driving_license INT ,
Phone_number INT ,
Email VARCHAR(45),
Cars_Driving_license INT,
expiry_date date,
DPassword VARCHAR(45));

create table student (
  SID INT PRIMARY KEY AUTO_INCREMENT not null,
  PID INT not null,
  Sname VARCHAR(45) not null,
  Phone_number INT not null,
  Email VARCHAR(45),
  Major VARCHAR(45),
  Address VARCHAR(45) not null,
  Gender VARCHAR(45),
  Still_study boolean);
  
create table Cars (
  Driving_license INT PRIMARY KEY AUTO_INCREMENT not null,
  Ctype VARCHAR(45) not null,
  Drivers_DID INT,
  Color VARCHAR(45) not null,
  Number_Of_Passenger INT not null,
  Insurance VARCHAR(45) not null,
  expiry_date date,
  foreign key (Drivers_DID) references Drivers (DID) ON DELETE CASCADE ON UPDATE CASCADE);

ALTER TABLE Drivers
ADD FOREIGN KEY (Cars_Driving_license) REFERENCES Cars(Driving_license) ON DELETE CASCADE on update cascade;
  
create table Routes (
  RID INT AUTO_INCREMENT not null,
  Distance INT not null,
  RName VARCHAR(45) ,
  Details VARCHAR(45) ,
  Safety boolean,
  Rtime int,
  PRIMARY KEY (RID));
 
 create table Transportation_time (
  TID INT not null,
  Thour INT not null,
  Tday VARCHAR(45) not null,
  Semester INT not null,
  Tyear int,
  primary key (TID, Thour, Tday, Semester, Tyear));

create table Responsible (
Drivers_DID INT,
RID INT,
Transportation_time_TID INT,
foreign key (Drivers_DID) references Drivers (DID) ON DELETE CASCADE on update cascade,
foreign key (Transportation_time_TID) references Transportation_time (TID) ON DELETE CASCADE on update cascade,
foreign key (RID) references Routes (RID) ON DELETE CASCADE on update cascade,
primary key (Drivers_DID, Transportation_time_TID)
);
  
create table Register(
Student_SID INT,
Transportation_time_TID INT,
foreign key (Student_SID) references student (SID) ON DELETE CASCADE on update cascade,
foreign key (Transportation_time_TID) references Transportation_time (TID) ON DELETE CASCADE on update cascade,
primary key(Student_SID, Transportation_time_TID)
); 

SET FOREIGN_KEY_CHECKS=0;

INSERT INTO drivers  VALUES (1,258852369,'Majdy Musbah',102030,598536442,'majdy@gmail.com',302010,'2022-07-05','1234');
INSERT INTO drivers VALUES (2,147785214,'Qasim Shawakha',203040,598232101,'qasim@gmail.com',403020,'2022-07-05','1234');
INSERT INTO drivers VALUES (3,278912245,'Hasan Shawakha',304050,599878520,'hasan@gmail.com',504030,'2023-07-05','1234');
INSERT INTO cars  VALUES (1,'Truck',1,'Red',7,'abcd','2022-07-05');
INSERT INTO cars  VALUES (2,'Truck',2,'Blue',7,'aavv','2022-07-05');
INSERT INTO cars  VALUES (3,'Car',3,'Green',7,'mcc','2022-07-05');
INSERT INTO student VALUES (1,407246651,'Maysam Khatib',562300959,'maysam@gmail.com','COMP','Al-Balad','Female',1);
INSERT INTO student VALUES (2,40258852,'Danya Kahla',592877232,'dan@gmail.com','CULS','Mazrouqa','Female',1);
INSERT INTO student VALUES (3,468886221,'Reem Kahla',598567854,'roro@gmail.com','CIVIL','Mazrouqa','Female',1);
INSERT INTO student VALUES (4,9022653,'Assalah Yousef',597465745,'assalah@yahoo.com','COMP','Al-Balad','Female',1);
INSERT INTO student VALUES (5,30588321,'Fatima Taher',595866521,'fatima@gmail.com','COMP','al-Jabal','Female',0);
INSERT INTO student VALUES (6,2033124,'Hatem Taher',52366987,'hat@gmail.com','ENG','Al-Risan','Male',1);
INSERT INTO student VALUES (7,789654123,'Amro Raed',56322147,'amoor@gmail.com','COMP','Al-Jabal','Male',1);
INSERT INTO transportation_time VALUES (7,7,'Saturday',2,2022);
INSERT INTO transportation_time VALUES (9,9,'Saturday',2,2022);
INSERT INTO transportation_time VALUES (27,7,'Monday',2,2022);
INSERT INTO transportation_time VALUES (28,8,'Monday',2,2022);
INSERT INTO transportation_time VALUES (29,9,'Monday',2,2022);
INSERT INTO transportation_time VALUES (37,7,'Tuesday',2,2022);
INSERT INTO transportation_time VALUES (38,8,'Tuesday',2,2022);
select * from routes;