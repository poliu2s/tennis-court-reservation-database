SELECT
	utl.transaction_id
FROM
	User_TransactionList utl
WHERE
	utl.user_id = @user_id
	AND utl.transaction_id IN
	(
		SELECT
			transaction_id
		FROM
			Course_Reserve
	)
	
	