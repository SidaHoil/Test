package unitProductFormView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.Query;

import com.mysql.jdbc.Statement;

import productForms.SqlConnection;

public class MyQuery {
   	    
	    public ArrayList<UnitProductClass> BindTable(){
	        
	   ArrayList<UnitProductClass> list = new ArrayList<UnitProductClass>();
	   Connection cnn = null;
	   
	   try {
	   //st = con.createStatement();
	  // rs = st.executeQuery("SELECT * FROM tbl_unit_product");
	   
	   cnn = SqlConnection.dbConnector();
	   PreparedStatement ps = cnn.prepareStatement("SELECT * FROM tbl_unit_product");
	   ResultSet rs = ps.executeQuery();
	   
	   UnitProductClass upro;
	   while(rs.next()){
	   upro = new UnitProductClass(
	   rs.getInt("unit_product_id"),
	   rs.getString("unit_product_name"),
	   rs.getString("unit_product_color"),
	   rs.getString("unit_product_type"),
	   rs.getBytes("unit_product_image"),
	   rs.getInt("import_product_id")
	   );
	   list.add(upro);
	   }
	   
	   } catch (SQLException ex) {
	   Logger.getLogger(MyQuery.class.getName()).log(Level.SEVERE, null, ex);
	   }
	   return list;
	   }
	    
	}
