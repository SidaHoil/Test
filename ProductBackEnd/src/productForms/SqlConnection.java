package productForms;

import java.sql.*;
import javax.swing.*;

public class SqlConnection {
	Connection con=null;
	public static Connection dbConnector()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/product_back_end","root","");
		//	JOptionPane.showMessageDialog(null,"Connection successful");
			return con;
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
			return null;
		}
	}
	
}
