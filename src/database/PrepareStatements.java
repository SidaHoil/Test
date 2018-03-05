package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrepareStatements {
	Connection cnn;
	PreparedStatement pr;
	ResultSet rs;
//	public PrepareStatements() {
//		try {
//			OpenConnection();
//		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}	
//	}
	public void OpenConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			cnn=DriverManager.getConnection("jdbc:mysql://localhost:3300/JavaJDBC","root","root");
	}
	public void CloseConnection() throws SQLException {
		//rs.close();
		cnn.close();
	}
	public int Insert(int id,String n,String sex,float s) throws SQLException {
		pr=cnn.prepareStatement("INSERT INTO tblStudents VALUES(?,?,?,?);"
				,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		pr.setInt(1, id);
		pr.setString(2, n);
		pr.setString(3, sex);
		pr.setFloat(4, s);
		int i=pr.executeUpdate();
		return i;
	}
	public int Update(int id,String name,String sex,float score) throws SQLException {
		pr=cnn.prepareStatement("UPDATE tblStudents SET Name=?,Sex=?,Score=? WHERE Id=?;");
		pr.setString(1, name);
		pr.setString(2, sex);
		pr.setFloat(3, score);
		pr.setInt(4, id);
		int i=pr.executeUpdate();
		return i;
	}
	public int Delete(int id) throws SQLException {
		pr=cnn.prepareStatement("DELETE FROM tblStudents WHERE Id=?;");
		pr.setInt(1, id);
		int i=pr.executeUpdate();
		return i;
	}
	public ResultSet Select() throws SQLException {
		pr=cnn.prepareStatement("SELECT * FROM tblStudents");
		rs=pr.executeQuery();
		return rs;
	}
}
