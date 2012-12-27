INSERT into Transaction(transaction_amount, transaction_id)
	VALUES (@amount, @transaction_id)
INSERT into User_TransactionList(trans_list_id, transaction_id, user_id)
	VALUES (@trans_list_id, @transaction_id, @user_id)

-- this query should insert number++ into the course
--use this query first to get the current number in the course:
SELECT
	course_number
FROM
	COURSE
WHERE
	course.course_id = @course_id;
	
--	end comment
UPDATE Course
SET current_number = @number
WHERE course_id = @course_id;

INSERT into Course_Reserve(course_reserve_id, course_id, transaction_id)
	VALUES (@course_reserve_id, @course_id, @transaction_id)

	
	
UPDATE appuser
SET balance = @balance
WHERE user_id = @user_id



	court_rental_id INT,
	course_id INT,
	coach_id INT,
	

transaction

court_rental
court

reservation
account_holder
account_payment