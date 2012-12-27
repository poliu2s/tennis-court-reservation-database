SELECT
	c.course_id,
	c.course_name,
	c.course_level,
	c.current_number,
FROM 
	Course c
JOIN
	Court_Reserve cr
ON
	c.course_id = cr.course_id
JOIN 
	Court_Rental cre
ON
	cr.court_rental_id = cre.court_rental_id
WHERE
	c.max_capacity > c.current_number
	AND cre.start_time <= @time
	AND cre.end_time >= @time;
	


