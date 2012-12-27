package main.gui;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.sql.Timestamp;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import main.database.DbHandler;
import main.gui.TableModel;
import main.shared.Converter;

import main.gui.*;

public class AppaApplet extends Applet implements ActionListener {
	
	DbHandler db;
	
	// Panels used for different sections of gui
	JPanel searchByPane;
	JPanel searchPane;
	JPanel outputPane;
	JPanel bookingPane;
	JPanel accountPane;
	
	
	// Components inside the different sections
	JLabel court[];
	JLabel courtStatus[];
	String courtAvailability;

	JRadioButton searchRadioGetAllCourts;
	JRadioButton searchRadioGetAllCentersAndCourts;
	JRadioButton searchRadioGetSmallestCourseForCoach;
	JRadioButton searchRadioGetSmallestPeopleCourt;
	JRadioButton searchRadioGetLargestPeopleCourt;
	
	JRadioButton searchRadioButtonDateCourse;
	JRadioButton searchRadioButtonDateCourt;
	JRadioButton searchRadioButtonCourt;
	JRadioButton searchRadioButtonCourse;
	JRadioButton searchRadioGetUsersWithAllCourts;
	JButton bookCourtButton;
	JButton createAccountButton;
	JLabel loginInfo;
	
	
	// Components for search (input) Pane
	JLabel inputCourtLabel;
	JLabel inputCourseLabel; 	
	JTextField inputCourts;
	JTextField inputCourse;
	JFormattedTextField inputYearStart = new JFormattedTextField("YYYY");
	JFormattedTextField inputYearEnd = new JFormattedTextField("YYYY");
	JFormattedTextField inputMonthStart = new JFormattedTextField("MM");
	JFormattedTextField inputMonthEnd = new JFormattedTextField("MM");
	JFormattedTextField inputDayStart = new JFormattedTextField("DD");
	JFormattedTextField inputDayEnd = new JFormattedTextField("DD");
	JFormattedTextField inputHourStart = new JFormattedTextField("HH");
	JFormattedTextField inputHourEnd = new JFormattedTextField("HH");
	String[] halfHours = {"00", "30"};
	JComboBox inputMinuteStart = new JComboBox(halfHours);
	JComboBox inputMinuteEnd = new JComboBox(halfHours);
	
	
	
	// ------------ Components for bookingPane -------------------
	JTextField filler;
	JTextField filler2;
	JTextField filler3;
	JTextField filler4;
	JLabel bpStartYearLabel = new JLabel("Start Year: ");
	JLabel bpEndYearLabel = new JLabel("End Year: ");
	JLabel bpStartMonthLabel = new JLabel("Start Month: ");
	JLabel bpEndMonthLabel = new JLabel("End Month: ");
	JLabel bpStartDayLabel = new JLabel("Start Day: ");
	JLabel bpEndDayLabel = new JLabel("End Day: ");
	JLabel bpStartHourLabel = new JLabel("Start Hour: ");
	JLabel bpEndHourLabel = new JLabel("End Hour: ");
	JLabel bpStartMinuteLabel = new JLabel("Start Minute: ");
	JLabel bpEndMinuteLabel = new JLabel("End Minute: ");
	JLabel bpAmountLabel = new JLabel("Amount: ");
	
	JTextField bpStartYear = new JTextField();
	JTextField bpEndYear = new JTextField();
	JTextField bpStartMonth = new JTextField();
	JTextField bpEndMonth = new JTextField();
	JTextField bpStartDay = new JTextField();
	JTextField bpEndDay = new JTextField();
	JTextField bpStartHour = new JTextField();
	JTextField bpEndHour = new JTextField();
	JComboBox bpStartMinute = new JComboBox(halfHours);
	JComboBox bpEndMinute = new JComboBox(halfHours);
	JTextField bpAmount = new JTextField();
	
	
	JLabel bpCourtNumberLabel = new JLabel("Court Number: ");
	JLabel bpCourtLocationLabel = new JLabel("Court Location: ");

	
	JComboBox bpCourtLocation = new JComboBox();	
	JComboBox bpCourtNumber = new JComboBox();		
	JButton bpBookButton = new JButton("Done");
	JButton bpCancelButton = new JButton();
	// -----------End bookingPane components--------------------------
	
	//Components for account creation pane
	JLabel aUsernameLabel = new JLabel("Username: ");
	JLabel aPasswordLabel = new JLabel("Password: ");
	JLabel aAddressLabel = new JLabel("Address: ");
	JLabel aNameLabel = new JLabel("Name: ");
	JLabel aBalanceLabel = new JLabel("Balance: ");
	JTextField aUsername= new JTextField();
	JTextField aPassword= new JTextField();
	JTextField aAddress= new JTextField();
	JTextField aName= new JTextField();
	JTextField aBalance = new JTextField();
	JButton aCreateAccount = new JButton("Create Account");
	
	String userID;
	String userPassword;
	int userLoginButton; 		// 0 = ok, 1 = cancel
    protected static final String textFieldString = "JTextField";
	
    // Cancellation GUI
    JButton cancelCourtRental;
    JPanel cancelPane;
    JTextField cReason;
    JComboBox cTransactionIDs;
    JComboBox cCourtOrCourse;
    
    // Court reservation pane
    JPanel reservePane;
    JButton crMainReserveCourseButton = new JButton("Reserve a Course");
    JButton crReserveCourseButton = new JButton("Reserve Course");
    JButton crCancelButton = new JButton("Cancel");
    
    
    JTextField crAmount = new JTextField();
    JTextField crYearStart = new JTextField("YYYY");
    JTextField crYearEnd = new JTextField("YYYY");
    JTextField crMonthStart = new JTextField("MM");
    JTextField crMonthEnd = new JTextField("MM");
    JTextField crDayStart = new JTextField("DD");
    JTextField crDayEnd = new JTextField("DD");
    JTextField crHourStart = new JTextField("HH");
    JTextField crHourEnd = new JTextField("HH");
    JComboBox crMinuteStart = new JComboBox(halfHours);
    JComboBox crMinuteEnd = new JComboBox(halfHours);
    String[] ourCourses = {"class 304:Intermediate", 
    		"class 305:Beginner", 
    		"class 306:Advanced", 
    		"class 307:Beginner", 
    		"class 308:Intermediate", 
    		"class 309:Intermediate"};
    JComboBox crCourses = new JComboBox(ourCourses);
    
    
    // Update User Address Pane
    JPanel updatePane;
    JButton uMainUpdateAddressButton = new JButton("Update My Address");
    JButton uUpdate = new JButton("Update");
    JButton uCancel = new JButton("Cancel");
    
    JTextField uNewAddress = new JTextField();
    
    
    
	public AppaApplet() {

		// Set up the DBHandler
		db = DbHandler.getInstance();
		db.connect("localhost", "ora_i5l4", "a10653038");
	}

	public void init() {
		setSize(500,500);
		court = new JLabel[15];
		courtStatus = new JLabel[15];
		inputCourts = new JTextField(5);
		inputCourse = new JTextField(5);


		searchRadioGetAllCourts = new JRadioButton();
		searchRadioGetAllCentersAndCourts = new JRadioButton();
		searchRadioGetSmallestCourseForCoach = new JRadioButton();
		searchRadioGetSmallestPeopleCourt = new JRadioButton();
		
		searchRadioButtonDateCourse = new JRadioButton();
		searchRadioButtonDateCourt = new JRadioButton();
		searchRadioButtonCourt = new JRadioButton();
		searchRadioButtonCourse = new JRadioButton();
		searchRadioGetLargestPeopleCourt = new JRadioButton();
		searchRadioGetUsersWithAllCourts = new JRadioButton();
		
		
	    
		
		//----------------Search Selection Pane------------------------------
		searchByPane = new JPanel();
		searchByPane.setLayout(new GridLayout(10,2));
		searchByPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Search Selection"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(searchRadioGetAllCourts);
		group.add(searchRadioGetAllCentersAndCourts);
		group.add(searchRadioGetSmallestCourseForCoach);
		group.add(searchRadioGetSmallestPeopleCourt);
		group.add(searchRadioGetLargestPeopleCourt);
		group.add(searchRadioButtonDateCourse);
		group.add(searchRadioButtonDateCourt);
		group.add(searchRadioButtonCourt);
		group.add(searchRadioButtonCourse);
		group.add(searchRadioGetUsersWithAllCourts);
		
		//Register a listener for the radio buttons
		searchRadioGetAllCourts.addActionListener(this);
		searchRadioGetAllCentersAndCourts.addActionListener(this);
		searchRadioGetSmallestCourseForCoach.addActionListener(this);
		searchRadioGetSmallestPeopleCourt.addActionListener(this);
		searchRadioGetLargestPeopleCourt.addActionListener(this);
		searchRadioButtonDateCourt.addActionListener(this);
		searchRadioButtonDateCourse.addActionListener(this);
		searchRadioButtonCourt.addActionListener(this);
		searchRadioButtonCourse.addActionListener(this);
		searchRadioGetUsersWithAllCourts.addActionListener(this);
		
		JLabel searchGetAllCourts = new JLabel("Get all courts: ");
		JLabel searchGetAllCentersAndCourts = new JLabel("Get all centers: ");
		JLabel searchGetSmallestCourseForCoach = new JLabel("Get course with fewest people for each coach: ");
		JLabel searchGetSmallestPeopleCourt = new JLabel("Get court with least people given time: ");
		JLabel searchGetLargestPeopleCourt = new JLabel("Get court with most people given time: ");
		JLabel searchDateCourse = new JLabel("Get courses for given times: ");
		JLabel searchDateCourt = new JLabel("Get courts for given times: ");
		JLabel searchCourse = new JLabel("Get times for a given course: ");
		JLabel searchCourt = new JLabel("Get times for a given court: ");
		JLabel searchRadioGetUsersWithAllCourtsLabel = new JLabel("Get users with all courts: ");
		
		searchByPane.add(searchGetAllCourts);
		searchByPane.add(searchRadioGetAllCourts);
		searchByPane.add(searchGetAllCentersAndCourts);
		searchByPane.add(searchRadioGetAllCentersAndCourts);
		searchByPane.add(searchGetSmallestCourseForCoach);
		searchByPane.add(searchRadioGetSmallestCourseForCoach);
		searchByPane.add(searchGetSmallestPeopleCourt);
		searchByPane.add(searchRadioGetSmallestPeopleCourt);
		searchByPane.add(searchGetLargestPeopleCourt);
		searchByPane.add(searchRadioGetLargestPeopleCourt);
		searchByPane.add(searchDateCourse);
		searchByPane.add(searchRadioButtonDateCourse);
		searchByPane.add(searchDateCourt);
		searchByPane.add(searchRadioButtonDateCourt);
		searchByPane.add(searchCourt);
		searchByPane.add(searchRadioButtonCourt);
		searchByPane.add(searchCourse);
		searchByPane.add(searchRadioButtonCourse);
		searchByPane.add(searchRadioGetUsersWithAllCourtsLabel);
		searchByPane.add(searchRadioGetUsersWithAllCourts);
		add(searchByPane);		
		
		// -----------------End of Search By Pane -------------------------
		
		
		// ---------------------- Search Input Pane ------------------------
		searchPane = new JPanel();
		// ---------------- End Search Input Pane ------------------------
	

		
		// ------------------- Output Pane---------------------------------------
/*		
		// Sample Output
				ArrayList<ArrayList<String>> test = new ArrayList<ArrayList<String>>();
				for(int j = 0; j < 4; j++) {
					ArrayList<String> value = new ArrayList<String>();
					value.add("First");
					value.add("Second");
					value.add("Thirds");
					value.add("Fourth");
					test.add(j, value);
					
				}
		TableModel tm = new TableModel(test);
		
		outputPane = new JPanel();
		outputPane.setLayout(new GridLayout(tm.getRowCount()+1, tm.getColumnCount()));
		outputPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Output"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		
		//ArrayList<ArrayList<JLabel>> records = new ArrayList<ArrayList<JLabel>>();
		
		for (int i =0; i < tm.getColumnCount(); i++) {
			JLabel out1 = new JLabel(tm.getColumns().get(i));
			outputPane.add(out1);
		}
		
		
		for (int i = 0; i < tm.getRowCount(); i++) {
			for (int j = 0; j < tm.getColumnCount(); j++) {
				
				JLabel out2 = new JLabel(tm.getValueAt(i,j));
				outputPane.add(out2);
			}
			
		}*/
		
		outputPane = new JPanel();
		add(outputPane);
		// -------------------- End Output Pane --------------------------------
		
		
		bookCourtButton = new JButton("Book a court!");
		bookCourtButton.addActionListener(this);
		add(bookCourtButton);
		
		crMainReserveCourseButton = new JButton("Reserve a Course!");
		crMainReserveCourseButton.addActionListener(this);
		add(crMainReserveCourseButton);
		
		createAccountButton = new JButton("Create Account");
		createAccountButton.addActionListener(this);
		add(createAccountButton);
		
		cancelCourtRental = new JButton("Cancel a Rental or Reservation");
		cancelCourtRental.addActionListener(this);
		add(cancelCourtRental);
		
		uMainUpdateAddressButton.addActionListener(this);
		add(uMainUpdateAddressButton);
	
		
	
		
		//---------------------Booking Pane -------------------------------------------
		
		bookingPane = new JPanel();
		bookingPane.setLayout(new GridLayout(15, 2));
		bookingPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Court Booking"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		
		loginInfo = new JLabel("Login ID: ");
		bookingPane.add(loginInfo);
		filler = new JTextField();
		filler.setText(userID);
		filler.setEditable(false);
		bookingPane.add(filler);
		
		String[] myCenters = {"Richmond", "Chiliwack", "Burnaby",
				"Vancouver", "New West Minster", "Coquitlam",
				"Surrey", "Marine Drive", "Abbotsford",
				"Langley", "North Vancouver", "Aldergrove",
				"Port Coquitlam", "Delta", "White Rock"	};
		System.out.println(myCenters[1]);
		bpCourtLocation = new JComboBox(myCenters);
		bpCourtLocation.addItemListener(new ItemListener() {
	
			String[] myCenters = {"Richmond", "Chilliwack", "Burnaby",
					"Vancouver", "New West Minster", "Coquitlam",
					"Surrey", "Marine Drive", "Abbotsford",
					"Langley", "North Vancouver", "Aldergrove",
					"Port Coquitlam", "Delta", "White Rock"	};
			
			public void itemStateChanged(ItemEvent a) {
				if (a.getStateChange() == ItemEvent.SELECTED) {
					bpCourtNumber.removeAllItems();
					
					String item = (String) a.getItem();
					for (int i = 0; i< 15;i++) {
						
						if (item.compareTo(myCenters[i])==0) {
							ArrayList<ArrayList<String>> al = db.getAllCourtNumbersForLocation(myCenters[i]);
							for(ArrayList<String> i1: al) {
								for(String i2: i1) {
									bpCourtNumber.addItem(i2);
								}
							}
							
						}
						
					}
					validate();
				}			
				
			}
			
		});
		
		
		
		bookingPane.add(bpStartYearLabel);
		bookingPane.add(bpStartYear);
		bookingPane.add(bpEndYearLabel);
		bookingPane.add(bpEndYear);
		bookingPane.add(bpStartMonthLabel);
		bookingPane.add(bpStartMonth);
		bookingPane.add(bpEndMonthLabel);
		bookingPane.add(bpEndMonth);
		bookingPane.add(bpStartDayLabel);
		bookingPane.add(bpStartDay);
		bookingPane.add(bpEndDayLabel);
		bookingPane.add(bpEndDay);
		bookingPane.add(bpStartHourLabel);
		bookingPane.add(bpStartHour);
		bookingPane.add(bpEndHourLabel);
		bookingPane.add(bpEndHour);
		bookingPane.add(bpStartMinuteLabel);
		bookingPane.add(bpStartMinute);
		bookingPane.add(bpEndMinuteLabel);
		bookingPane.add(bpEndMinute);
		
		bookingPane.add(bpCourtLocationLabel);
		bookingPane.add(bpCourtLocation);
		bookingPane.add(bpCourtNumberLabel);
		bookingPane.add(bpCourtNumber);
		bookingPane.add(bpAmountLabel);
		bookingPane.add(bpAmount);
		
		
		bpBookButton.addActionListener(this);
		bpCancelButton.setText("Cancel");
		bpCancelButton.addActionListener(this);
		bookingPane.add(bpBookButton);
		bookingPane.add(bpCancelButton);
		
		add(bookingPane);
		bookingPane.setVisible(false);
		
		// -------------------End Booking Pane ------------------------------
		
		
		// -------------------Create Account Pane --------------------------
		accountPane = new JPanel();
		accountPane.setLayout(new GridLayout(6, 2));
		accountPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Create an Account"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		accountPane.add(aUsernameLabel);
		accountPane.add(aUsername);
		accountPane.add(aPasswordLabel);
		accountPane.add(aPassword);
		accountPane.add(aNameLabel);
		accountPane.add(aName);	
		accountPane.add(aAddressLabel);
		accountPane.add(aAddress);
		accountPane.add(aBalanceLabel);
		accountPane.add(aBalance);
		accountPane.add(aCreateAccount);
		
		aCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewUser();
			}
			}
		);
		
		JButton aCancel = new JButton("Cancel");
		aCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountPane.setVisible(false);
				add(createAccountButton);
				createAccountButton.setVisible(true);
				validate();
				
			}
			
		} 
		);
		accountPane.add(aCancel);
			
		
		add(accountPane);
		accountPane.setVisible(false);
		//--------------------End Create Account Pane-----------------------
		
		//--------------------Create Cancellation Pane----------------------
		cancelPane = new JPanel();
		cancelPane.setLayout(new GridLayout(5, 2));
		cancelPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Cancel Booking"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
	
		JLabel cUser = new JLabel("Username: ");
		cancelPane.add(cUser);
		
		filler2 = new JTextField();
		filler2.setText(userID);
		filler2.setEditable(false);
		cancelPane.add(filler2);
		
		JLabel cCourtOrCourseLabel = new JLabel("Court or Course? ");
		cancelPane.add(cCourtOrCourseLabel);
		String[] myCourtOrCourse = {"Court", "Course"};
		cCourtOrCourse = new JComboBox(myCourtOrCourse);
		cCourtOrCourse.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent a) {
				if (a.getStateChange() == ItemEvent.SELECTED) {
					
					String item = (String) a.getItem();
					if (item.compareTo("Court") == 0) {
						System.out.println("Changed court event");
						updateCourtTransactionList();
					}
					else if (item.compareTo("Course") == 0) {
						System.out.println("Changed course event");
						updateCourseTransactionList();
					}
				}			
				
			}
			
		});
		cancelPane.add(cCourtOrCourse);
		
		JLabel cTransactionIDLabel = new JLabel("Transaction ID: ");
		cancelPane.add(cTransactionIDLabel);
		cTransactionIDs = new JComboBox();	
		cancelPane.add(cTransactionIDs);
		//updateCourtTransactionList();
		
		JLabel cReasonLabel = new JLabel("Reason for cancellation: ");
		cancelPane.add(cReasonLabel);
		cReason = new JTextField();
		cancelPane.add(cReason);
		
		
		
		
		JButton cancelCourt = new JButton("Cancel Transaction");
		JButton closeCancel = new JButton("Exit");
		
		closeCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelPane.setVisible(false);
				validate();
				
			}			
		});
		
		// Where the action happens in the cancel pane
		cancelCourt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				if (cCourtOrCourse.getSelectedIndex() == 1) { ///  cTransactionIDs.getSelectedIndex() == 0
					db.cancelCourseReservation(Integer.parseInt((String)cTransactionIDs.getSelectedItem()), cReason.getText());
					
					JPanel frame = new JPanel();
					JOptionPane.showMessageDialog(frame,
						    "Your transaction was successfully cancelled.");
				}
				else {
					JPanel frame = new JPanel();
					JOptionPane.showMessageDialog(frame,
						    "Your transaction was successfully cancelled.");
					db.cancelCourtRental(Integer.parseInt((String)cTransactionIDs.getSelectedItem()), cReason.getText());
				}
				cancelPane.setVisible(false);
				validate();
				}
				catch (Exception e) {
					JPanel frame = new JPanel();
					JOptionPane.showMessageDialog(frame,
						    "Invalid input. Please try again!",
						    "Watch out",
						    JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
		
		cancelPane.add(cancelCourt);
		cancelPane.add(closeCancel);
		add(cancelPane);
		cancelPane.setVisible(false);
		//---------------------End Cancellation Pane-----------------------
		
		
		//--------------------Reserve course--------------------------------
		reservePane = new JPanel();
		reservePane.setLayout(new GridLayout(4, 2));	//12
		reservePane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Reserve a Course"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		
		JLabel crUserName = new JLabel("Username: ");
		reservePane.add(crUserName);
		
		filler3 = new JTextField();
		filler3.setText(userID);
		filler3.setEditable(false);
		reservePane.add(filler3);
		
		JLabel crCoursesLabel = new JLabel("Course: ");
		JLabel crAmountLabel = new JLabel("Amount: ");
		JLabel crYearStartLabel = new JLabel("Start Year: ");
		JLabel crYearEndLabel = new JLabel("End Year: ");
		JLabel crMonthStartLabel = new JLabel("Start Month: ");
		JLabel crMonthEndLabel = new JLabel("End Month: ");
		JLabel crDayStartLabel = new JLabel("Start Day: ");
		JLabel crDayEndLabel = new JLabel("End Day: ");
		JLabel crHourStartLabel = new JLabel("Start Hour: ");
		JLabel crHourEndLabel = new JLabel("End Hour: ");
		JLabel crMinuteStartLabel = new JLabel("Start Minute: ");
		JLabel crMinuteEndLabel = new JLabel("End Minute: ");
		
		reservePane.add(crCoursesLabel);
		reservePane.add(crCourses);
		reservePane.add(crAmountLabel);
		reservePane.add(crAmount);
		//reservePane.add(crYearStartLabel);
		//reservePane.add(crYearStart);
		//reservePane.add(crYearEndLabel);
		//reservePane.add(crYearEnd);
		//reservePane.add(crMonthStartLabel);
		//reservePane.add(crMonthStart);
		//reservePane.add(crMonthEndLabel);
		//reservePane.add(crMonthEnd);
		//reservePane.add(crHourStartLabel);
		//reservePane.add(crHourStart);
		//reservePane.add(crHourEndLabel);
		//reservePane.add(crHourEnd);
		//reservePane.add(crMinuteStartLabel);
		//reservePane.add(crMinuteStart);
		//reservePane.add(crMinuteEndLabel);
		//reservePane.add(crMinuteEnd);
		
		
		crReserveCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				try{
					// Check timestamps make sense
					/*
					 * Timestamp crStart = new Timestamp(
					 * Integer.parseInt(crYearStart.getText())-1900,
					 * Integer.parseInt(crMonthStart.getText()),
					 * Integer.parseInt(crDayStart.getText()),
					 * Integer.parseInt(crHourStart.getText()),
					 * Integer.parseInt(
					 * (String)crMinuteStart.getSelectedItem()), 0, 0);
					 * Timestamp crEnd = new Timestamp(
					 * Integer.parseInt(crYearEnd.getText())-1900,
					 * Integer.parseInt(crMonthEnd.getText()),
					 * Integer.parseInt(crDayEnd.getText()),
					 * Integer.parseInt(crHourEnd.getText()),
					 * Integer.parseInt((String)crMinuteEnd.getSelectedItem()),
					 * 0, 0);
					 */
					// Timestamps were not used because it was deprecated by
					// Akash Peri
					db.reserveCourse(userID,
							(String) crCourses.getSelectedItem(),
							Integer.parseInt(crAmount.getText()));

					JPanel frame = new JPanel();
					JOptionPane.showMessageDialog(frame,
							"Reservation was made successfully.");

					reservePane.setVisible(false);
					validate();
				} catch (Exception e) {
					JPanel frame = new JPanel();
					JOptionPane.showMessageDialog(frame,
						    "Reservation was unsuccessfully. Please try again.");
					
					reservePane.setVisible(false);
					validate();
				}
			}
		});
		
		crCancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reservePane.setVisible(false);
				validate();
				
			}
		});
		
		reservePane.add(crReserveCourseButton); 
		reservePane.add(crCancelButton);
		
		add(reservePane);
		reservePane.setVisible(false);
		
		//-------------------End course reservation pane-------------------------------------
		
		//----------------Update Address Pane-----------------------------------
		updatePane = new JPanel();
		updatePane.setLayout(new GridLayout(3, 2));
		updatePane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Update my address"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		
		JLabel uFiller = new JLabel("Username: ");
		JLabel newAddress = new JLabel("New Address: ");
		filler4 = new JTextField();
		filler4.setEditable(false);
		
		updatePane.add(uFiller);
		updatePane.add(filler4);
		updatePane.add(newAddress);
		updatePane.add(uNewAddress);
		updatePane.add(uUpdate);
		updatePane.add(uCancel);
		
		
		uUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// MELSSA!!! LOOK HERE!!!!
				// OUR CONSTRAINT IS HERE: LIMITS ADDRESSES TO AT LEAST 4 CHARS!!! :)
				if (uNewAddress.getText().length() > 4) {
				
					db.updateUserAddress(userID, uNewAddress.getText());
					
					JPanel frame = new JPanel();
					JOptionPane.showMessageDialog(frame,
						    "Your address has been successfully updated.");
					updatePane.setVisible(false);
				} else {
					JPanel frame = new JPanel();
					JOptionPane.showMessageDialog(frame,
						    "Your address is too short. Please enter at least 5 characters.");
					updatePane.setVisible(false);
				}
			}
		});
		
		uCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePane.setVisible(false);
				validate();
			}
			
		});
		
		add(updatePane);
		updatePane.setVisible(false);
		
		
		//------------------End Update Address Pane-----------------------------
	}

	public void actionPerformed(ActionEvent ae) {
		
		// Get information on all the courts when the user clicks on the radio button and display
		// the results in the output pane
		if (searchRadioGetAllCourts.isSelected()) {
			getAllCourts();
		} 
		
		// Get information on all the reserved courts when the user clicks on the radio button and display
		// the results in the output pane
		else if (searchRadioGetAllCentersAndCourts.isSelected()) {
			getAllCentersAndCourts();
		} 
		
		// Get information on all the unreserved courts when the user clicks on the radio button and display
		// the results in the output pane
		else if (searchRadioGetSmallestCourseForCoach.isSelected()) {
			getSmallestPeopleCourse();
		}
		
		else if( searchRadioGetSmallestPeopleCourt.isSelected()) {
			getMinimumCourt(0);
		}
		
		else if ( searchRadioGetLargestPeopleCourt.isSelected()) {
			getMinimumCourt(1);
		}
		
		else if (searchRadioButtonCourse.isSelected() && ae.getSource() == searchRadioButtonCourse) {

			getWithCourse();			
		}
		
		else if (searchRadioButtonCourt.isSelected() && ae.getSource() == searchRadioButtonCourt) {
			
			getWithCourt();
		}
		
		else if (searchRadioButtonDateCourse.isSelected() && ae.getSource() == searchRadioButtonDateCourse) {
			getCourseWithDate();
		}
		
		else if (searchRadioButtonDateCourt.isSelected() && ae.getSource() == searchRadioButtonDateCourt) {
			getCourtWithDate();
		}
		
		else if (searchRadioGetUsersWithAllCourts.isSelected() && ae.getSource() == searchRadioGetUsersWithAllCourts) {
			getUsersWithAllCourts();
			
		}
		
		// Clear the output pane if none of the radio buttons with immediate output are
		// selected
		/*else if( !searchRadioGetAllReservedCourts.isSelected() 
				&& !searchRadioGetAllCourts.isSelected()
				&& !(ae.getSource() == queryButton) ){
			outputPane.setVisible(false);
			remove(outputPane);
	
			outputPane = new JPanel();
			add(outputPane);
			validate();
		}*/
		
		
		
		if (ae.getSource() == bookCourtButton) {
			
			JFrame frame = new JFrame() {
			    public Dimension getPreferredSize() {
			        return new Dimension(200,100);
			    }
			};
			frame.setTitle("Debugging frame");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(false);
			
			JDialog dialog = new LoginDialog(frame, true, this);
			dialog.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent event) {
			        //System.exit(0);
			    	if (userLoginButton == 0) 				// successful login
			    		bookingPane.setVisible(true);
			    	else 									// unsuccessful login
			    		bookingPane.setVisible(false);
			    }
			    public void windowClosed(WindowEvent event) {
			        //System.exit(0);
			    	if (userLoginButton == 0)				// successful login
			    		bookingPane.setVisible(true);
			    	else									// unsuccessful login
			    		bookingPane.setVisible(false);
			    }
			});
			dialog.pack();
			dialog.setVisible(true);
		}
		else if (ae.getSource() == crMainReserveCourseButton) {
			//_------------------------------------------------------------------------------
			JFrame frame = new JFrame() {
			    public Dimension getPreferredSize() {
			        return new Dimension(200,100);
			    }
			};
			frame.setTitle("Debugging frame");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(false);
			
			JDialog dialog = new LoginDialog(frame, true, this);
			dialog.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent event) {
			        //System.exit(0);
			    	if (userLoginButton == 0) 				// successful login
			    		reservePane.setVisible(true);
			    	else 									// unsuccessful login
			    		reservePane.setVisible(false);
			    }
			    public void windowClosed(WindowEvent event) {
			        //System.exit(0);
			    	if (userLoginButton == 0)				// successful login
			    		reservePane.setVisible(true);
			    	else									// unsuccessful login
			    		reservePane.setVisible(false);
			    }
			});
			dialog.pack();
			dialog.setVisible(true);
			
		}
		else if (ae.getSource() == uMainUpdateAddressButton) {
			JFrame frame = new JFrame() {
			    public Dimension getPreferredSize() {
			        return new Dimension(200,100);
			    }
			};
			frame.setTitle("Debugging frame");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(false);
			
			JDialog dialog = new LoginDialog(frame, true, this);
			dialog.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent event) {
			        //System.exit(0);
			    	if (userLoginButton == 0) 				// successful login
			    		updatePane.setVisible(true);
			    	else 									// unsuccessful login
			    		updatePane.setVisible(false);
			    }
			    public void windowClosed(WindowEvent event) {
			        //System.exit(0);
			    	if (userLoginButton == 0)				// successful login
			    		updatePane.setVisible(true);
			    	else									// unsuccessful login
			    		updatePane.setVisible(false);
			    }
			});
			dialog.pack();
			dialog.setVisible(true);
		}
		
		// ------ Actions for booking pane ---------------------------
		else if (ae.getSource() == bpBookButton) {
			
			// Code to create transaction
			if (checkBookDates()) {
				Timestamp st = new Timestamp(
						Integer.parseInt(bpStartYear.getText())-1900, Integer.parseInt(bpStartMonth.getText()),
						Integer.parseInt(bpStartDay.getText()), Integer.parseInt(bpStartHour.getText()), 
						Integer.parseInt((String)bpStartMinute.getSelectedItem()), 0, 0);
				Timestamp et = new Timestamp(
						Integer.parseInt(bpEndYear.getText())-1900, Integer.parseInt(bpEndMonth.getText()),
						Integer.parseInt(bpEndDay.getText()), Integer.parseInt(bpEndHour.getText()), 
						Integer.parseInt((String)bpEndMinute.getSelectedItem()), 0, 0);
				
				
				
				db.rentCourt(userID, Integer.parseInt((String)bpCourtNumber.getSelectedItem()), 
						(String)bpCourtLocation.getSelectedItem(),
						Integer.parseInt(bpAmount.getText()), st, et);
				
				JPanel frame = new JPanel();
				JOptionPane.showMessageDialog(frame,
					    "Court Booked Successfully!");
			}
			
			bookingPane.setVisible(false);
			validate();
		}
		else if (ae.getSource() == bpCancelButton) {
			System.out.println("Cancel button pressed.");
			// Code for canceling the book court pane
			
			bookingPane.setVisible(false);
			validate();
		}
		// ------------End actions for booking pane-------------------------
		
		
		else if (ae.getSource() == createAccountButton) {
			accountPane.setVisible(true);
			createAccountButton.setVisible(false);
		}
		
		
		else if (ae.getSource() == cancelCourtRental) {
			// Login first
			JFrame frame = new JFrame() {
			    public Dimension getPreferredSize() {
			        return new Dimension(200,100);
			    }
			};
			frame.setTitle("Debugging frame");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(false);
			
			JDialog dialog = new LoginDialog(frame, true, this);
			dialog.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent event) {
			    	if (userLoginButton == 0)
			    		cancelPane.setVisible(true);
			    	
			    }
			    public void windowClosed(WindowEvent event) {
			    	if (userLoginButton == 0)
			    		cancelPane.setVisible(true);
			    }
			});
			dialog.pack();
			dialog.setVisible(true);
			
		}
	}

	

	// Used for user login 
	public void setID(String id) {
		userID = id;
		if (userLoginButton == 0)
			filler.setText(userID);
			filler2.setText(userID);
			filler3.setText(userID);
			filler4.setText(userID);
			
	}
	// Used for user login
	public void setPassword(String password) {
		userPassword = password;
	}
	// Used for user login
	public void setLoginButtonPressed(int input) {
		userLoginButton = input;

	}

	// For cancellation page, selecting transaction ids as either courses or court
	private void updateCourseTransactionList() {
		cTransactionIDs.removeAllItems();
		ArrayList<Integer> courses = db.getListOfTransactionID(userID, 2);
		
		for (Integer iterator: courses) {			
			cTransactionIDs.addItem(iterator.toString());
			System.out.println(iterator.toString());
		}
		validate();
	}
	
	private void updateCourtTransactionList() {
		cTransactionIDs.removeAllItems();
		
		ArrayList<Integer> courts = db.getListOfTransactionID(userID, 1);
		for (Integer iterator: courts) {
			cTransactionIDs.addItem(iterator.toString());
			System.out.println(iterator.toString());
		}
		validate();
	}
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
//--------------------- Helper Methods Below ----------------------------------
	private void getAllCourts() {
		System.out.println("Getting all courts..");
		
		
		ArrayList<ArrayList<String>> myQuery = db.getAllCourts();
		TableModel tm = new TableModel(myQuery);
		
		outputPane.setVisible(false);
		outputPane = new JPanel();
		outputPane.setLayout(new GridLayout(tm.getRowCount()+1, tm.getColumnCount()));
		outputPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Output"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		
		for (int i =0; i < tm.getColumnCount(); i++) {
			JLabel out1 = new JLabel(tm.getColumns().get(i));
			outputPane.add(out1);
		}
		
		
		for (int i = 0; i < tm.getRowCount(); i++) {
			for (int j = 0; j < tm.getColumnCount(); j++) {
				
				JLabel out2 = new JLabel(tm.getValueAt(i,j));
				outputPane.add(out2);
			}
				
		}
		
		add(outputPane);
		validate();	
		
	}
	
	private void getAllCentersAndCourts() {
		System.out.println("Getting all centers and courts..");
		
		
		ArrayList<ArrayList<String>> myQuery = db.getAllCentresAndCourts();
		TableModel tm = new TableModel(myQuery);
		
		outputPane.setVisible(false);
		outputPane = new JPanel();
		outputPane.setLayout(new GridLayout(tm.getRowCount()+1, tm.getColumnCount()));
		outputPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Output"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		
		for (int i =0; i < tm.getColumnCount(); i++) {
			JLabel out1 = new JLabel(tm.getColumns().get(i));
			outputPane.add(out1);
		}
		
		
		for (int i = 0; i < tm.getRowCount(); i++) {
			for (int j = 0; j < tm.getColumnCount(); j++) {
				
				JLabel out2 = new JLabel(tm.getValueAt(i,j));
				outputPane.add(out2);
			}
				
		}
		
		add(outputPane);
		validate();	
	}
	
	private void getSmallestPeopleCourse() {
		
		
		ArrayList<ArrayList<String>> myQuery = db.getCoachWithSmallestCourse();
		TableModel tm = new TableModel(myQuery);
		
		outputPane.setVisible(false);
		outputPane = new JPanel();
		outputPane.setLayout(new GridLayout(tm.getRowCount()+1, tm.getColumnCount()));
		outputPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Output"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		
		for (int i =0; i < tm.getColumnCount(); i++) {
			JLabel out1 = new JLabel(tm.getColumns().get(i));
			outputPane.add(out1);
		}
		
		
		for (int i = 0; i < tm.getRowCount(); i++) {
			for (int j = 0; j < tm.getColumnCount(); j++) {
				
				JLabel out2 = new JLabel(tm.getValueAt(i,j));
				outputPane.add(out2);
			}	
		}
		
		add(outputPane);
		validate();	
	}
	
	private void getWithCourse() {
		
		JButton searchDone = new JButton("Search");
		JButton searchCancel = new JButton("Cancel");
		
		searchDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When search is pressed
				
				ArrayList<ArrayList<String>> myQuery = db.getAllTimePeriodsForCourse(Integer.parseInt(inputCourse.getText()));
				
				TableModel tm = new TableModel(myQuery);
				
				outputPane.setVisible(false);
				outputPane = new JPanel();
				outputPane.setLayout(new GridLayout(tm.getRowCount()+1, tm.getColumnCount()));
				outputPane.setBorder(BorderFactory.createCompoundBorder(
		                BorderFactory.createTitledBorder("Output"),
		                BorderFactory.createEmptyBorder(5,5,5,5)));
				
				for (int i =0; i < tm.getColumnCount(); i++) {
					JLabel out1 = new JLabel(tm.getColumns().get(i));
					outputPane.add(out1);
				}
				
				
				for (int i = 0; i < tm.getRowCount(); i++) {
					for (int j = 0; j < tm.getColumnCount(); j++) {
						
						JLabel out2 = new JLabel(tm.getValueAt(i,j));
						outputPane.add(out2);
					}
						
				}
				
				add(outputPane);
				validate();	
			}
			
		});
		
		searchCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When Cancel is pressed
				searchPane.setVisible(false);
				outputPane.setVisible(false);
				validate();
			}
			
		});
		searchPane.setVisible(false);
		searchPane = new JPanel();
		searchPane.setLayout(new GridLayout(2,1));
		searchPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Search Input"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		
		inputCourtLabel = new JLabel("Court: ");
		//searchPane.add(inputCourtLabel);
		//searchPane.add(inputCourts);
		
		inputCourseLabel = new JLabel("Course: ");
		searchPane.add(inputCourseLabel);
		searchPane.add(inputCourse);
		
		
		searchPane.add(searchDone);
		searchPane.add(searchCancel);
		
		searchPane.setVisible(true);

		
		add(searchPane);
		validate();
	}

	private void getWithCourt() {
		JButton searchDone = new JButton("Search");
		JButton searchCancel = new JButton("Cancel");
		
		searchDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When search is pressed
				
				ArrayList<ArrayList<String>> myQuery = db.getAllTimePeriodsForCourts(Integer.parseInt(inputCourts.getText()));
				
				TableModel tm = new TableModel(myQuery);
				
				outputPane.setVisible(false);
				outputPane = new JPanel();
				outputPane.setLayout(new GridLayout(tm.getRowCount()+1, tm.getColumnCount()));
				outputPane.setBorder(BorderFactory.createCompoundBorder(
		                BorderFactory.createTitledBorder("Output"),
		                BorderFactory.createEmptyBorder(5,5,5,5)));
				
				for (int i =0; i < tm.getColumnCount(); i++) {
					JLabel out1 = new JLabel(tm.getColumns().get(i));
					outputPane.add(out1);
				}
				
				
				for (int i = 0; i < tm.getRowCount(); i++) {
					for (int j = 0; j < tm.getColumnCount(); j++) {
						
						JLabel out2 = new JLabel(tm.getValueAt(i,j));
						outputPane.add(out2);
					}
						
				}
				
				add(outputPane);
				validate();	
			}
			
		});
		
		searchCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When Cancel is pressed
				searchPane.setVisible(false);
				outputPane.setVisible(false);
				validate();
			}
			
		});
		searchPane.setVisible(false);
		searchPane = new JPanel();
		searchPane.setLayout(new GridLayout(2,1));
		searchPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Search Input"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		
		inputCourtLabel = new JLabel("Court: ");
		searchPane.add(inputCourtLabel);
		searchPane.add(inputCourts);		
		searchPane.add(searchDone);
		searchPane.add(searchCancel);
		
		searchPane.setVisible(true);

		
		add(searchPane);
		validate();
		
	}

	// Displays error message if the user entered invalid input
	private void displaySearchError() {
		JPanel frame = new JPanel();
		JOptionPane.showMessageDialog(frame ,
		    "Error! The things you typed don't seem to make sense.",
		    "User Input Error",
		    JOptionPane.ERROR_MESSAGE);
	}
	
	// Check if the user has typed the invalid input
	private boolean checkSearchDates() {
		try {
			boolean checkYearStart = (Integer.parseInt(inputYearStart.getText()) > 2000) && (Integer.parseInt(inputYearStart.getText()) < 3000);	
			boolean checkYearEnd = (Integer.parseInt(inputYearEnd.getText()) > 2000) && (Integer.parseInt(inputYearEnd.getText()) < 3000);
			boolean checkYearMakeSense = (Integer.parseInt(inputYearStart.getText()) <= Integer.parseInt(inputYearEnd.getText()));
			boolean checkMonthStart = (Integer.parseInt(inputMonthStart.getText()) >=1) && (Integer.parseInt(inputMonthStart.getText()) <=12);
			boolean checkMonthEnd = (Integer.parseInt(inputMonthEnd.getText()) >=1) && (Integer.parseInt(inputMonthEnd.getText()) <=12);
			boolean checkMonthMakeSense = (Integer.parseInt(inputMonthStart.getText()) <= Integer.parseInt(inputMonthEnd.getText()));
			boolean checkHourStart = (Integer.parseInt(inputHourStart.getText()) >=0) && (Integer.parseInt(inputHourStart.getText()) < 24 );
			boolean checkHourEnd = (Integer.parseInt(inputHourEnd.getText()) >=0) && (Integer.parseInt(inputHourEnd.getText()) < 24);
			boolean checkHourMakeSense = (Integer.parseInt(inputHourStart.getText()) <= Integer.parseInt(inputHourEnd.getText()));
			boolean checkEqualStartEnd =
					(Integer.parseInt(inputYearStart.getText()) == Integer.parseInt(inputYearEnd.getText())) &&
					(Integer.parseInt(inputMonthStart.getText()) == Integer.parseInt(inputMonthEnd.getText())) &&
					(Integer.parseInt(inputHourStart.getText()) == Integer.parseInt(inputHourEnd.getText())) &&
					(inputMinuteStart.getSelectedIndex() == inputMinuteEnd.getSelectedIndex());

			
			if (
					checkYearStart && checkYearEnd && checkYearMakeSense &&
					checkMonthStart && checkMonthEnd && checkMonthMakeSense &&
					checkHourStart && checkHourEnd && checkHourMakeSense &&
					!checkEqualStartEnd
					) {
				return true;
			}
			
		} catch( Exception e) {
			System.out.println("exception"+ e.getMessage());
			
			displaySearchError();
			return false;
		}
		displaySearchError();		
		return false;
	}
	
	private boolean checkBookDates() {
		try {
			boolean checkYearStart = (Integer.parseInt(bpStartYear.getText()) > 2000) && (Integer.parseInt(bpStartYear.getText()) < 3000);	
			boolean checkYearEnd = (Integer.parseInt(bpEndYear.getText()) > 2000) && (Integer.parseInt(bpEndYear.getText()) < 3000);
			boolean checkYearMakeSense = (Integer.parseInt(bpStartYear.getText()) <= Integer.parseInt(bpEndYear.getText()));
			boolean checkMonthStart = (Integer.parseInt(bpStartMonth.getText()) >=1) && (Integer.parseInt(bpStartMonth.getText()) <=12);
			boolean checkMonthEnd = (Integer.parseInt(bpEndMonth.getText()) >=1) && (Integer.parseInt(bpEndMonth.getText()) <=12);
			boolean checkMonthMakeSense = (Integer.parseInt(bpStartMonth.getText()) <= Integer.parseInt(bpEndMonth.getText()));
			boolean checkHourStart = (Integer.parseInt(bpStartHour.getText()) >=0) && (Integer.parseInt(bpStartHour.getText()) < 24 );
			boolean checkHourEnd = (Integer.parseInt(bpEndHour.getText()) >=0) && (Integer.parseInt(bpEndHour.getText()) < 24);
			boolean checkHourMakeSense = (Integer.parseInt(bpStartHour.getText()) <= Integer.parseInt(bpEndHour.getText()));
			boolean checkEqualStartEnd =
					(Integer.parseInt(bpStartYear.getText()) == Integer.parseInt(bpEndYear.getText())) &&
					(Integer.parseInt(bpStartMonth.getText()) == Integer.parseInt(bpEndMonth.getText())) &&
					(Integer.parseInt(bpStartHour.getText()) == Integer.parseInt(bpEndHour.getText())) &&
					(bpStartMinute.getSelectedIndex() == bpEndMinute.getSelectedIndex());

			
			if (
					checkYearStart && checkYearEnd && checkYearMakeSense &&
					checkMonthStart && checkMonthEnd && checkMonthMakeSense &&
					checkHourStart && checkHourEnd && checkHourMakeSense &&
					!checkEqualStartEnd
					) {
				return true;
			}
			
		} catch( Exception e) {
			System.out.println("exception"+ e.getMessage());
			
			displaySearchError();
			return false;
		}
		displaySearchError();		
		return false;
	}
	//private boolean checkReserve
	
	

	private void getCourseWithDate() {
		JButton searchDone = new JButton("Search");
		JButton searchCancel = new JButton("Cancel");
		
		searchDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (checkSearchDates()) {
				
					
				
					// TODO
					// Create timestamps for beginning and end times and pass into dbHandler
					int minStart = 0;
					if (inputMinuteStart.getSelectedIndex() == 0) {
						minStart = 0;
						
					} else {
						minStart = 30;
					}
					
					
					Timestamp startTime = new Timestamp(Integer.parseInt(inputYearStart.getText())-1900,
							Integer.parseInt(inputMonthStart.getText()),
							Integer.parseInt(inputHourStart.getText()),
							minStart, 0 , 0, 0);
					
					
					int minEnd = 0;
					if (inputMinuteEnd.getSelectedIndex() == 0) {
						minEnd = 0;
						
					} else {
						minEnd = 30;
					}
					Timestamp endTime = new Timestamp(Integer.parseInt(inputYearEnd.getText())-1900,
							Integer.parseInt(inputMonthEnd.getText()),
							Integer.parseInt(inputHourEnd.getText()),
							minEnd, 0 , 0, 0);
					ArrayList<ArrayList<String>> myQuery = db.getAllCourseInTimePeriod(startTime, endTime);
					
					TableModel tm = new TableModel(myQuery);
					
					outputPane.setVisible(false);
					outputPane = new JPanel();
					outputPane.setLayout(new GridLayout(tm.getRowCount()+1, tm.getColumnCount()));
					outputPane.setBorder(BorderFactory.createCompoundBorder(
			                BorderFactory.createTitledBorder("Output"),
			                BorderFactory.createEmptyBorder(5,5,5,5)));
					
					for (int i =0; i < tm.getColumnCount(); i++) {
						JLabel out1 = new JLabel(tm.getColumns().get(i));
						outputPane.add(out1);
					}
					
					
					for (int i = 0; i < tm.getRowCount(); i++) {
						for (int j = 0; j < tm.getColumnCount(); j++) {
							
							JLabel out2 = new JLabel(tm.getValueAt(i,j));
							outputPane.add(out2);
						}
							
					}
					
					add(outputPane);
					validate();
						
				}
			}
			
		});
		
		searchCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When Cancel is pressed
				searchPane.setVisible(false);
				outputPane.setVisible(false);
				validate();
			}
			
		});
		searchPane.setVisible(false);
		searchPane = new JPanel();
		searchPane.setLayout(new GridLayout(6,1));
		searchPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Search Input"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		
		// Year Label
		JLabel inputYearLabel = new JLabel("Year: ");
		searchPane.add(inputYearLabel);
		
		// Year start and end boxes
		JPanel yearBoxes = new JPanel();
		yearBoxes.setLayout(new GridLayout(1,2));
		yearBoxes.add(inputYearStart);			
		yearBoxes.add(inputYearEnd);
		searchPane.add(yearBoxes);
		

		
		// Month label
		JLabel inputMonthLabel=  new JLabel("Month: ");
		searchPane.add(inputMonthLabel);
		
		// Month start and end boxes
		JPanel monthBoxes = new JPanel();
		monthBoxes.setLayout(new GridLayout(1,2));
		monthBoxes.add(inputMonthStart);
		monthBoxes.add(inputMonthEnd);
		searchPane.add(monthBoxes);
		
		
		
		
		// Day label
		JLabel inputDayLabel=  new JLabel("Day: ");
		searchPane.add(inputDayLabel);
		
		// Day start and end boxes
		JPanel dayBoxes = new JPanel();
		dayBoxes.setLayout(new GridLayout(1,2));
		dayBoxes.add(inputDayStart);
		dayBoxes.add(inputDayEnd);
		searchPane.add(dayBoxes);
		
		
		
		// Hour label
		JLabel inputHourLabel=  new JLabel("Hour: ");
		searchPane.add(inputHourLabel);
		
		// Hour start and end boxes
		JPanel hourBoxes = new JPanel();
		hourBoxes.setLayout(new GridLayout(1,2));
		hourBoxes.add(inputHourStart);
		hourBoxes.add(inputHourEnd);
		searchPane.add(hourBoxes);
		

		// Minute label
		JLabel inputMinuteLabel =  new JLabel("Minute: ");
		searchPane.add(inputMinuteLabel);
		
		// Minute start and end boxes
		JPanel minuteBoxes = new JPanel();
		minuteBoxes.setLayout(new GridLayout(1,2));
		minuteBoxes.add(inputMinuteStart);
		minuteBoxes.add(inputMinuteEnd);
		searchPane.add(minuteBoxes);
			
		searchPane.add(searchDone);
		searchPane.add(searchCancel);
		searchPane.setVisible(true);
		add(searchPane);
		validate();
	}

	private void getCourtWithDate() {
		JButton searchDone = new JButton("Search");
		JButton searchCancel = new JButton("Cancel");
		
		searchDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkSearchDates()) {
					

					// Create timestamps for beginning and end times and pass into dbHandler
					int minStart = 0;
					if (inputMinuteStart.getSelectedIndex() == 0) {
						minStart = 0;
						
					} else {
						minStart = 30;
					}
					
					
					Timestamp startTime = new Timestamp(Integer.parseInt(inputYearStart.getText())-1900,
							Integer.parseInt(inputMonthStart.getText()),
							Integer.parseInt(inputHourStart.getText()),
							minStart, 0 , 0, 0);
					
					
					int minEnd = 0;
					if (inputMinuteEnd.getSelectedIndex() == 0) {
						minEnd = 0;
						
					} else {
						minEnd = 30;
					}
					Timestamp endTime = new Timestamp(Integer.parseInt(inputYearEnd.getText())-1900,
							Integer.parseInt(inputMonthEnd.getText()),
							Integer.parseInt(inputHourEnd.getText()),
							minEnd, 0 , 0, 0);
					ArrayList<ArrayList<String>> myQuery = db.getAllCourtsInTimePeriod(startTime, endTime);
					
					TableModel tm = new TableModel(myQuery);
					
					outputPane.setVisible(false);
					outputPane = new JPanel();
					outputPane.setLayout(new GridLayout(tm.getRowCount()+1, tm.getColumnCount()));
					outputPane.setBorder(BorderFactory.createCompoundBorder(
			                BorderFactory.createTitledBorder("Output"),
			                BorderFactory.createEmptyBorder(5,5,5,5)));
					
					for (int i =0; i < tm.getColumnCount(); i++) {
						JLabel out1 = new JLabel(tm.getColumns().get(i));
						outputPane.add(out1);
					}
					
					
					for (int i = 0; i < tm.getRowCount(); i++) {
						for (int j = 0; j < tm.getColumnCount(); j++) {
							
							JLabel out2 = new JLabel(tm.getValueAt(i,j));
							outputPane.add(out2);
						}
							
					}
					
					add(outputPane);
					validate();
					
				}
			}
				
			 	
		});
		
		searchCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When Cancel is pressed
				searchPane.setVisible(false);
				outputPane.setVisible(false);
				validate();
			}
			
		});
		searchPane.setVisible(false);
		searchPane = new JPanel();
		searchPane.setLayout(new GridLayout(6,1));
		searchPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Search Input"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		
		// Year Label
		JLabel inputYearLabel = new JLabel("Year: ");
		searchPane.add(inputYearLabel);
		
		// Year start and end boxes
		JPanel yearBoxes = new JPanel();
		yearBoxes.setLayout(new GridLayout(1,2));
		yearBoxes.add(inputYearStart);			
		yearBoxes.add(inputYearEnd);
		searchPane.add(yearBoxes);
		
		
		
		
		
		
		// Month label
		JLabel inputMonthLabel=  new JLabel("Month: ");
		searchPane.add(inputMonthLabel);
		
		// Month start and end boxes
		JPanel monthBoxes = new JPanel();
		monthBoxes.setLayout(new GridLayout(1,2));
		monthBoxes.add(inputMonthStart);
		monthBoxes.add(inputMonthEnd);
		searchPane.add(monthBoxes);
		
		
		
		
		// Day label
		JLabel inputDayLabel=  new JLabel("Day: ");
		searchPane.add(inputDayLabel);
		
		// Day start and end boxes
		JPanel dayBoxes = new JPanel();
		dayBoxes.setLayout(new GridLayout(1,2));
		dayBoxes.add(inputDayStart);
		dayBoxes.add(inputDayEnd);
		searchPane.add(dayBoxes);
		
		
		
		// Hour label
		JLabel inputHourLabel=  new JLabel("Hour: ");
		searchPane.add(inputHourLabel);
		
		// Hour start and end boxes
		JPanel hourBoxes = new JPanel();
		hourBoxes.setLayout(new GridLayout(1,2));
		hourBoxes.add(inputHourStart);
		hourBoxes.add(inputHourEnd);
		searchPane.add(hourBoxes);
		
		
		
		
		// Minute label
		JLabel inputMinuteLabel =  new JLabel("Minute: ");
		searchPane.add(inputMinuteLabel);
		
		// Minute start and end boxes
		JPanel minuteBoxes = new JPanel();
		minuteBoxes.setLayout(new GridLayout(1,2));
		minuteBoxes.add(inputMinuteStart);
		minuteBoxes.add(inputMinuteEnd);
		searchPane.add(minuteBoxes);
		
		
		searchPane.add(searchDone);
		searchPane.add(searchCancel);
		searchPane.setVisible(true);
		
		

		add(searchPane);
		validate();
	}

	private void getMinimumCourt(int minMax) {
		JButton searchDone = new JButton("Search");
		JButton searchCancel = new JButton("Cancel");
		 final int minMax1 = minMax;
		searchDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (checkSearchDates()) {	
					// Create timestamps for beginning and end times and pass into dbHandler
					int minStart = 0;
					if (inputMinuteStart.getSelectedIndex() == 0) {
						minStart = 0;
						
					} else {
						minStart = 30;
					}
					
					
					Timestamp startTime = new Timestamp(Integer.parseInt(inputYearStart.getText())-1900,
							Integer.parseInt(inputMonthStart.getText()),
							Integer.parseInt(inputHourStart.getText()),
							minStart, 0 , 0, 0);
					
					
					int minEnd = 0;
					if (inputMinuteEnd.getSelectedIndex() == 0) {
						minEnd = 0;
						
					} else {
						minEnd = 30;
					}
					Timestamp endTime = new Timestamp(Integer.parseInt(inputYearEnd.getText())-1900,
							Integer.parseInt(inputMonthEnd.getText()),
							Integer.parseInt(inputHourEnd.getText()),
							minEnd, 0 , 0, 0);
					
					ArrayList<ArrayList<String>> myQuery;
					if (minMax1 == 1) {
						myQuery = db.getMinimumCourt(startTime, endTime);
					} else {
						myQuery = db.getMaximumCourt(startTime, endTime);
					}
					
					TableModel tm = new TableModel(myQuery);
					
					outputPane.setVisible(false);
					outputPane = new JPanel();
					outputPane.setLayout(new GridLayout(tm.getRowCount()+1, tm.getColumnCount()));
					outputPane.setBorder(BorderFactory.createCompoundBorder(
			                BorderFactory.createTitledBorder("Output"),
			                BorderFactory.createEmptyBorder(5,5,5,5)));
					
					for (int i =0; i < tm.getColumnCount(); i++) {
						JLabel out1 = new JLabel(tm.getColumns().get(i));
						outputPane.add(out1);
					}
					
					
					for (int i = 0; i < tm.getRowCount(); i++) {
						for (int j = 0; j < tm.getColumnCount(); j++) {
							
							JLabel out2 = new JLabel(tm.getValueAt(i,j));
							outputPane.add(out2);
						}
					}
					
					add(outputPane);
					validate();
						
				}
			}
			
		});
		
		searchCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When Cancel is pressed
				searchPane.setVisible(false);
				outputPane.setVisible(false);
				validate();
			}
			
		});
		searchPane.setVisible(false);
		searchPane = new JPanel();
		searchPane.setLayout(new GridLayout(6,1));
		searchPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Search Input"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		
		// Year Label
		JLabel inputYearLabel = new JLabel("Year: ");
		searchPane.add(inputYearLabel);
		
		// Year start and end boxes
		JPanel yearBoxes = new JPanel();
		yearBoxes.setLayout(new GridLayout(1,2));
		yearBoxes.add(inputYearStart);			
		yearBoxes.add(inputYearEnd);
		searchPane.add(yearBoxes);
		

		
		// Month label
		JLabel inputMonthLabel=  new JLabel("Month: ");
		searchPane.add(inputMonthLabel);
		
		// Month start and end boxes
		JPanel monthBoxes = new JPanel();
		monthBoxes.setLayout(new GridLayout(1,2));
		monthBoxes.add(inputMonthStart);
		monthBoxes.add(inputMonthEnd);
		searchPane.add(monthBoxes);
		
		
		
		
		// Day label
		JLabel inputDayLabel=  new JLabel("Day: ");
		searchPane.add(inputDayLabel);
		
		// Day start and end boxes
		JPanel dayBoxes = new JPanel();
		dayBoxes.setLayout(new GridLayout(1,2));
		dayBoxes.add(inputDayStart);
		dayBoxes.add(inputDayEnd);
		searchPane.add(dayBoxes);
		
		
		
		// Hour label
		JLabel inputHourLabel=  new JLabel("Hour: ");
		searchPane.add(inputHourLabel);
		
		// Hour start and end boxes
		JPanel hourBoxes = new JPanel();
		hourBoxes.setLayout(new GridLayout(1,2));
		hourBoxes.add(inputHourStart);
		hourBoxes.add(inputHourEnd);
		searchPane.add(hourBoxes);
		

		// Minute label
		JLabel inputMinuteLabel =  new JLabel("Minute: ");
		searchPane.add(inputMinuteLabel);
		
		// Minute start and end boxes
		JPanel minuteBoxes = new JPanel();
		minuteBoxes.setLayout(new GridLayout(1,2));
		minuteBoxes.add(inputMinuteStart);
		minuteBoxes.add(inputMinuteEnd);
		searchPane.add(minuteBoxes);
			
		searchPane.add(searchDone);
		searchPane.add(searchCancel);
		searchPane.setVisible(true);
		add(searchPane);
		validate();
		
	}

	
	

	private void createNewUser() {
		db.createNewUser(aUsername.getText(), aPassword.getText(), aBalance.getText(), aAddress.getText(), aName.getText());
		
		JPanel frame = new JPanel();
		JOptionPane.showMessageDialog(frame,
			    "User account " + aUsername.getText() + " has been created successfully.");
		
		accountPane.setVisible(false);
		createAccountButton.setVisible(true);
		validate();
	}
	
	private void getUsersWithAllCourts() {
		ArrayList<ArrayList<String>> myQuery = db.getUsersWithAllCourts();		
		TableModel tm = new TableModel(myQuery);
		
		outputPane.setVisible(false);
		outputPane = new JPanel();
		outputPane.setLayout(new GridLayout(tm.getRowCount()+1, tm.getColumnCount()));
		outputPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Output"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		
		for (int i =0; i < tm.getColumnCount(); i++) {
			JLabel out1 = new JLabel(tm.getColumns().get(i));
			outputPane.add(out1);
		}
		
		
		for (int i = 0; i < tm.getRowCount(); i++) {
			for (int j = 0; j < tm.getColumnCount(); j++) {
				
				JLabel out2 = new JLabel(tm.getValueAt(i,j));
				outputPane.add(out2);
			}
				
		}
		
		add(outputPane);
		validate();	
	}
	
	
}
