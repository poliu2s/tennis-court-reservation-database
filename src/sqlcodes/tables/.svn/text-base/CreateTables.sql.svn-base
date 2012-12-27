
create table Transaction(
	transaction_amount INT,
	transaction_id INT,
	date INT,
	time INT,
	primary key (transaction_id),
	unique (Account_Holder_id)
);
	
create table Reservation(
	reservation_id INT
	transaction_id INT,
	Account_Holder_id INT,
	court_rental_id,
	
	primary key (reservation_id),
	foreign key (transaction_id) references Transaction(transaction_id),
	foreign key (Account_Holder_id) references Account_Holder(user_id),
	foreign key (court_rental_id) references Court_Rental(id)
);

create table Cancel(
	cancel_id INT,
	cancel_reason CHAR(40)
	transaction_id INT,
	reservation_id INT,
	Account_Holder_id INT,
	court_rental_id,
	primary key(id),
	foreign key (transaction_id) references Transaction(transaction_id),
	foreign key (reservation_id) references Reservation(reservation_id),
	foreign key (Account_Holder_id) references Account_Holder(user_id),
	foreign key (court_rental_id) references Court_Rental(court_rental_id)
);

create table Account_Holder(
	user_id INT,
	balance INT,
	address CHAR(40),
	name CHAR(40),
	transaction_id INT,
	court_rental_id,
	primary key (user_id)
	foreign key (transaction_id) references Transaction(transaction_id),
	foreign key (court_rental_id) references Court_Rental(court_rental_id),
);

create table Court_Rental(
	court_rental_id INT,
	start_time INT,
	end_time INT,
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

create table Coach(
	coach_id INT,
	name CHAR(40)
	salary INT,
	trainer_level CHAR(20),
	primary_key (coach_id)
);

create table Course(
	course_id INT,
	course_name CHAR(40),
	course_level CHAR(40),
	max_capacity INT,
	primary_key (course_id)
);

create table Based_On(
	reservation_id INT,
	id INT,
	priamary_key(reservation_id,id),
	foreign key(reservation_id) references Reservation,
	foreign key(id) references Cancel

);

create table Reserves(
	court_id INT
   	court_rental_id INT
	
	priamary_key(court_id,court_rental_id),
	foreign key(court_id) references Court,
	foreign key(court_rental_id) references Court_Rental

);

create table Course_Reserve(
	court_rental_id INT,
	course_id INT,
	coach_id INT,
	
	priamary_key(court_rental_id,course_id,coach_id),
	foreign key(court_rental_id) references Court_Rental,
	foreign key(Course_id) references COurse,
	foreign key(coach_id) references Coach

);

create table Account_Payment(
	reservation_id INT,
	court_rental_id INT,
	user_id INT,
	
	priamary_key(reservation_id,court_rental_id,user_id),
	foreign key(reservation_id) references Reservation
	foreign key(court_rental_id) references Court_Rental
	foreign key(user_id) references Account_Holder

);

create table Account_Refund(
	cancel_id INT,
	court_rental_id INT,
	user_id INT,
	
	priamary_key(cancel_id,court_rental_id,user_id),
	foreign key(cancel_id) references Cancel
	foreign key(court_rental_id) references Court_Rental
	foreign key(user_id) references Account_Holder

);

create table Views(

	court_id INT
	viewer_id INT

	primary_key(court_id,viewer_id)

	foreign key(viewer_id) references Viewer
	foreign key(court_id) references Court
);

create table Viewer(
	viewer_id INT
	primary_key(viewer_id)
);

