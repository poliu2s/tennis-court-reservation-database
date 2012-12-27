

Insert into Cancel(cancel_id, cancel_reason, cancel_amount, cancel_date, user_id)
VALUES (@cancel_id, @cancel_reason, @cancel_amount, @cancel_date, @user_id)

DELETE
FROM
	Account_Payment ap
WHERE
	ap.reservation_id = @reservation_id
	AND ap.court_rental_id = @court_rental_id
	AND ap.trans_list_id = @trans_list_id

DELETE
FROM
	Reservation r
WHERE
	r.reservation_id = @reservation_id
	
DELETE
FROM
	Court_Rental cr
WHERE
	cr.court_rental_id = @court_rental_id
	
DELETE
FROM
	User_TransactionList utl
WHERE
	utl.transaction_id = @transaction_id;
	
DELETE
FROM
	Transaction t
WHERE
	t.transaction_id = @transaction_id;

UPDATE
	AppUser a
SET
	a.balance = @balance
WHERE
	a.user_id = @user_id


