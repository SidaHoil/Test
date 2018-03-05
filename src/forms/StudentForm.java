package forms;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import database.PrepareStatements;

public class StudentForm extends JFrame{
	private JPanel pnlButton = new JPanel(new GridLayout(1, 4));
	
	private JButton btnFirst = new JButton("First");
	private JButton btnPreviouse = new JButton("Previouse");
	private JButton btnNext = new JButton("Next");
	private JButton btnLast = new JButton("Last");
	
	private GridLayout grd = new GridLayout(4, 3);
	private JPanel pnlContian = new JPanel(grd);
	private JLabel lblId = new JLabel("Id");
	private JLabel lblName = new JLabel("Name");
	private JLabel lblSex = new JLabel("Sex");
	private JLabel lblScore = new JLabel("Score");
	private JLabel lblRec = new JLabel("Rec: 0/0", JLabel.CENTER);
	private JTextField txtId = new JTextField();
	private JTextField txtName = new JTextField();
	private JTextField txtScore = new JTextField();
	private JComboBox cboSex = new JComboBox();
	
	private JButton btnInsert = new JButton("Insert");
	private JButton btnDelete=new JButton("Delete");
	private JButton btnUpdate=new JButton("Update");
	
	 PrepareStatements p=new PrepareStatements();
	 ResultSet rss;
	 int rowCount=0;
	
	public StudentForm() {
		setTitle("Updating Database");
		setSize(320, 180);		
		
		cboSex.addItem("Male");
		cboSex.addItem("Female");
		
		pnlContian.add(lblId);
		pnlContian.add(txtId);
		pnlContian.add(btnInsert);
		
		pnlContian.add(lblName);
		pnlContian.add(txtName);
		pnlContian.add(btnUpdate);
		
		pnlContian.add(lblSex);
		pnlContian.add(cboSex);
		pnlContian.add(btnDelete);
		
		pnlContian.add(lblScore);
		pnlContian.add(txtScore);
		pnlContian.add(lblRec);
		add(pnlContian, BorderLayout.CENTER);
		
		grd.setHgap(5);
		grd.setVgap(5);
		
		pnlButton.add(btnFirst);
		pnlButton.add(btnPreviouse);
		pnlButton.add(btnNext);
		pnlButton.add(btnLast);
		add(pnlButton, BorderLayout.SOUTH);
		try {
			p.OpenConnection();
			rss=p.Select();
			rss.first();
			SetValue(rss);
			rss.last();
	    		rowCount = rss.getRow();
			btnFirst.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						rss.first();
						SetValue(rss);
						lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
			btnPreviouse.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						rss.previous();
						if(rss.isBeforeFirst())
							rss.first();
						else {
							SetValue(rss);
							lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);
						}	
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
			});
			btnNext.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						rss.next();
						if(rss.isAfterLast())
							rss.last();
						else {
							SetValue(rss);
							lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
			btnLast.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						rss.last();
						SetValue(rss);
						lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					
				}
			});
			btnInsert.addActionListener(new ActionListener() {		
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int id = Integer.parseInt(txtId.getText());
					String name = txtName.getText();
					String sex = cboSex.getSelectedItem().toString();
					float score = Float.parseFloat(txtScore.getText());
					try {
						int n=p.Insert(id, name, sex, score);
						Clear();
						if(n==1)
							System.out.println(n+ " was inseted.");
						else
							System.out.println("Insert not success.");
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
			btnUpdate.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int id = Integer.parseInt(txtId.getText());
					String name = txtName.getText();
					String sex = cboSex.getSelectedItem().toString();
					float score = Float.parseFloat(txtScore.getText());
					try {
						int n=p.Update(id, name, sex, score);
						Clear();
						if(n==1)
							System.out.println(n+" record was updated.");
						else
							System.out.println("Update not success.");
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
			btnDelete.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						int id = Integer.parseInt(txtId.getText());
						int n=p.Delete(id);
						System.out.println(n);
						Clear();
						if(n>=1)
							System.out.println(n+ " record was deleted.");
						else
							System.out.println("Delete not success.");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
			});
		}catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				try {
					p.CloseConnection();
					System.exit(0);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void Clear() {
		txtId.setText("");
		txtName.setText("");
		txtScore.setText("");
	}
	public void SetValue(ResultSet rs) throws SQLException {
		txtId.setText(rs.getString(1));
		txtName.setText(rs.getString(2));
		cboSex.setSelectedItem(rs.getString(3));
		txtScore.setText("" + rs.getInt(4));
	}
	public static void main(String[] args) throws ClassNotFoundException, 
	InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new StudentForm();
	}
	
}
