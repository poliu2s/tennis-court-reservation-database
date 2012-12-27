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