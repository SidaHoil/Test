package sqlStatement;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class InsertUpdate extends JFrame{
	private GridLayout grd = new GridLayout(4, 3);
	private JPanel pButton = new JPanel(new GridLayout(1, 4)),
				   pContian = new JPanel(grd);
	private JButton btnFirst=new JButton("First");
	private JButton btnLast=new JButton("Last");
	private JButton btnPreviouse=new JButton("Previouse");
	private JButton btnNext=new JButton("Next");
	private JButton btnInsert=new JButton("Insert");
	private JButton btnUpdate=new JButton("Update");
	private JLabel lname=new JLabel("Name");
	private JLabel lsex=new JLabel("Sex");
	private JLabel lroom=new JLabel("Room");
	private JLabel lmajor=new JLabel("Major");
	private JLabel lcurrentIndex=new JLabel("index: 0/0");
	private JTextField tname=new JTextField();
	private JTextField troom=new JTextField();
	private JTextField tmajor=new JTextField();
	private JComboBox jcobSex=new JComboBox();
	
	private Connection cnn;
	private Statement st;
	private ResultSet rs;
	private int rowCount=0;
	public InsertUpdate() {
		setTitle("Insert And Update With Form");
		setSize(400,180);
		
		jcobSex.addItem("Female");
		jcobSex.addItem("Male");
		
		pContian.add(lname);
		pContian.add(tname);
		pContian.add(btnInsert);
		
		pContian.add(lsex);
		pContian.add(jcobSex);
		pContian.add(new JLabel()/*btnUpdate*/);
		
		pContian.add(lroom);
		pContian.add(troom);
		pContian.add(new JLabel());
		
		pContian.add(lmajor);
		pContian.add(tmajor);
		pContian.add(lcurrentIndex);
		
		add(pContian,BorderLayout.CENTER);
		grd.setHgap(5);
		grd.setVgap(5);
		
		pButton.add(btnFirst);
		pButton.add(btnPreviouse);
		pButton.add(btnNext);
		pButton.add(btnLast);
		add(pButton,BorderLayout.SOUTH);
		
		try {
			cnn=DriverManager.getConnection("jdbc:mysql://localhost:3300/JavaJDBC","root","root");
			st=cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=st.executeQuery("SELECT * FROM Students;");
			rs.last();
			rowCount=rs.getRow();
			rs.first();
//			tname.setText(rs.getString(1));
//			jcobSex.setSelectedItem(rs.getString(2));
//			troom.setText(rs.getString(3));
//			tmajor.setText(rs.getString(4));
//			lcurrentIndex.setText("Current Index:"+rs.getRow()+"/"+ rowCount);
			setValues(rs);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		btnFirst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					rs.first();
					setValues(rs);
				}catch (SQLException e) {
					e.printStackTrace();
				}finally {
					btnInsert.enable();
				}
				
			}
		});
		btnLast.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					rs.last();
					setValues(rs);
				}catch (SQLException e) {
					e.printStackTrace();
				}finally {
					btnInsert.enable();
				}
				
			}
		});
		btnPreviouse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					rs.previous();
					if(rs.isBeforeFirst()) {
						rs.first();
					}else {
						setValues(rs);
					}
				}catch (SQLException e) {
					e.printStackTrace();
				}finally {
					btnInsert.enable();
				}
				
			}
		});
		btnNext.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					rs.next();
					if(rs.isAfterLast()) {
						rs.last();
					}else {
						setValues(rs);
					}
				}catch (SQLException e) {
					e.printStackTrace();
				}finally {
					btnInsert.enable();
				}
				
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					rs.close();
					cnn.close();
					System.exit(0);
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnInsert.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String Name=tname.getText();
				String Sex=jcobSex.getSelectedItem().toString();
				String Room=troom.getText();
				String Major=tmajor.getText();
				try {
					st.executeUpdate("INSERT INTO Students(name,sex,room,major) "
							+ "VALUES('"+Name+"','"+Sex+"','"+Room+"','"+Major+"');");
					JOptionPane.showMessageDialog(null, "One Recod was add to Student Table.");
					rs=st.executeQuery("SELECT * FROM Students;");
					rs.last();
					rowCount=rs.getRow();
					lcurrentIndex.setText("Current Index:"+rs.getRow()+"/"+ rowCount);
				}catch (SQLException e) {
					e.printStackTrace();
				}finally {
					btnInsert.disable();
				}
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String Name=tname.getText();
				String Sex=jcobSex.getSelectedItem().toString();
				String Room=troom.getText();
				String Major=tmajor.getText();
				try {
					//tmajor.enable();
					st.executeUpdate("UPDATE TABLE Students SET name='"+Name+"',sex='"+Sex+"','"+Room+"' WHERE major='"+Major+"';");
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void setValues(ResultSet sr) throws SQLException {
		tname.setText(rs.getString(1));
		jcobSex.setSelectedItem(rs.getString(2));
		troom.setText(rs.getString(3));
		tmajor.setText(rs.getString(4));
		lcurrentIndex.setText("Current Index:"+rs.getRow()+"/"+ rowCount);
	}
	public static void main(String[] args) /*throws ClassNotFoundException, InstantiationException, 
	IllegalAccessException, UnsupportedLookAndFeelException*/ 
	{
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new InsertUpdate();

	}

}
