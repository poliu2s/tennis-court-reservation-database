SELECT	
	reservation_id,
	trans_list_id,	
	court_rental_id
FROM
	Reservation r
JOIN
	User_TransactionList utl
ON
	r.trans_list_id = utl.trans_list_id;
WHERE
	utl.user_id = @user_id;
