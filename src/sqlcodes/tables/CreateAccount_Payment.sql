create table Account_Payment(
	reservation_id INT,
	court_rental_id INT,
	user_id INT,
	
	priamary_key(reservation_id,court_rental_id,user_id),
	foreign key(reservation_id) references Reservation
	foreign key(court_rental_id) references Court_Rental
	foreign key(user_id) references Account_Holder

);