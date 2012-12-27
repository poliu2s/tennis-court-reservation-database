SELECT
	course_id,
	course_name,
	start_date, 
	end_date
FROM 
	Court_Rental cre
JOIN
	Court_Reserve cr
ON
	cre.court_rental_id = cr.court_rental_id
JOIN 
	Course c
ON
	cr.course_id = c.course_id
WHERE
	course_id = @id
	-- the @id needs to be added as a parameter to operation
	


