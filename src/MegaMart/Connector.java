package MegaMart;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

/**
 * This class creates the connection to the SQLite database
 * 
 * */
public class Connector 
{
	
	protected static Connection DBcon()
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
			
			Connection con = DriverManager.getConnection("jdbc:sqlite::resource:MegaMart.db");
			return con;
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
}
}

