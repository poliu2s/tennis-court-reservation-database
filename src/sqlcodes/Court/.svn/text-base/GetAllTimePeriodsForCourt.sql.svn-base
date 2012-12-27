SELECT
	c.court_id,
	c.court_number,
	c.court_location,
	cr.start_date, 
	cr.end_date
FROM 
	Court_Rental cr
JOIN
	Court c
ON
	cr.court_id = c.court_id
WHERE
	c.court_id = @id;
	-- the @id needs to be added as a parameter to operation