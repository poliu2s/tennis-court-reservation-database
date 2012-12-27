-- coach that teachest fewest people

SELECT
	coach_id,
	MIN(current_number)
FROM
	Course c
GROUP BY
coach_id;

