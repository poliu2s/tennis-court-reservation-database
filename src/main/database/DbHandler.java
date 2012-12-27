package main.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class DbHandler {
	
	private static DbHandler instance = null;
	protected DbHandler() { initConstraints(); }
	private static Connection con;
	private static String my_id;
	private static Map<String, List<String>> constraints = new HashMap<String, List<String>>();
	
	/**
	 * Construct the list of constraints at the instantiation
	 */
	private void initConstraints() {
		List<String> tmp = new ArrayList<String>();	
		tmp.add("transaction_ref");
		tmp.add("court_rental_ref");
		constraints.put("account_holder", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("reservation_id_ref");
		tmp.add("court_rental_id_ref");		
		tmp.add("user_id_ref");
		constraints.put("account_payment", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("cancel_id_ref");
		tmp.add("court_rental_id_ref");
		tmp.add("user_id_refund_ref");
		constraints.put("account_refund", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("reservation_id_ref");
		tmp.add("cancel_id_ref");
		constraints.put("Based_On", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("transaction_id_ref");
		tmp.add("reservation_id_cancel_ref");
		tmp.add("account_holder_id_ref");
		tmp.add("court_rental_id_ref");
		constraints.put("Cancel", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("court_rental_id_reseve_ref");
		tmp.add("course_id_ref");
		tmp.add("coach_id_ref");
		constraints.put("Course_Reserve", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("coach_id_rental_ref");
		tmp.add("account_holder_id_rental_ref");
		tmp.add("transaction_id_rental_ref");
		constraints.put("Court_Rental", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("rental_id_ref");
		constraints.put("Court", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("transaction_id_reservation_ref");
		tmp.add("account_id_reserv_ref");
		tmp.add("court_rental_id_reserv_ref");
		constraints.put("Reservation", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("court_id_ref");
		tmp.add("court_rental_id_reserves_ref");
		constraints.put("Reserves", tmp);
	}
	
	/**
	 * Enables all the constraints
	 */
	public void enableConstraints() {
		String sql = "";
		
		for (String key: constraints.keySet()) {
			sql = "alter table " + key;
			for (String value: constraints.get(key)) {
				sql += " enable Constraint " + value;
			}
			sql += ";";
			//System.out.println(sql);
		}
	}
	
	/**
	 * Disables all the constraints
	 */
	public void disableConstraints() {
			String sql = "";
		
		for (String key: constraints.keySet()) {
			sql = "alter table " + key;
			for (String value: constraints.get(key)) {
				sql += " disable Constraint " + value;
			}
			sql += ";";
			//System.out.println(sql);
		}
	}

	/**
	 * Initialize dbHandler singleton object
	 */
	public static DbHandler getInstance() {
		if (instance == null) 
			instance = new DbHandler();
		return instance;
	}
	
	/**
	 * Connected to the specified database
	 * @param url	url to the server address
	 * @param id	oracle DB id
	 * @param passwd	oracle DB password
	 */
	public void connect(String url, String id, String passwd) {
		my_id = id;
		System.out.println("Registering Driver...");
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		
		System.out.println("Driver Registered.");
		
		System.out.println("Attempting to connect...");
		con = DriverManager.getConnection(
				"jdbc:oracle:thin:@" + url + ":1521:ug", my_id, passwd);
		System.out.println("Connection Successful.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: Connection Unsuccessful");
			e.printStackTrace();
		}
	}
	
	/**
	 * Disconnect the current connection
	 */
	public void disconnect() { 
		try {
			con.close();
			System.out.println("Disconnected from the server.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: Disconnect Unsuccessful");
			e.printStackTrace();
		}	
	}
	
	/**
	 * Test method for debugging
	 * Receive the list of all tables under this account
	 * @return vector of string of all table names
	 */
	public Vector<String> getTables() {
		Vector<String> tables = new Vector<String>();
		
		try {
			PreparedStatement ps = con.prepareStatement(
					"SELECT table_name " +
					"FROM all_tables " + 
					"WHERE lower(owner) = ?");
			ps.setString(1, my_id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				tables.add(rs.getString(1));
			}
			
			return tables;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
		
	// sqlcodes.Centres
	/**
	 * Return the number of courts at all centres
	 * @return COURT_LOCATION, COUNT(COURT)
	 */
	public ArrayList<ArrayList<String>> getAllCentresAndCourts() {
		String sql = "SELECT c.court_location, COUNT(c.court_number) " +
				"FROM court c " +
				"GROUP BY c.court_location";
		
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("COURT_LOCATION");
		columnNames.add("COUNT");
		
		ArrayList<ArrayList<String>> result = runSqlRead(sql, 2);
		result.add(0, columnNames);
		
		return result;
	}
	
	public ArrayList<ArrayList<String>> getAllCourtLocations() {
		String sql = "SELECT DISTINCT COURT_LOCATION" +
				" FROM court c";
		
		ArrayList<ArrayList<String>> result = runSqlRead(sql, 1);
		
		return result;
	}
	
	public ArrayList<ArrayList<String>> getAllCourtNumbersForLocation(String location) {
		String sql = "SELECT c.court_number" +
				" FROM court c" +
				" WHERE lower(c.court_location) = " + quote(location.toLowerCase());
	
		ArrayList<ArrayList<String>> result = runSqlRead(sql, 1);
		
		return result;
	}
	
	// sqlcodes.Course
	/**
	 * Returns the list of tuples within the specified time period
	 * @param start	start time
	 * @param end	end time
	 * @return	COURSE_ID, COURSE_NAME, COURSE_LEVEL, CURRENT_NUMBER
	 */
	public ArrayList<ArrayList<String>> getAllCourseInTimePeriod(Timestamp start, Timestamp end) {
		String sql = "SELECT DISTINCT c.course_id, c.course_name, c.course_level, c.current_number " +
				"FROM court_rental cre " +
				"JOIN course c " +
				"ON c.court_id = cre.court_id " +
				"JOIN course_reserve cr " +
				"ON cr.course_id = c.course_id " +
				"WHERE c.max_capacity > c.current_number " +
				"AND cre.start_date >= timestamp '" + start + "' " +
				"AND cre.end_date <= timestamp '" + end + "'";
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("COURSE_ID");
		columnNames.add("COURSE_NAME");
		columnNames.add("COURSE_LEVEL");
		columnNames.add("CURRENT_NUMBER");
		
		ArrayList<ArrayList<String>> result = runSqlRead(sql, 4);
		result.add(0, columnNames);
		
		return result;
	}
	/**
	 * Returns the list of tuples of all time periods of the specified court id
	 * @param courtid id of the court
	 * @return COURSE_ID, COURSE_NAME, COURSE_LEVEL, CURRENT_NUMBER
	 */
	public ArrayList<ArrayList<String>> getAllTimePeriodsForCourse(int courseid) {
		String sql = "SELECT DISTINCT course_id, course_name, course_level, current_number " +
				"FROM court_rental cre " +
				"JOIN course c " +
				"ON c.court_id = cre.court_id " +
				"JOIN course_reserve cr " +
				"ON cr.course_id = c.course_id " +
				"WHERE course_id = " + courseid;
		
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("COURSE_ID");
		columnNames.add("COURSE_NAME");
		columnNames.add("COURSE_LEVEL");
		columnNames.add("CURRENT_NUMBER");
		
		ArrayList<ArrayList<String>> result = runSqlRead(sql, 4);
		result.add(0, columnNames);
		
		return result;
	}

	public ArrayList<ArrayList<String>> getSmallestCourse() {
		String subsql = "SELECT course.current_number " +
				"FROM course";
				
		String sql = "SELECT course_id, course_name, current_number " +
				"FROM course c " +
				"WHERE c.current_number <= ALL (" + subsql + ")";
		
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("COURSE_ID");
		columnNames.add("COURSE_NAME");
		columnNames.add("CURRENT_NUMBER");
		
		ArrayList<ArrayList<String>> result = runSqlRead(sql, 3);
		result.add(0, columnNames);
		
		return result;
	}
	
	/**
	 * Reserve a course
	 * @param login_id
	 * @param course_name
	 * @param amount
	 * @return
	 */
	public int reserveCourse(String login_id, String course_name, int amount) {
			
		ArrayList<Integer> maxids = findMaxIDs();
		String transaction_id = Integer.toString(maxids.get(0) + 1);
		String reservation_id = Integer.toString(maxids.get(1) + 1);
		String court_rental_id = Integer.toString(maxids.get(2) + 1);
		String trans_list_id = Integer.toString(maxids.get(3) + 1);
		String course_reserve_id = Integer.toString(maxids.get(4) +1);
		String trans_amt = Integer.toString(amount);
		String user_id = this.findUserID(login_id);
		course_name = (course_name.split(":"))[0];
		int course_id = this.findCourseID(course_name);
		String current_number = Integer.toString(this.findCourseCurrentNumber(course_id) + 1);
				
		Map<String, String> data = new HashMap<String, String>();
		data.put("transaction_amount", trans_amt);
		data.put("transaction_id", transaction_id);
		this.runSqlInsert("Transaction", data);
		
		data = new HashMap<String, String>();		
		data.put("trans_list_id", trans_list_id);
		data.put("transaction_id", transaction_id);
		data.put("user_id", user_id);
		this.runSqlInsert("user_transactionlist", data);

		this.runSqlUpdate("course", 
				"current_number = " + current_number, 
				"course_id = " + course_id);

		data = new HashMap<String, String>();
		data.put("course_reserve_id", course_reserve_id);
		data.put("course_id", Integer.toString(course_id));
		data.put("transaction_id", transaction_id);
		this.runSqlInsert("course_reserve", data);
		
		this.runSqlUpdate("appuser", 
				"balance = " + (this.findBalance(Integer.parseInt(user_id)) + amount), 
				"user_id = " + user_id);
		
		return 0;
	}

	public ArrayList<ArrayList<String>> getCourseList() {
		String sql = "SELECT course_name, course_level " +
				"FROM course ";
		
		ArrayList<ArrayList<String>> result = runSqlRead(sql, 2);
		
		return result;
	}
	
	// sqlcodes.Court
	/**
	 * Returns the list of tuples of all courts
	 * @return COURT_ID, COURT_NUMBER, COURT_LOCATION
	 */
	public ArrayList<ArrayList<String>> getAllCourts() {
		String sql = "SELECT court_id, court_number, court_location " +
				"FROM court";
		
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("COURT_ID");
		columnNames.add("COURT_NUMBER");
		columnNames.add("COURT_LOCATION");
		
		ArrayList<ArrayList<String>> result = runSqlRead(sql, 3);
		result.add(0, columnNames);
		
		return result;
	}

	/**
	 * Returns all available courts within the given time period
	 * @param start start time
	 * @param end end time
	 * @return COURT_ID, COURT_NUMBER, COURT_LOCATION
	 */
	public ArrayList<ArrayList<String>> getAllCourtsInTimePeriod(Timestamp start, Timestamp end) {
		String sql = "SELECT c.court_id, c.court_number, c.court_location " +
				"FROM court c " +
				"JOIN court_rental cr " +
				"ON c.court_id = cr.court_id " +
				"WHERE cr.start_date >= timestamp '" + start + "' " +
				"AND cr.end_date <= timestamp '" + end + "'";
		
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("COURT_ID");
		columnNames.add("COURT_NUMBER");
		columnNames.add("COURT_LOCATION");
		
		ArrayList<ArrayList<String>> result = runSqlRead(sql, 3);
		result.add(0, columnNames);
		
		return result;
	}


	/**
	 * Returns all reservation information of the specified court
	 * @param courtid
	 * @return COURT_ID, COURT_NUMBER, COURT_LOCATION, START_DATE, END_DATE
	 */
	public ArrayList<ArrayList<String>> getAllTimePeriodsForCourts(int courtid) {
		String sql = "SELECT c.court_id, c.court_number, c.court_location, cr.start_date, cr.end_date " +
				"FROM court_rental cr " +
				"JOIN court c " +
				"ON cr.court_id = c.court_id " + 
				"WHERE c.court_id = " + courtid;
		
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("COURT_ID");
		columnNames.add("COURT_NUMBER");
		columnNames.add("COURT_LOCATION");
		columnNames.add("START_DATE");
		columnNames.add("END_DATE");
		
		
		ArrayList<ArrayList<String>> result = runSqlRead(sql, 5);
		result.add(0, columnNames);
		
		return result;
	}

	public ArrayList<ArrayList<String>> getMinimumCourt(Timestamp start, Timestamp end) {
		
		String subsql1 = "SELECT c.court_location, count(*) as num " +
				"FROM court c " +
				"JOIN court_rental cr " +
				"ON cr.court_id = c.court_id " +
				"WHERE cr.start_date >= timestamp '" + start + "' " +
				"AND cr.end_date <= timestamp '" + end + "' " +
				"GROUP BY c.court_location";
		
		String subsql2 = "SELECT max(count(*)) " +
				"FROM court c2 " +
				"JOIN court_rental cr2 " +
				"ON cr2.court_id = c2.court_id " +
				"WHERE cr2.start_date >= timestamp '" + start + "' " +
				"AND cr2.end_date <= timestamp '" + end + "' " +
				"GROUP BY c2.court_location";
		
		String sql = "SELECT court_location, num " + 
				"FROM (" + subsql1 + ") " +
				"WHERE num >= (" + subsql2 + ")";	
	
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("LOCATION");
		columnNames.add("COUNT(NUM)");
		
		ArrayList<ArrayList<String>> result = runSqlRead(sql, 2);
		result.add(0, columnNames);
		
		return result;
	}
	
	public ArrayList<ArrayList<String>> getMaximumCourt(Timestamp start, Timestamp end) {
	
		String subsql1 = "SELECT c.court_location, count(*) as num " +
				"FROM court c " +
				"JOIN court_rental cr " +
				"ON cr.court_id = c.court_id " +
				"WHERE cr.start_date >= timestamp '" + start + "' " +
				"AND cr.end_date <= timestamp '" + end + "' " +
				"GROUP BY c.court_location";
		
		String subsql2 = "SELECT min(count(*)) " +
				"FROM court c2 " +
				"JOIN court_rental cr2 " +
				"ON cr2.court_id = c2.court_id " +
				"WHERE cr2.start_date >= timestamp '" + start + "' " +
				"AND cr2.end_date <= timestamp '" + end + "' " +
				"GROUP BY c2.court_location";
		
		String sql = "SELECT court_location, num " + 
				"FROM (" + subsql1 + ") " +
				"WHERE num <= (" + subsql2 + ")";	
		
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("LOCATION");
		columnNames.add("COUNT(NUM)");
		
		ArrayList<ArrayList<String>> result = runSqlRead(sql, 2);
		result.add(0, columnNames);
			
		return result;
	}

	/**
	 * Rent the court
	 * @param login_id
	 * @param court_number
	 * @param location
	 * @param amount
	 * @param start
	 * @param end
	 * @return
	 */
	public int rentCourt(String login_id, int court_number, String location, int amount, Timestamp start, Timestamp end) {
			
		ArrayList<Integer> maxids = findMaxIDs();
		String transaction_id = Integer.toString(maxids.get(0) + 1);
		String reservation_id = Integer.toString(maxids.get(1) + 1);
		String court_rental_id = Integer.toString(maxids.get(2) + 1);
		String trans_list_id = Integer.toString(maxids.get(3) + 1);
		String trans_amt = Integer.toString(amount);
		String c_id = Integer.toString(this.findCourtID(court_number, location));
		String user_id = this.findUserID(login_id);
				
		Map<String, String> data = new HashMap<String, String>();
		data.put("transaction_amount", trans_amt);
		data.put("transaction_id", transaction_id);
		this.runSqlInsert("Transaction", data);
		
		data = new HashMap<String, String>();		
		data.put("trans_list_id", trans_list_id);
		data.put("transaction_id", transaction_id);
		data.put("user_id", user_id);
		this.runSqlInsert("user_transactionlist", data);
		
		data = new HashMap<String, String>();		
		data.put("court_rental_id", court_rental_id);
		data.put("start_date", "timestamp " + quote(start.toString()));	
		data.put("end_date", "timestamp " + quote(end.toString()));
		data.put("for_course", quote("F"));
		data.put("court_id", c_id);
		data.put("is_wait_list", quote("F"));
		data.put("trans_list_id", trans_list_id);
		this.runSqlInsert("court_rental", data);
		
		data = new HashMap<String, String>();		
		data.put("reservation_id", reservation_id);
		data.put("trans_list_id", trans_list_id);
		data.put("court_rental_id", court_rental_id);
		this.runSqlInsert("reservation", data);

		data = new HashMap<String, String>();		
		data.put("reservation_id", reservation_id);
		data.put("court_rental_id", court_rental_id);
		data.put("trans_list_id", trans_list_id);
		this.runSqlInsert("account_payment", data);	
		
		this.runSqlUpdate("appuser", 
				"balance = " + (this.findBalance(Integer.parseInt(user_id)) + amount), 
				"user_id = " + user_id);

		return 0;
	}

	public ArrayList<ArrayList<String>> verifyTimes(Timestamp start, Timestamp end) {
		String sql = "SELECT cr.court_id" +
				" FROM Court_Rental cr" +
				" WHERE cr.start_date <= timestamp '" + start + "'" +
				" AND cr.end_date >= timestamp '" + end + "'";
		
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("COURT_ID");
		
		ArrayList<ArrayList<String>> result = runSqlRead(sql, 1);
		result.add(0, columnNames);
		
		return result;
	}

	
	
	// sqlcodes.User

	public int cancelCourseReservation(int transaction_id, String reason) {
		
		String cancel_id = Integer.toString(this.findNewCancelID());
		ArrayList<Integer> tmpdata = this.getInfoForCancelCourse(transaction_id);
		String user_id = Integer.toString(tmpdata.get(0));
		String cancel_amount = Integer.toString(tmpdata.get(1));
		String course_id = Integer.toString(tmpdata.get(2));
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("cancel_id", cancel_id);
		data.put("cancel_reason", quote(reason));
		data.put("cancel_amount", cancel_amount);
		data.put("cancel_date", "timestamp '" + 
				new Timestamp(new Date().getTime()).toString() + "'");
		data.put("user_id", user_id);
		
		this.runSqlInsert("cancel", data);
		
		this.runSqlDelete("Course_Reserve cr",
				"cr.transaction_id = " + transaction_id, 
				"");
		
		this.runSqlUpdate("course c", 
				"c.current_number = " + (this.findCourseCurrentNumber(tmpdata.get(2))-1), 
				"c.course_id = " + course_id);
		
		this.runSqlDelete("user_transactionlist utl",
				"utl.transaction_id = " + transaction_id, "");
		
		this.runSqlDelete("transaction t", 
				"t.transaction_id = " + transaction_id, "");
		
		this.runSqlUpdate("appuser a", 
				"a.balance = " + (this.findBalance(tmpdata.get(0)) + Integer.parseInt(cancel_amount)),
				"a.user_id = " + user_id);
		
		return 0;
	}

	public int cancelCourtRental(int transaction_id, String reason) {
		
		String cancel_id = Integer.toString(this.findNewCancelID());
		ArrayList<Integer> tmpdata = this.getInfoForCancelCourt(transaction_id);
		String reservation_id = Integer.toString(tmpdata.get(0));
		String court_rental_id = Integer.toString(tmpdata.get(1));
		String user_id = Integer.toString(tmpdata.get(2));
		String cancel_amount = Integer.toString(tmpdata.get(3));
		String trans_list_id = Integer.toString(tmpdata.get(4));
		
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("cancel_id", cancel_id);
		data.put("cancel_reason", quote(reason));
		data.put("cancel_amount", cancel_amount);
		data.put("cancel_date", "timestamp '" + 
				new Timestamp(new Date().getTime()).toString() + "'");
		data.put("user_id", user_id);
		
		this.runSqlInsert("cancel", data);
		
		this.runSqlDelete("Account_Payment ap", 
				"ap.reservation_id = " + reservation_id + 
				" AND ap.court_rental_id = " + court_rental_id +
				" AND ap.trans_list_id = " + trans_list_id ,
				"");
	
		this.runSqlDelete("Reservation r",
				"r.reservation_id = " + reservation_id, "");
			
		this.runSqlDelete("court_rental cr",
				"cr.court_rental_id = " + court_rental_id, "");
	
		this.runSqlDelete("user_transactionlist utl",
				"utl.transaction_id = " + transaction_id, "");
		
		this.runSqlDelete("transaction t", 
				"t.transaction_id = " + transaction_id, "");
		
		this.runSqlUpdate("appuser a", 
				"a.balance = " + (this.findBalance(tmpdata.get(2)) + Integer.parseInt(cancel_amount)),
				"a.user_id = " + user_id);
		
		return 0;
		
	}
	
	public int createNewUser(String loginid, String passwd, String balance, String address, String name) {
		
		Map<String,String> data = new HashMap<String, String>();	
		data.put("user_id", Integer.toString(this.findNewUserID()));
		data.put("password", quote(passwd));
		data.put("login_id", quote(loginid));
		data.put("balance", balance);
		data.put("address", quote(address));
		data.put("name", quote(name));

		runSqlInsert("AppUser", data);
		
		return 0; // successful
	}
	
	public ArrayList<ArrayList<String>> getAllReservationsUnderUser(int user_id) {
		String sql = "SELECT reservation_id, trans_list_id, court_rental_id " +
				"FROM reservation r " +
				"JOIN user_transactionlist utl " +
				"ON r.trans_list_id = utl.trans_list_id " +
				"WHERE utl.user_id = " + user_id;
		
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("RESERVATION_ID");
		columnNames.add("TRANS_LIST_ID");
		columnNames.add("COURT_RENTAL_ID");
		
		ArrayList<ArrayList<String>> result = runSqlRead(sql, 3);
		result.add(0, columnNames);
		
		return result;
	}

	public ArrayList<ArrayList<String>> getUsersWithAllCourts() {
		String subsql2 = "SELECT distinct c.court_location" +
				" FROM Court c" +
				" JOIN Court_Rental cr" +
				" ON c.court_id = cr.court_id" +
				" JOIN User_TransactionList utl" +
				" ON cr.trans_list_id = utl.trans_list_id" +
				" WHERE utl.user_id = utlout.user_id";
		
		String subsql1 = "SELECT distinct cmid.court_location, utlmid.user_id" +
				" FROM Court cmid" +
				" JOIN Court_Rental crmid" +
				" ON crmid.court_id = cmid.court_id" +
				" JOIN User_TransactionList utlmid" +
				" ON utlmid.trans_list_id = crmid.trans_list_id" +
				" WHERE cmid.court_location" +
				" NOT IN (" + subsql2 + ")";
		
		String sql = "SELECT distinct utlout.user_id" +
				" FROM User_TransactionList utlout" +
				" WHERE NOT EXISTS (" + subsql1 + ")";
		
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("USER_ID");
		
		ArrayList<ArrayList<String>> result = runSqlRead(sql, 1);
		result.add(0, columnNames);
		
		return result;
	}
	
	public int updateUserAddress(String login_id, String address) {
	
		runSqlUpdate("AppUser a", "a.address = " + quote(address), 
				"a.user_id = " + quote(this.findUserID(login_id)));
		
		// TODO: return what?
		return 0;
		
	}
	
	
	/* helper methods */

	public ArrayList<ArrayList<String>> getAllCourseID() {
		String sql = "SELECT course_id " +
				"FROM course";
		
		return runSqlRead(sql, 1);
	}
	
	public ArrayList<ArrayList<String>> getAllCourtID() {
		String sql = "SELECT court_id " +
				"FROM court";
		
		return runSqlRead(sql, 1);
	}
	
	public ArrayList<ArrayList<String>> getCoachWithSmallestCourse() {
		String sql = "SELECT coach_id, min(current_number) " +
				"FROM course c " +
				"GROUP by coach_id";
		
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("COACH_ID");
		columnNames.add("MIN(CURRENT_NUMBER)");
		
		ArrayList<ArrayList<String>> result = runSqlRead(sql, 2);
		result.add(0, columnNames);
		
		return result;
				
	}
		
	/**
	 * Helper function to receive 2-D ArrayList result 
	 * @param sql	SQL query to run
	 * @param numColumn	The number of columns to be received
	 * @return 2-D ArrayList result for the query 
	 */
	private ArrayList<ArrayList<String>> runSqlRead(String sql, int numColumn) {
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("Running SQL: " + sql);
			ResultSet rs = ps.executeQuery();
			
			ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
			
			while (rs.next()) {
				ArrayList<String> tuple = new ArrayList<String>();
				for (int i=1; i <= numColumn; i++) {
					tuple.add(rs.getString(i));
				}
				result.add(tuple);
			}
				
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;	
	}

	/**
	 * Takes the name of table and the input data, run sql insert query
	 * @param table name of the table 
	 * @param data Map<String column, String value>: name of the column and the corresponding value 
	 * @return number of rows processed
	 */
	private int runSqlInsert(String table, Map<String, String> data) {
		String sql = "INSERT INTO ";
		String columns = "(";
		String values = "VALUES (";
			
		for (String s: data.keySet()) {
			columns = columns + s + ",";
			values = values + data.get(s) + ",";
		}
		columns = columns.substring(0, columns.length() - 1) + ") ";
		values = values.substring(0,  values.length() - 1) + ")";
		
		sql = sql + table + columns + values;
		//System.out.println(sql);
		
		System.out.println("Running SQL: " + sql);

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			int result = ps.executeUpdate();
			con.commit();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// shouldn't get here
		return 0;
	}
	/**
	 * Takes the name of table and the input data, run sql insert query
	 * @param table name of the table 
	 * @param data Map<String column, String value>: name of the column and the corresponding value 
	 * @return number of rows processed
	 */
	private int runSqlUpdate(String table, String set, String where) {
		
		set = " SET " + set;
		where = " WHERE " + where;
		String sql = "UPDATE " + table + set + where;
		
		System.out.println("Running SQL: " + sql);
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			int result = ps.executeUpdate();
			con.commit();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}
	private int runSqlDelete(String table, String where, String statements) {
		
		String sql = "DELETE FROM " + table +
				" WHERE " + where + " " + statements;
		
		System.out.println("Running SQL: " + sql);
		
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			int result = ps.executeUpdate();
			con.commit();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return 0;
	}

	// sql test methods
	
	private int runSqlUpdate_test(String table, String set, String where) {
		
		set = " SET " + set;
		where = " WHERE " + where;
		String sql = "UPDATE " + table + set + where;
		
		System.out.println("Running SQL: " + sql);
		
		return 0;
	}
	private int runSqlInsert_test(String table, Map<String, String> data) {
		String sql = "INSERT INTO ";
		String columns = "(";
		String values = "VALUES (";
			
		for (String s: data.keySet()) {
			columns = columns + s + ",";
			values = values + data.get(s) + ",";
		}
		columns = columns.substring(0, columns.length() - 1) + ") ";
		values = values.substring(0,  values.length() - 1) + ")";
		
		sql = sql + table + columns + values;
		//System.out.println(sql);
		
		System.out.println("Running SQL: " + sql);
		
		// shouldn't get here
		return 0;
	}
	private int runSqlDelete_test(String table, String where, String statements) {
		
		String sql = "DELETE FROM " + table + 
				" WHERE " + where + " " + statements;
		
		System.out.println("Running SQL: " + sql);
				
		return 0;
	}
	
	/**
	 * Return the max ID numbers 
	 * @return MAX(transaction_id), MAX(reservation_id), MAX(court_rental_id), 
	 * 		MAX(trans_list_id), MAX(course_reserve_id)
	 */
	private ArrayList<Integer> findMaxIDs() {
		String sql = "SELECT MAX(utl.transaction_id), MAX(r.reservation_id)," +
				" MAX(cr.court_rental_id), MAX(utl.trans_list_id)," +
				" MAX(cre.course_reserve_id)" +
				" FROM Transaction t" +
				" FULL OUTER JOIN user_transactionlist utl" +
				" ON utl.transaction_id = t.transaction_id" +
				" FULL OUTER JOIN Reservation r" +
				" ON utl.trans_list_id = r.trans_list_id" +
				" FULL OUTER JOIN Court_Rental cr" +
				" ON cr.trans_list_id = r.trans_list_id" +
				" FULL OUTER JOIN course_reserve cre" +
				" ON cre.transaction_id = t.transaction_id";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("Running SQL: " + sql);
			ResultSet rs = ps.executeQuery();
			
			//ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> tuple = new ArrayList<Integer>();
			while (rs.next()) {
				
				for (int i=1; i <= 5; i++) {
					tuple.add(rs.getInt(i));
					System.out.println(tuple.get(i-1));
				}
				//result.add(tuple);
			}
				
			return tuple;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Return the maximum cancel ID, and return ID+1 
	 * @return 
	 */
	private int findNewCancelID() {
		String sql = "SELECT MAX(cancel_id) from cancel";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("Running SQL: " + sql);
			ResultSet rs = ps.executeQuery();
			
			//ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
			int result = 0;
			while (rs.next()) {	
				result = rs.getInt(1);
			}
				
			return result+1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	private int findNewUserID() {
		String sql = "SELECT MAX(user_id) from appuser";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("Running SQL: " + sql);
			ResultSet rs = ps.executeQuery();
			
			//ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
			int result = 0;
			while (rs.next()) {	
				result = rs.getInt(1);
			}
				
			return result+1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	private String findUserID(String login_id) {
		String sql = "SELECT user_id from appuser where login_id = " + quote(login_id);
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("Running SQL: " + sql);
			ResultSet rs = ps.executeQuery();
			
			//ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
			String result = "";
			while (rs.next()) {	
				result = rs.getString(1);
			}
				
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	private int findBalance(int user_id) {
		String sql = "SELECT balance FROM appuser" +
				" WHERE user_id = " + user_id;
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("Running SQL: " + sql);
			ResultSet rs = ps.executeQuery();
			
			int result = 0;
			while (rs.next()) {	
				result = rs.getInt(1);
			}
				
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	private int findCourseCurrentNumber(int course_id) {
		String sql = "SELECT current_number FROM course " +
				"WHERE course_id = " + course_id;
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("Running SQL: " + sql);
			ResultSet rs = ps.executeQuery();
			
			int result = 0;
			while (rs.next()) {	
				result = rs.getInt(1);
			}
				
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;	
	}
	
	private int findCourtID(int court_number, String location) {
		String sql = "SELECT court_id FROM court " +
				"WHERE court_location = " + quote(location) +
				" AND court_number = " + court_number;
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("Running SQL: " + sql);
			ResultSet rs = ps.executeQuery();
			
			int result = 0;
			while (rs.next()) {	
				result = rs.getInt(1);
			}
				
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;	
	}
	
	private int findCourseID(String course_name) {
		String sql = "SELECT course_id FROM course " +
				"WHERE course_name = " + quote(course_name);
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("Running SQL: " + sql);
			ResultSet rs = ps.executeQuery();
			
			int result = 0;
			while (rs.next()) {	
				result = rs.getInt(1);
			}
				
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;	
	}
	
	/**
	 * Return the corresponding data to the transaction_id
	 * @param user_id
	 * @return RESERVATION_ID, COURT_RENTAL_ID, USER_ID, TRANSACTION_AMOUNT, TRANS_LIST_ID
	 */
	private ArrayList<Integer> getInfoForCancelCourt(int transaction_id) {
		String sql = "SELECT reservation_id, court_rental_id, user_id, " +
				"transaction_amount, utl.trans_list_id" +
				" FROM user_transactionlist utl" +
				" JOIN reservation r" +
				" ON utl.trans_list_id = r.trans_list_id" +
				" JOIN AppUser a" +
				" ON a.user_id = utl.user_id" +
				" JOIN transaction t" +
				" ON t.transaction_id = utl.transaction_id" +
				" JOIN court_rental cre " +
				" ON cre.court_rental_id = r.court_rental_id" +
				" WHERE transaction_id = " + transaction_id;
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("Running SQL: " + sql);
			ResultSet rs = ps.executeQuery();
			
			//ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> tuple = new ArrayList<Integer>();
			while (rs.next()) {
				
				for (int i=1; i <= 5; i++) {
				tuple.add(rs.getInt(i));
				}
				//result.add(tuple);
			}
				
			return tuple;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private ArrayList<Integer> getInfoForCancelCourse(int transaction_id) {
		String sql = "SELECT a.user_id, " +
				"t.transaction_amount, cre.course_id" +
				" FROM user_transactionlist utl" +
				" JOIN AppUser a" +
				" ON a.user_id = utl.user_id" +
				" JOIN transaction t" +
				" ON t.transaction_id = utl.transaction_id" +
				" JOIN course_reserve cre " +
				" ON cre.transaction_id = utl.transaction_id" +
				" WHERE transaction_id = " + transaction_id;
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("Running SQL: " + sql);
			ResultSet rs = ps.executeQuery();
			
			//ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> tuple = new ArrayList<Integer>();
			while (rs.next()) {
				
				for (int i=1; i <= 3; i++) {
				tuple.add(rs.getInt(i));
				}
				//result.add(tuple);
			}
				
			return tuple;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** 
	 * Return the list of transaction IDs which belong to specified user
	 * @param user_id 
	 * @param option 0 for all transactions, 1 for court only, 2 for course only
	 * @return ArrayList of transaction IDs
	 */
	public ArrayList<Integer> getListOfTransactionID(String login_id, int option) {
		String sql = "SELECT utl.transaction_id " +
				"FROM User_Transactionlist utl " +
				"WHERE utl.user_id = " + findUserID(login_id);
		
		if (option > 0) { // court or course transactions only, 1=court, 2=course
			sql += " AND utl.transaction_id " + ((option==1)? "NOT":"") + " IN (" +
					"SELECT transaction_id " +
					"FROM course_reserve)";
		}
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("Running SQL: " + sql);
			ResultSet rs = ps.executeQuery();
			
			//ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> tuple = new ArrayList<Integer>();
			while (rs.next()) {
				
				tuple.add(rs.getInt(1));
				//result.add(tuple);
			}
				
			return tuple;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Returns the list of all user IDs
	 * @return ArrayList of user_id
	 */
	public ArrayList<String> getListOfLoginID() {
		String sql = "SELECT login_id " +
				"FROM appuser";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("Running SQL: " + sql);
			ResultSet rs = ps.executeQuery();
			
			//ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
			ArrayList<String> tuple = new ArrayList<String>();
			while (rs.next()) {
				
				tuple.add(rs.getString(1).replace(" ", ""));
				//result.add(tuple);
			}
				
			return tuple;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Checks if the user_id exists in the database
	 * @param user_id
	 * @return true if it exists, false otherwise
	 */
	public boolean checkUserID(String login_id) {
		return this.getListOfLoginID().contains(login_id);
	}
	
	/**
	 * Checks the password of login_id
	 * @param login_id
	 * @param password
	 * @return true if the password is correct
	 */
	public boolean checkPassword(String login_id, String password) {
		return password.equals(this.retrievePassword(login_id));
	}
	
	private String retrievePassword(String login_id) {
		String sql = "SELECT password FROM appuser " +
				"WHERE lower(login_id) = lower(" + this.quote(login_id) + ")";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("Running SQL: " + sql);
			ResultSet rs = ps.executeQuery();
			
			String result = "";
			while (rs.next()) {	
				result = rs.getString(1);
			}
//			System.out.println(result);
			return result.replace(" ", "");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String quote(String s) {
		return "'" + s + "'";
	}
	
	public static void main(String args[]) {
		DbHandler db = DbHandler.getInstance();
		db.connect("localhost", "ora_i5l4", "a10653038");
//		db.connect("localhost", "ora_s8g7", "a24595092");
//		db.connect("localhost", "ora_e1d7", "a21856075");
		
//		Vector<String> tables = db.getTables();
//		for (int i=0; i<tables.size(); i++) 
//			System.out.println(tables.get(i));

//		db.createNewUser("abd", "aaa", "111", "home", "guy");
		
//		db.rentCourt("thesunrise", 4011, 100, ts1, ts2);
//		db.reserveCourse("thesunrise", 8001, 500);

//		db.cancelCourseReservation(1020, "bull");
//		db.cancelCourtRental(1001, "just because");

		Timestamp ts1 = new Timestamp(107, 3, 10, 9, 0, 0, 0);
		Timestamp ts2 = new Timestamp(127, 3, 10, 12, 0, 0, 0);
		for (ArrayList a: db.getMaximumCourt(ts1, ts2)) {
			for (Object s: a) {
				System.out.print(s + "\t");
			}
			System.out.println("");
		}	
		
		for (ArrayList a: db.getMinimumCourt(ts1, ts2)) {
			for (Object s: a) {
				System.out.print(s + "\t");
			}
			System.out.println("");
		}	
		
//		for (ArrayList a: db.getUsersWithAllCourts()) {
//			for (Object s: a) {
//				System.out.print(s + "\t");
//			}
//			System.out.println("");
//		}		
		
//		for (int i: db.getListOfTransactionID("ab", 2)) System.out.println(i);
//		System.out.println(db.checkUserID("poliu"));
//		db.initConstraints();
//		db.enableConstraints();
				
		
//		Map<String, String> map = new HashMap<String, String>();
//		
//		map.put("user_id", "123");
//		map.put("balance", "3456");
//		
//		db.runSqlInsert("aa", map); 
		
		
		db.disconnect();
		
	} 
}