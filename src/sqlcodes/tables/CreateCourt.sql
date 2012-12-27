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