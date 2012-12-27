	
SELECT
	reservation_id,
	court_rental_id,
	user_id,
	transaction_amount
FROM
	Reservation r
JOIN
	User_TransactionList utl
ON
	utl.trans_list_id = r.trans_list_id
JOIN
	AppUser a
ON
	a.user_id = utl.user_id
JOIN
	Transaction t
ON
	t.transaction_id = @transaction_id;
