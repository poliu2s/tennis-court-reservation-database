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