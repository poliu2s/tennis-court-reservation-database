create table Account_Refund(
	cancel_id INT,
	court_rental_id INT,
	user_id INT,
	
	priamary_key(cancel_id,court_rental_id,user_id),
	foreign key(cancel_id) references Cancel
	foreign key(court_rental_id) references Court_Rental
	foreign key(user_id) references Account_Holder

);