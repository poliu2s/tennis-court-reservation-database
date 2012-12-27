

Insert into Cancel(cancel_id, cancel_reason, cancel_amount, cancel_date, user_id)
VALUES (@cancel_id, @cancel_reason, @cancel_amount, @cancel_date, @user_id)

DELETE 
FROM 
	Course_Reserve cr
WHERE
	cr.transaction_id = @transaction_id
	
UPDATE
SET
	Course c
WHERE
	c.course_id = @course_id

DELETE
FROM
	User_TransactionList utl
WHERE
	utl.transaction_id = @transaction_id
	
DELETE
FROM
	Transaction t
WHERE
	t.transaction_id = @transaction_id
	
UPDATE
	AppUser a
SET
	a.balance = @balance
WHERE
	a.user_id = @user_id



