create table Course_Reserve(
	court_rental_id INT,
	course_id INT,
	coach_id INT,
	
	priamary_key(court_rental_id,course_id,coach_id),
	foreign key(court_rental_id) references Court_Rental,
	foreign key(course_id) references Course,
	foreign key(coach_id) references Coach

);