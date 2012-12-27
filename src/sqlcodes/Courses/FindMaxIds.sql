SELECT
	MAX(t.transaction_id), 
	MAX(r.reservation_id),
	MAX(cr.court_rental_id),
	MAX(r.trans_list_id),
	MAX(cre.course_reserve_id)
FROM
	Transaction t
JOIN 
	user_transactionlist utl
ON
	utl.transaction_id = t.transaction_id
JOIN
	Reservation r
ON
	utl.trans_list_id = r.trans_list_id
JOIN
	Court_Rental cr
ON
	cr.trans_list_id = r.trans_list_id
LEFT OUTER JOIN
	Course_Reserve cre
ON
	cre.transaction_id = t.transaction_id;


