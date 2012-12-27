SELECT
	c.court_id,
	c.court_number,
	c.court_location,
FROM 
	Court c
JOIN
	Court_Rental cr
ON
	c.rental_id = cr.court_rental_id
WHERE
	AND cr.start_time >= @time
	AND cr.end_time <= @time  -- mixed up inequality again?
	


