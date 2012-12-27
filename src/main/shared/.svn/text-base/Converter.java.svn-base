package main.shared;

import main.database.DbHandler;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;


public class Converter {
	
	private DbHandler dbHandler;
	
	public Converter() {
		dbHandler = DbHandler.getInstance();
		dbHandler.connect("localhost", "ora_i5l4", "a10653038");
	}
	
	public ArrayList<ArrayList<String>> returnCourtWithDate(String startTime, String endTime) throws ParseException {
		
		java.util.Date tempStartDate = null;
		java.util.Date tempEndDate = null;
		
		tempStartDate = DateFormat.getDateInstance().parse(startTime);
		tempEndDate = DateFormat.getDateInstance().parse(endTime);
		
		Timestamp startDate = new Timestamp(tempStartDate.getTime());
		Timestamp endDate = new Timestamp(tempStartDate.getTime());
		
		return dbHandler.getAllCourtsInTimePeriod(startDate, endDate);
		
		
	}
	
	public ArrayList<ArrayList<String>> returnCourseWithDate(String startTime, String endTime) throws ParseException {
		
		java.util.Date tempStartDate = null;
		java.util.Date tempEndDate = null;
		
		tempStartDate = DateFormat.getDateInstance().parse(startTime);
		tempEndDate = DateFormat.getDateInstance().parse(endTime);
		
		Timestamp startDate = new Timestamp(tempStartDate.getTime());
		Timestamp endDate = new Timestamp(tempStartDate.getTime());
		
		return dbHandler.getAllCourseInTimePeriod(startDate, endDate);
		
	}
	
	public ArrayList<ArrayList<String>> returnDateWithCourse(String courseIdValue) throws NumberFormatException{
		int courseId = Integer.parseInt(courseIdValue);
		
		return dbHandler.getAllTimePeriodsForCourse(courseId);
	}
	
	public ArrayList<ArrayList<String>> returnDateWithCourt(String courtIdValue) {
		int courtId = Integer.parseInt(courtIdValue);
		return dbHandler.getAllTimePeriodsForCourts(courtId);
	}
}
