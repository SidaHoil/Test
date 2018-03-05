package database;
import java.sql.*;

import javax.swing.JOptionPane;

import forms.InputToForm;
public class JDBC {

	public static void main(String[] args) {
		InputToForm in=new InputToForm();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			Connection cnn=DriverManager.getConnection("jdbc:mysql://localhost:3300/JavaJDBC","root","root");
			Statement st=cnn.createStatement();
			do {
				in.whichMenu=Integer.parseInt(in.Menu(in.firsMenu));
				switch(in.whichMenu) {
				case 1:
					do {
						try {
							in.tname.setFocusable(true);
							in.missing=in.Input(in.obj,"Input Person Information");
							String Name=in.tname.getText().toString();
							String Sex=in.tsex.getText().toString();
							String Room=in.troom.getText().toString();
							String Major=in.tmajor.getText().toString();
							if(in.missing==JOptionPane.OK_OPTION ) {
								int i=st.executeUpdate("INSERT INTO Students(name,sex,room,major) "
										+ "VALUES('"+Name+"','"+Sex+"','"+Room+"','"+Major+"');");
								in.more=in.Question(i+"Record insert into database successfully."
										+ "\nDo you want to input more?");
								in.tname.setText("");
								in.tsex.setText("");
								in.troom.setText("");
								in.tmajor.setText("");
								in.tname.setFocusable(true);
							}
							else if(in.missing==JOptionPane.CANCEL_OPTION) break;
						} catch (Exception e) {
							
						}
					}while(in.more!=JOptionPane.CANCEL_OPTION);
					break;
				case 2:
					try {
						String info="";
						ResultSet rst=st.executeQuery("SELECT * FROM  Students;");
						while(rst.next()) {
							String name=rst.getString("name");
							String age=rst.getString("sex");
							String room=rst.getString("room");
							String major=rst.getString("major");
						    info+=name+", "+age+", "+room+", "+major+"\n";
						}
						in.ShowInfo(info);
					} catch (Exception e) {
						
					}
					break;
				case 3:
					
					break;
				case 4:
					break;
				}
			}while(in.whichMenu!=5);
		} catch (InstantiationException e) {
			System.out.println("InstantiationException");
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException");
		}catch(SQLException e) {
			System.out.println("SQL Exception");
		}catch (Exception e) {
			
		}
		
	}

}
