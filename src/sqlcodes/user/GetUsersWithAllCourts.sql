--SELECT
--	Appuser.user_id, Court.court_location
--FROM
--	Appuser
--JOIN
--	User_TransactionList
--ON
--	User_TransactionList.user_id = Appuser.user_id
--JOIN
--	Court_Rental
--ON
--	Court_Rental.trans_list_id = User_TransactionList.trans_list_id
--JOIN
--	Court
--ON
--	Court_Rental.court_id = Court.court_id

SELECT
	distinct utlout.user_id
FROM 
	User_TransactionList utlout
WHERE NOT EXISTS
(
	SELECT
		distinct cmid.court_location, utlmid.user_id
	FROM
		Court cmid
	JOIN
		Court_Rental crmid
	ON
		crmid.court_id = cmid.court_id
	JOIN
		User_TransactionList utlmid
	ON
		utlmid.trans_list_id = crmid.trans_list_id
	WHERE cmid.court_location NOT IN
		(
			SELECT distinct c.court_location
			FROM Court c
			JOIN
				Court_Rental cr
			ON
				c.court_id = cr.court_id
			JOIN
				User_TransactionList utl
			ON
				cr.trans_list_id = utl.trans_list_id
			WHERE 
				utl.user_id = utlout.user_id
		)
);
