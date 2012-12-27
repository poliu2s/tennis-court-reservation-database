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