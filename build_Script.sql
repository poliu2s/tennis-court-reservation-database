drop table APPUSER cascade constraints;
drop table ACCOUNT_PAYMENT cascade constraints;
drop table CANCEL cascade constraints;
drop table COACH cascade constraints;
drop table COURSE_RESERVE cascade constraints;
drop table COURSE cascade constraints;
drop table COURT_RENTAL cascade constraints;
drop table COURT cascade constraints;
drop table RESERVATION cascade constraints;
drop table USER_TRANSACTIONLIST cascade constraints;
drop table TRANSACTION cascade constraints;

create table AppUser(
	user_id INT, 
	password CHAR(20),
	login_id CHAR(20),
	balance INT check(balance > 0),
	address CHAR(40) check(LENGTH(address) > 5),
	name CHAR(40),
	primary key(user_id),
	UNIQUE(login_id)
);

create table Account_Payment(
	reservation_id INT,
	court_rental_id INT,
	trans_list_id INT,
	primary key(reservation_id,court_rental_id,trans_list_id)
);

create table Cancel(
	cancel_id INT,
	cancel_reason CHAR(40),
	cancel_amount INT,
	cancel_date DATE,
	user_id INT,
	primary key(cancel_id)	
);

create table Coach(
	coach_id INT,
	salary INT,
	trainer_level CHAR(20),
	primary key (coach_id)
);

create table Course_Reserve(
	course_reserve_id INT,
	course_id INT,
	transaction_id INT,
	primary key(course_reserve_id),
	unique (course_id, transaction_id)
);

create table Course(
	course_id INT,
	course_name CHAR(40),
	course_level CHAR(40),
	max_capacity INT,
	current_number INT,
	coach_id INT,
	court_id INT,
	court_rental_id INT,
	primary key (course_id)
);

create table Court_Rental(
	court_rental_id INT,
	start_date DATE,
	end_date DATE,
	for_course CHAR(1),
	is_wait_list CHAR(1),
	court_id INT,
	trans_list_id INT,
	primary key (court_rental_id),
	unique (start_date, end_date, court_id)
	--ON DELETE CASCADE
);

create table Court(
	court_id INT,
	court_number INT,
	court_location CHAR(40),
	primary key (court_id),
	unique (court_number, court_location)
);

create table Reservation(
	reservation_id INT,
	trans_list_id INT,
	court_rental_id INT,
	primary key (reservation_id)
	--ON DELETE CASCADE
);

create table Transaction(
	transaction_amount INT,
	transaction_id INT,
	primary key (transaction_id)
	--ON DELETE CASCADE
);

create table User_TransactionList(
	trans_list_id INT,
	transaction_id INT,
	user_id INT,
	primary key (trans_list_id),
	unique (user_id, transaction_id)
	--ON DELETE CASCADE
);

alter table Account_Payment
	add Constraint reservation_id_pay_ref
		foreign key(reservation_id) references Reservation
		ON DELETE CASCADE;
alter table Account_Payment
	add Constraint court_rental_id_pay_ref
		foreign key(court_rental_id) references Court_Rental
		ON DELETE CASCADE;
alter table Account_Payment
	add Constraint trans_list_id_pay_ref
		foreign key(trans_list_id) references User_TransactionList
		ON DELETE CASCADE;

alter table Cancel
	add constraint user_cancel_ref
		foreign key(user_id) references AppUser
		ON DELETE CASCADE;
		
alter table Coach
	add Constraint user_coach_ref
		foreign key (coach_id) references AppUser(user_id)
		ON DELETE CASCADE;

alter table Course
	add Constraint coach_course_ref
		foreign key(coach_id) references Coach
		ON DELETE CASCADE;
alter table Course
	add Constraint court_course_ref
		foreign key(court_id) references Court
		ON DELETE CASCADE;
alter table Course
	add Constraint court_rental_course_ref
		foreign key(court_rental_id) references Court_Rental
		ON DELETE CASCADE;
			
alter table Course_Reserve
	add Constraint course_id_creserve_ref
		foreign key(course_id) references Course
		ON DELETE CASCADE;
alter table Course_Reserve
	add Constraint transaction_creserve_ref
		foreign key(transaction_id) references Transaction
		ON DELETE CASCADE;
		
alter table Court_Rental
	add Constraint coach_id_crent_ref
		foreign key (court_id) references Court(court_id)
		ON DELETE CASCADE;
alter table Court_Rental
	add Constraint trans_list_id_crent_ref
		foreign key (trans_list_id) references User_TransactionList(trans_list_id)
		ON DELETE CASCADE;

alter table Reservation
	add Constraint account_id_reserv_ref
		foreign key (trans_list_id) references User_TransactionList(trans_list_id)
		ON DELETE CASCADE;
alter table Reservation
	add Constraint court_rental_id_reserv_ref
		foreign key (court_rental_id) references Court_Rental(court_rental_id)
		ON DELETE CASCADE;

alter table User_TransactionList
	add Constraint transaction_hold_ref
		foreign key (transaction_id) references Transaction(transaction_id)
		ON DELETE CASCADE;
alter table User_TransactionList
	add Constraint user_hold_ref
		foreign key (user_id) references AppUser(user_id)
		ON DELETE CASCADE;
		
INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (50, 1001);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1002);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1003);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1004);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1005);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1006);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (50, 1007);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (50, 1008);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (50, 1009);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (50, 1010);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (50, 1011);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (0, 1012);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (0, 1013);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (0, 1014);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (0, 1015);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (0, 1016);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (0, 1017);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (50, 1018);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (50, 1019);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (50, 1020);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (50, 1021);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1022);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1023);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1024);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1025);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1026);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1027);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1028);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1029);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1030);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1031);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1032);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1033);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1034);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1035);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1036);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1037);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1038);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1039);

INSERT INTO Transaction(transaction_amount, transaction_id)
VALUES (25, 1040);

--

INSERT INTO AppUser(user_id, password, login_id, balance,address, name)
VALUES (9999, 123, 'Admin', 25, 'Richmond', 'Administrater');

INSERT INTO AppUser(user_id, password, login_id, balance,address, name)
VALUES (0001, 123, 'a', 25, '1326 W.13th Ave', 'peter');

INSERT INTO AppUser(user_id, password, login_id, balance,address, name)
VALUES (0002, 123, 'ab', 25, '1326 W.13th Ave', 'peter');

INSERT INTO AppUser(user_id, password, login_id, balance,address, name)
VALUES (0003, 123, 'abc', 27, '41 Oak St', 'Andrew');

INSERT INTO AppUser(user_id, password, login_id, balance,address, name)
VALUES (0004, 123, 'anewhope', 28, '546 Ontario St', 'Neil');

INSERT INTO AppUser(user_id, password, login_id, balance,address, name)
VALUES (0005, 123, 'thesunrise', 29, '26 Arbutus St', 'Jennifer');

INSERT INTO AppUser(user_id, password, login_id, balance,address, name)
VALUES (0006, 123, 'suDupAMarIo', 30, '16 WestBroadWay', 'Kathy');

INSERT INTO AppUser(user_id, password, login_id, balance,address, name)
VALUES (7001, 123, 'watchTHEthrone', 25, '123 West 13th Ave','Kisoo');

INSERT INTO AppUser(user_id, password, login_id, balance,address, name)
VALUES (7002, 123, 'arkham123asylum', 30, '43 Marin Dr', 'Akash');

INSERT INTO AppUser(user_id, password, login_id, balance,address, name)
VALUES (7003, 123, 'the12winner12', 25, '1325 West 13th Ave', 'Jung');

INSERT INTO AppUser(user_id, password, login_id, balance,address, name)
VALUES (7004, 123, 'findmeanonion', 20, '13 East 8th Ave', 'Po');

INSERT INTO AppUser(user_id, password, login_id, balance,address, name)
VALUES (0101, 'lester', 'makeawish', 2500, 'The far side', 'Wiley Coyote');

--

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0001, 1001, 0001);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0002, 1002, 0001);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0003, 1003, 0001);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0004, 1004, 0002);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0005, 1005, 0002);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0006, 1006, 0006);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0007, 1007, 0006);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0008, 1008, 7001);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0009, 1009, 7001);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0010, 1010, 7002);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0011, 1011, 7003);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0012, 1012, 9999);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0013, 1013, 9999);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0014, 1014, 9999);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0015, 1015, 9999);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0016, 1016, 9999);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0017, 1017, 9999);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0018, 1022, 0001);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0019, 1023, 0001);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0020, 1024, 0001);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0021, 1025, 0001);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0022, 1018, 0001);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0023, 1019, 0001);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0024, 1020, 0001);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0025, 1021, 0001);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0026, 1026, 0101);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0027, 1027, 0101);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0028, 1028, 0101);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0029, 1029, 0101);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0030, 1030, 0101);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0031, 1031, 0101);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0032, 1032, 0101);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0033, 1033, 0101);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0034, 1034, 0101);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0035, 1035, 0101);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0036, 1036, 0101);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0037, 1037, 0101);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0038, 1038, 0101);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0039, 1039, 0101);

INSERT INTO User_TransactionList(trans_list_id,transaction_id,user_id)
VALUES (0040, 1040, 0101);

--

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4000, 1000, 'Vancouver');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4001, 1001, 'New West Minister');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4002, 1002, 'Coquitlam');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4003, 1003, 'Marine Drive');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4004, 1004, 'North Vancouver');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4005, 1005, 'Richmond');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4006, 1006, 'Burnaby');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4007, 1007, 'Abbotsford');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4008, 1008, 'Langley');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4009, 1009, 'Surrey');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4010, 1010, 'Chiliwack');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4011, 1011, 'Aldergrove');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4012, 1012, 'Port Coquitlam');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4013, 1013, 'White Rock');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4014, 1014, 'Delta');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4015, 1015, 'Vancouver');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4016, 1016, 'Vancouver');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4017, 1017, 'New West Minister');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4018, 1018, 'New West Minister');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4019, 1019, 'Richmond');

INSERT INTO Court(court_id, court_number, court_location)
VALUES(4020, 1020, 'Richmond');


--

INSERT INTO Court_Rental(Court_Rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2016, to_date('2012/3/10 09:00:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/10 11:30:00', 'yyyy/mm/dd hh24:mi:ss'), 'T', 4000, 'F', 0012 );

INSERT INTO Court_Rental(Court_Rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2000, to_date('2012/3/3 09:00:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/3 11:30:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4000, 'F', 0007 );

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2001, to_date('2012/3/5 09:00:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/5 12:30:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4001, 'F', 0003 );

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2017, to_date('2012/3/12 11:00:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/12 14:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'T', 4002, 'F', 0013 );

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2002, to_date('2012/3/10 11:00:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/10 13:30:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4002, 'F', 0008 );

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2003, to_date('2012/3/11 12:00:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/11 13:30:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4003, 'F', 0004 );

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2018, to_date('2012/3/13 07:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/13 11:30:00', 'yyyy/mm/dd hh24:mi:ss'), 'T', 4004, 'F', 0014 );

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2004, to_date('2012/3/15 07:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/15 10:30:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4004, 'F', 0009 );

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2010, to_date('2012/3/18 07:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/18 10:30:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4010, 'F', 0005 );

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2019, to_date('2012/3/14 09:00:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/14 11:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'T', 4011, 'F', 0015 );

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2011, to_date('2012/3/22 08:00:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/22 09:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4011, 'F', 0010 );

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2012, to_date('2012/3/24 15:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/24 17:30:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4012, 'F', 0006 );

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2020, to_date('2012/3/11 20:00:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/11 21:30:00', 'yyyy/mm/dd hh24:mi:ss'), 'T', 4013, 'F', 0016 );

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2013, to_date('2012/3/11 19:00:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/11 21:30:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4013, 'F', 0011 );

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2014, to_date('2012/3/11 21:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/11 23:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4014, 'F', 0001);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2021, to_date('2012/3/16 09:00:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/16 14:30:00', 'yyyy/mm/dd hh24:mi:ss'), 'T', 4000, 'F', 0017 );

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2015, to_date('2012/3/16 09:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/16 15:30:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4000, 'F', 0002 );

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2022, to_date('2012/3/14 21:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/14 22:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4014, 'F', 00018);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2023, to_date('2012/3/16 15:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/16 16:30:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4014, 'F', 00019);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2024, to_date('2012/3/18 12:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/18 13:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4014, 'F', 00020);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2025, to_date('2012/3/20 14:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/20 15:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4014, 'F', 00021);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2026, to_date('2012/3/20 14:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/20 17:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4000, 'F', 00026);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2027, to_date('2012/3/21 14:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/21 17:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4001, 'F', 00027);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2028, to_date('2012/3/22 14:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/22 17:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4002, 'F', 00028);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2029, to_date('2012/3/23 14:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/23 17:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4003, 'F', 00029);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2030, to_date('2012/3/24 14:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/24 17:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4004, 'F', 00030);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2031, to_date('2012/3/25 14:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/25 17:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4005, 'F', 00031);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2032, to_date('2012/3/26 14:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/26 17:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4006, 'F', 00032);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2033, to_date('2012/3/27 14:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/27 17:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4007, 'F', 00033);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2034, to_date('2012/3/28 14:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/28 17:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4008, 'F', 00034);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2035, to_date('2012/3/29 14:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/29 17:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4009, 'F', 00035);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2036, to_date('2012/3/20 13:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/20 15:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4010, 'F', 00036);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2037, to_date('2012/3/21 13:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/21 15:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4011, 'F', 00037);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2038, to_date('2012/3/22 13:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/22 15:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4012, 'F', 00038);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2039, to_date('2012/3/23 13:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/23 15:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4013, 'F', 00039);

INSERT INTO Court_Rental(court_rental_id, start_date, end_date, for_course, court_id ,is_wait_list, trans_list_id)
VALUES (2040, to_date('2012/3/24 13:30:00', 'yyyy/mm/dd hh24:mi:ss'), to_date('2012/3/24 15:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'F', 4014, 'F', 00040);

--

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5000, 0007, 2000);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5001, 0003, 2001);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5002, 0008, 2002);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5003, 0004, 2003);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5004, 0009, 2004);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5005, 0005, 2010);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5006, 0010, 2011);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5007, 0006, 2012);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5008, 0011, 2013);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5009, 0001, 2014);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5010, 0002, 2015);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5011, 0012, 2016);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5012, 0013, 2017);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5013, 0014, 2018);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5014, 0015, 2019);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5015, 0016, 2020);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5016, 0017, 2021);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5017, 0018, 2022);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5018, 0019, 2023);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5019, 0020, 2024);

INSERT INTO Reservation(reservation_id, trans_list_id, court_rental_id)
VALUES (5020, 0021, 2025);

--

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5000, 2000, 0007);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5001, 2001, 0003);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5002, 2002, 0008);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5003, 2003, 0004);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5004, 2004, 0009);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5005, 2010, 0005);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5006, 2011, 0010);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5007, 2012, 0006);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5008, 2013, 0011);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5009, 2014, 0001);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5010, 2015, 0002);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5011, 2016, 0012);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5012, 2017, 0013);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5013, 2018, 0014);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5014, 2019, 0015);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5015, 2020, 0016);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5016, 2021, 0017);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5017, 2022, 0018);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5018, 2023, 0019);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5019, 2024, 0020);

INSERT INTO Account_Payment(reservation_id, court_rental_id, trans_list_id)
VALUES (5020, 2025, 0021);

--

INSERT INTO Coach(coach_id,salary, trainer_level)
VALUES (7001, 34500, 'Beginner');

INSERT INTO Coach(coach_id, salary, trainer_level)
VALUES (7002, 100000, 'Intermediate');

INSERT INTO Coach(coach_id,salary, trainer_level)
VALUES (7003, 130000, 'Advanced');

INSERT INTO Coach(coach_id,salary, trainer_level)
VALUES (7004, 102030, 'Intermediate');

--

INSERT INTO Course(course_id, course_name, course_level, max_capacity, current_number, coach_id, court_id, court_rental_id)
VALUES (8000, 'class 304', 'intermediate', 10, 10, 7004, 4000, 2016);

INSERT INTO Course(course_id, course_name, course_level, max_capacity, current_number, coach_id, court_id, court_rental_id)
VALUES (8001, 'class 305', 'beginner', 10, 10, 7001, 4002, 2017);

INSERT INTO Course(course_id, course_name, course_level, max_capacity, current_number, coach_id, court_id, court_rental_id)
VALUES (8002, 'class 306', 'advanced', 5, 3, 7003, 4004, 2018);

INSERT INTO Course(course_id, course_name, course_level, max_capacity, current_number, coach_id, court_id, court_rental_id)
VALUES (8003, 'class 307', 'beginner', 20, 10, 7001, 4011, 2019);

INSERT INTO Course(course_id, course_name, course_level, max_capacity, current_number, coach_id, court_id, court_rental_id)
VALUES (8004, 'class 308', 'intermediate', 10, 3, 7002, 4013, 2020);

INSERT INTO Course(course_id, course_name, course_level, max_capacity, current_number, coach_id, court_id, court_rental_id)
VALUES (8005, 'class 309', 'intermediate', 10, 4, 7002, 4000, 2021);



--

INSERT INTO Course_Reserve(course_reserve_id, course_id, transaction_id)
VALUES (1230, 8000, 1002);

INSERT INTO Course_Reserve(course_reserve_id, course_id, transaction_id)
VALUES (1231, 8004, 1003);

INSERT INTO Course_Reserve(course_reserve_id, course_id, transaction_id)
VALUES (1232, 8002, 1004);

INSERT INTO Course_Reserve(course_reserve_id, course_id, transaction_id)
VALUES (1233, 8001, 1005);

INSERT INTO Course_Reserve(course_reserve_id, course_id, transaction_id)
VALUES (1234, 8003, 1006);

INSERT INTO Course_Reserve(course_reserve_id, course_id, transaction_id)
VALUES (1235, 8005, 1007);

INSERT INTO Course_Reserve(course_reserve_id, course_id, transaction_id)
VALUES (1236, 8001, 1018);

INSERT INTO Course_Reserve(course_reserve_id, course_id, transaction_id)
VALUES (1237, 8003, 1019);

INSERT INTO Course_Reserve(course_reserve_id, course_id, transaction_id)
VALUES (1238, 8001, 1020);

INSERT INTO Course_Reserve(course_reserve_id, course_id, transaction_id)
VALUES (1239, 8003, 1021);


--

INSERT INTO Cancel(cancel_id, cancel_reason, cancel_amount, cancel_date, user_id)
VALUES (6000,'court is full', 50, to_date('2012/3/26 07:30:00', 'yyyy/mm/dd hh24:mi:ss'), 0001);

commit;
