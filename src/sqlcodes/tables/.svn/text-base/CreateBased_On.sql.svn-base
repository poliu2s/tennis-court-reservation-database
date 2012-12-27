create table Based_On(
	reservation_id INT,
	cancel_id INT,
	priamary_key(reservation_id,cancel_id),
	foreign key(reservation_id) references Reservation,
	foreign key(cancel_id) references Cancel

);