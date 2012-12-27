SELECT
	transaction_id
FROM
	User_TransactionList utl
JOIN
	Reservation r
ON
	utl.trans_list_id = r.trans_list_id
JOIN
	Court_Rental cr
ON
	cr.court_rental_id = r.court_rental_id;
JOIN
	Course_Reserve cre
ON
	cr.court_rental_id = cre.court_rental_id;
WHERE
	utl.user_id = @user_id;
	
