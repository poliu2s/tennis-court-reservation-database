create table Transaction(
	transaction_amount INT,
	transaction_id INT,
	time DATETIME,
	primary key (transaction_id),
	unique (Account_Holder_id)
);