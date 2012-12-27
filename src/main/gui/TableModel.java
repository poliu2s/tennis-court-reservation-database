package main.gui;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


public class TableModel extends AbstractTableModel {

	public ArrayList<String>				columns;
	private ArrayList<ArrayList<String>>	records;

	public TableModel(ArrayList<ArrayList<String>> records) {
		super();
		
		this.columns = records.get(0);
		records.remove(0);
		this.records = records;
		
		
	}
	
	public ArrayList<String> getColumns() {
		return columns;
	}
	
	public int getColumnCount() {
		
		return columns.size();
	}
	
	
	public int getRowCount() {
		
		return records.size();
		
	}
	
	
	public String getValueAt(int arg0, int arg1) {
		ArrayList<String> temp = records.get(arg0);
		
		return temp.get(arg1);
		
	}
	
	
}
