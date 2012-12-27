create table Account_Holder(
	user_id INT,
	balance INT,
	address CHAR(40),
	name CHAR(40),
	transaction_id INT,
	court_rental_id INT,
	primary key (user_id, transaction_id)
	foreign key (transaction_id) references Transaction(transaction_id),
	foreign key (court_rental_id) references Court_Rental(court_rental_id),
);

create table Account_Payment(
	reservation_id INT,
	court_rental_id INT,
	user_id INT,
	
	primary_key(reservation_id,court_rental_id,user_id),
	foreign key(reservation_id) references Reservation
	foreign key(court_rental_id) references Court_Rental
	foreign key(user_id) references Account_Holder

);

create table Account_Refund(
	cancel_id INT,
	court_rental_id INT,
	user_id INT,
	
	primary_key(cancel_id,court_rental_id,user_id),
	foreign key(cancel_id) references Cancel
	foreign key(court_rental_id) references Court_Rental
	foreign key(user_id) references Account_Holder

);

create table Based_On(
	reservation_id INT,
	cancel_id INT,
	primary_key(reservation_id,cancel_id),
	foreign key(reservation_id) references Reservation,
	foreign key(cancel_id) references Cancel

);

create table Cancel(
	cancel_id INT,
	cancel_reason CHAR(40)
	transaction_id INT,
	reservation_id INT,
	Account_Holder_id INT,
	court_rental_id,
	primary key(cancel_id),
	foreign key (transaction_id) references Transaction(transaction_id),
	foreign key (reservation_id) references Reservation(reservation_id),
	foreign key (Account_Holder_id) references Account_Holder(user_id),
	foreign key (court_rental_id) references Court_Rental(court_rental_id)
);

create table Coach(
	coach_id INT,
	name CHAR(40)
	salary INT,
	trainer_level CHAR(20),
	primary_key (coach_id)
);

create table Course_Reserve(
	court_rental_id INT,
	course_id INT,
	coach_id INT,
	
	priamary_key(court_rental_id,course_id,coach_id),
	foreign key(court_rental_id) references Court_Rental,
	foreign key(course_id) references Course,
	foreign key(coach_id) references Coach

);

create table Course(
	course_id INT,
	course_name CHAR(40),
	course_level CHAR(40),
	max_capacity INT,
	current_number INT,
	primary_key (course_id)
);

create table Court_Rental(
	court_rental_id INT,
	start_time DATETIME,
	end_time DATETIME,
	for_course BOOLEAN,
	court_id INT,
	is_wait_list BOOLEAN,
	Account_Holder_id INT,
	transaction_id INT,
	primary key (court_rental_id),
	unique (start_time, end_time, court_id)
	foreign key (court_id) references Court(court_id),
	foreign key (Account_Holder_id) references Account_Holder(user_id),
	foreign key (transaction_id) references Transaction(transaction_id),
);

create table Court(
	court_id INT,
	court_number INT,
	court_location CHAR(40),
	rental_id INT,
	is_reserved BOOLEAN,
	primary_key (court_id),
	unique (court_number, court_location),
	foreign key (rental_id) references Court_Rental(court_rental_id)
);

create table Reservation(
	reservation_id INT
	transaction_id INT,
	Account_Holder_id INT,
	court_rental_id,
	
	primary key (reservation_id),
	foreign key (transaction_id) references Transaction(transaction_id),
	foreign key (Account_Holder_id) references Account_Holder(user_id),
	foreign key (court_rental_id) references Court_Rental(court_rental_id)
);

create table Reserves(
	court_id INT
   	court_rental_id INT
	
	priamary_key(court_id,court_rental_id),
	foreign key(court_id) references Court,
	foreign key(court_rental_id) references Court_Rental

);


create table Transaction(
	transaction_amount INT,
	transaction_id INT,
	date INT,
	time INT,
	primary key (transaction_id),
	unique (Account_Holder_id)
);
	
INSERT INTO Transaction(transaction_amount, transaction_id, time)
VALUES (50, 1007, '2012-3-10 09:00:00');

INSERT INTO Transaction(transaction_amount, transaction_id, time)
VALUES (50, 1007, '2012-3-16 09:05:00');

INSERT INTO Transaction(transaction_amount, transaction_id, time)
VALUES (25, 1003, '2012-3-10 09:00:00');

INSERT INTO Transaction(transaction_amount, transaction_id, time)
VALUES (50, 1008, '2012-3-12 11:00:00');

INSERT INTO Transaction(transaction_amount, transaction_id, time)
VALUES (25, 1003, '2012-3-12 12:00:00');

INSERT INTO Transaction(transaction_amount, transaction_id, time)
VALUES (50, 1009, '2012-3-13 07:30:00');

INSERT INTO Transaction(transaction_amount, transaction_id, time)
VALUES (25, 1004, '2012-3-10 8:00:01');

INSERT INTO Transaction(transaction_amount, transaction_id, time)
VALUES (50, 1010, '2012-3-14 08:00:00');

INSERT INTO Transaction(transaction_amount, transaction_id, time)
VALUES (25, 1005, '2012-3-15 15:30:00');

INSERT INTO Transaction(transaction_amount, transaction_id, time)
VALUES (50, 1011, '2012-3-11 20:00:00');

INSERT INTO Transaction(transaction_amount, transaction_id, time)
VALUES (25, 1006, '2012-3-11 21:30:00');

--

INSERT INTO Account_Holder(user_id,balance,address,name,transaction_id,court_rental_id)
VALUES (0000, 25, '1326 W.13th Ave', 'Peter', 1007, 2000);

INSERT INTO Account_Holder(user_id,balance,address,name,transaction_id,court_rental_id)
VALUES (0000, 25, '1326 W.13th Ave', 'Peter', 1007, 2015);

INSERT INTO Account_Holder(user_id,balance,address,name,transaction_id,court_rental_id)
VALUES (0000, 25, '1326 W.13th Ave', 'Peter', 1008, 2002);

INSERT INTO Account_Holder(user_id,balance,address,name,transaction_id,court_rental_id)
VALUES (0001, 26, '38 E.Hastings', 'Po', 1009, 2004);

INSERT INTO Account_Holder(user_id,balance,address,name,transaction_id,court_rental_id)
VALUES (0001, 26, '38 E.Hastings', 'Po', 1010, 2011);

INSERT INTO Account_Holder(user_id,balance,address,name,transaction_id,court_rental_id)
VALUES (0002, 27, '41 Oak St', 'Andrew', 1011, 2013);

INSERT INTO Account_Holder(user_id,balance,address,name,transaction_id,court_rental_id)
VALUES (0003, 28, '546 Ontario St', 'Neil', 1003, 2001);

INSERT INTO Account_Holder(user_id,balance,address,name,transaction_id,court_rental_id)
VALUES (0003, 28, '546 Ontario St', 'Neil', 1003, 2003);

INSERT INTO Account_Holder(user_id,balance,address,name,transaction_id,court_rental_id)
VALUES (0003, 28, '546 Ontario St', 'Neil', 1004, 2010);

INSERT INTO Account_Holder(user_id,balance,address,name,transaction_id,court_rental_id)
VALUES (0004, 29, '26 Arbutus St', 'Jennifer', 1005, 2012);

INSERT INTO Account_Holder(user_id,balance,address,name,transaction_id,court_rental_id)
VALUES (0005, 30, '16 WestBroadWay', 'Kathy', 1006, 2014);

--

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4000, 1000, 'Vancouver', 2000, 'TRUE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4015, 1015, 'Vancouver', 2015, 'FALSE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4016, 1016, 'Vancouver', 2016, 'FALSE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4001, 1001, 'New West Minister', 2001, 'TRUE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4017, 1017, 'New West Minister', 2017, 'FALSE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4018, 1018, 'New West Minister', 2018, 'FALSE');


INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4002, 1002, 'Coquitlam', 2002, 'TRUE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4003, 1003, 'Marine Drive', 2003, 'TRUE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4004, 1004, 'North Vancouver', 2004, 'TRUE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4005, 1005, 'Richmond', 2005, 'FALSE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4019, 1019, 'Richmond', 2019, 'FALSE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4020, 1020, 'Richmond', 2020, 'FALSE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4006, 1006, 'Burnaby', 2006, 'FALSE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4007, 1007, 'abbotsford', 2007, 'FALSE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4008, 1008, 'Langley', 2008, 'FALSE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4009, 1009, 'Surrey', 2009, 'FALSE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4010, 1010, 'Chiliwack', 2010, 'TRUE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4011, 1011, 'Aldergrove', 2011, 'TRUE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4012, 1012, 'Port Coquitlam', 2012, 'TRUE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4013, 1013, 'White Rock', 2013, 'TRUE');

INSERT INTO Court(court_id, court_number, court_location, rental_id, is_reserved)
VALUES(4014, 1014, 'Delta', 2014, 'TRUE');

--

INSERT INTO Court_Rental(Court_Rental_id, start_time, end_time, for_course, court_id ,is_wait_list, Account_holder_id, transaction_id)
VALUES (2000,'2012-3-10 09:00:00', '2012-3-10 11:30:00', 'TRUE', 4000, 'FALSE',0000, 1007 );

INSERT INTO Court_Rental(Court_Rental_id, start_time, end_time, for_course, court_id ,is_wait_list, Account_holder_id, transaction_id)
VALUES (2015,'2012-3-16 09:05:00', '2012-3-16 14:30:00', 'TRUE', 4000, 'FALSE',0000, 1007 );

INSERT INTO Court_Rental(Court_Rental_id, start_time, end_time, for_course, court_id ,is_wait_list, Account_holder_id, transaction_id)
VALUES (2001,'2012-3-10 09:00:00', '2012-3-10 12:30:00', 'FALSE', 4001, 'TRUE',0003, 1003 );

INSERT INTO Court_Rental(Court_Rental_id, start_time, end_time, for_course, court_id ,is_wait_list, Account_holder_id, transaction_id)
VALUES (2002,'2012-3-12 11:00:00', '2012-3-12 13:30:00', 'TRUE', 4002, 'FALSE',0000, 1008 );

INSERT INTO Court_Rental(Court_Rental_id, start_time, end_time, for_course, court_id ,is_wait_list, Account_holder_id, transaction_id)
VALUES (2003,'2012-3-12 12:00:00', '2012-3-12 13:30:00', 'FALSE', 4003, 'TRUE',0003, 1003 );

INSERT INTO Court_Rental(Court_Rental_id, start_time, end_time, for_course, court_id ,is_wait_list, Account_holder_id, transaction_id)
VALUES (2004,'2012-3-13 07:30:00', '2012-3-13 10:30:00', 'TRUE', 4004, 'FALSE',0001, 1009 );

INSERT INTO Court_Rental(Court_Rental_id, start_time, end_time, for_course, court_id ,is_wait_list, Account_holder_id, transaction_id)
VALUES (2010,'2012-3-13 07:30:00', '2012-3-13 10:30:00', 'FALSE', 4010, 'TRUE',0003, 1004 );

INSERT INTO Court_Rental(Court_Rental_id, start_time, end_time, for_course, court_id ,is_wait_list, Account_holder_id, transaction_id)
VALUES (2011,'2012-3-14 08:00:00', '2012-3-14 9:00:00', 'TRUE', 4011, 'FALSE',0001, 1010 );

INSERT INTO Court_Rental(Court_Rental_id, start_time, end_time, for_course, court_id ,is_wait_list, Account_holder_id, transaction_id)
VALUES (2012,'2012-3-15 15:30:00', '2012-3-15 17:30:00', 'FALSE', 4012, 'TRUE',0004, 1005 );

INSERT INTO Court_Rental(Court_Rental_id, start_time, end_time, for_course, court_id ,is_wait_list, Account_holder_id, transaction_id)
VALUES (2013,'2012-3-11 20:00:00', '2012-3-11 21:30:00', 'TRUE', 4013, 'FALSE',0002, 1011 );

INSERT INTO Court_Rental(Court_Rental_id, start_time, end_time, for_course, court_id ,is_wait_list, Account_holder_id, transaction_id)
VALUES (2014,'2012-3-11 21:30:00', '2012-3-11 23:00:00', 'FALSE', 4014, 'TRUE',0005, 1006);

--

INSERT INTO Reservation(reservation_id, transaction_id, Account_Holder_id, court_rental_id)
VALUES (5000, 1007, 0000, 2000);

INSERT INTO Reservation(reservation_id, transaction_id, Account_Holder_id, court_rental_id)
VALUES (5001, 1003, 0003, 2001);

INSERT INTO Reservation(reservation_id, transaction_id, Account_Holder_id, court_rental_id)
VALUES (5002, 1008, 0000, 2002);

INSERT INTO Reservation(reservation_id, transaction_id, Account_Holder_id, court_rental_id)
VALUES (5003, 1003, 0003, 2003);

INSERT INTO Reservation(reservation_id, transaction_id, Account_Holder_id, court_rental_id)
VALUES (5004, 1009, 0001, 2004);

INSERT INTO Reservation(reservation_id, transaction_id, Account_Holder_id, court_rental_id)
VALUES (5005, 1004, 0003, 2010);

INSERT INTO Reservation(reservation_id, transaction_id, Account_Holder_id, court_rental_id)
VALUES (5006, 1010, 0001, 2011);

INSERT INTO Reservation(reservation_id, transaction_id, Account_Holder_id, court_rental_id)
VALUES (5007, 1005, 0004, 2012);

INSERT INTO Reservation(reservation_id, transaction_id, Account_Holder_id, court_rental_id)
VALUES (5008, 1011, 0002, 2013);

INSERT INTO Reservation(reservation_id, transaction_id, Account_Holder_id, court_rental_id)
VALUES (5009, 1006, 0005, 2014);

INSERT INTO Reservation(reservation_id, transaction_id, Account_Holder_id, court_rental_id)
VALUES (5010, 1007, 0000, 2015);

--

INSERT INTO Reserves(court_id, court_rental_id)
VALUES (4000, 2000);

INSERT INTO Reserves(court_id, court_rental_id)
VALUES (4000, 2015);

INSERT INTO Reserves(court_id, court_rental_id)
VALUES (4001, 2001);

--

INSERT INTO Account_Payment(reservation_id, court_rental_id, user_id)
VALUES (5000, 2000, 0000);

INSERT INTO Account_Payment(reservation_id, court_rental_id, user_id)
VALUES (5001, 2001, 0003);

INSERT INTO Account_Payment(reservation_id, court_rental_id, user_id)
VALUES (5002, 2002, 0000);

INSERT INTO Account_Payment(reservation_id, court_rental_id, user_id)
VALUES (5003, 2003, 0003);

INSERT INTO Account_Payment(reservation_id, court_rental_id, user_id)
VALUES (5004, 2004, 0001);

INSERT INTO Account_Payment(reservation_id, court_rental_id, user_id)
VALUES (5005, 2010, 0003);

INSERT INTO Account_Payment(reservation_id, court_rental_id, user_id)
VALUES (5006, 2011, 0001);

INSERT INTO Account_Payment(reservation_id, court_rental_id, user_id)
VALUES (5007, 2012, 0004);

INSERT INTO Account_Payment(reservation_id, court_rental_id, user_id)
VALUES (5008, 2013, 0002);

INSERT INTO Account_Payment(reservation_id, court_rental_id, user_id)
VALUES (5009, 2014, 0005);

INSERT INTO Account_Payment(reservation_id, court_rental_id, user_id)
VALUES (5010, 2015, 0000);

--


INSERT INTO Account_Refund(cancel_id , court_rental_id , user_id)
VALUES (6000, 2001, 0003);

--

INSERT INTO Coach(coach_id, name, salary, trainer_level)
VALUES (7000, 'Kisoo', 34500, 'Beginner');

INSERT INTO Coach(coach_id, name, salary, trainer_level)
VALUES (7001, 'Akash', 100000, 'Intermediate');

INSERT INTO Coach(coach_id, name, salary, trainer_level)
VALUES (7002, 'Jung', 130000, 'Advanced');

INSERT INTO Coach(coach_id, name, salary, trainer_level)
VALUES (7003, 'Po', 102030, 'Intermediate');

--

INSERT INTO Course(course_id, course_name, course_level, max_capacity, current_number)
VALUES (8000, 'class 304', 'intermediate', 10, 10);

INSERT INTO Course(course_id, course_name, course_level, max_capacity, current_number)
VALUES (8001, 'class 305', 'beginner', 10, 10);

INSERT INTO Course(course_id, course_name, course_level, max_capacity, current_number)
VALUES (8002, 'class 306', 'advanced', 5, 3);

INSERT INTO Course(course_id, course_name, course_level, max_capacity, current_number)
VALUES (8003, 'class 307', 'beginner', 20, 10);

INSERT INTO Course(course_id, course_name, course_level, max_capacity, current_number)
VALUES (8004, 'class 308', 'intermediate', 10, 3);

INSERT INTO Course(course_id, course_name, course_level, max_capacity, current_number)
VALUES (8005, 'class 309', 'intermediate', 10, 4);



--

INSERT INTO Course_Reserve(court_rental_id, course_id, coach_id)
VALUES (2000, 8000, 7001);

INSERT INTO Course_Reserve(court_rental_id, course_id, coach_id)
VALUES (2002, 8004, 7001);

INSERT INTO Course_Reserve(court_rental_id, course_id, coach_id)
VALUES (2004, 8002, 7002);

INSERT INTO Course_Reserve(court_rental_id, course_id, coach_id)
VALUES (2011, 8001, 7003);

INSERT INTO Course_Reserve(court_rental_id, course_id, coach_id)
VALUES (2013, 8003, 7002);

INSERT INTO Course_Reserve(court_rental_id, course_id, coach_id)
VALUES (2015, 8005, 7002);

--

INSERT INTO Cancel(cancel_id, cancel_reason, transaction_id, reservation_id, Account_Holder_id, court_rental_id)
VALUES (6000,'court is full', 1003, 5001 , 0003, 2001);

--

INSERT INTO Based_On(reservation_id, cancel_id)
VALUES (5001, 6000);

--

