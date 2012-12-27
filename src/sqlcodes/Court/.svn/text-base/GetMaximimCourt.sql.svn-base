SELECT 
	court_location, num
FROM (
	SELECT c.court_location, count(*) as num
	FROM court c 
	JOIN court_rental cr 
	ON cr.court_id = c.court_id 
	WHERE cr.start_date >= timestamp '2007-04-10 09:00:00.0' 
	AND cr.end_date <= timestamp '2027-04-10 12:00:00.0' 
	GROUP BY c.court_location
)
WHERE num >= (
	SELECT max(count(*))
	FROM court c2 
	JOIN court_rental cr2 
	ON cr2.court_id = c2.court_id 
	WHERE cr2.start_date >= timestamp '2007-04-10 09:00:00.0' 
	AND cr2.end_date <= timestamp '2027-04-10 12:00:00.0' 
	GROUP BY c2.court_location
);

