package productForms;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CategoryForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryForm frame = new CategoryForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection cnn = null;
	/**
	 * Create the frame.
	 */
	public CategoryForm() {
		setTitle("Category");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 312, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("ID");
		label.setBounds(34, 11, 83, 34);
		contentPane.add(label);
		
		JLabel label1 = new JLabel("Name");
		label1.setBounds(34, 60, 83, 34);
		contentPane.add(label1);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(86, 18, 168, 27);
		contentPane.add(txtId);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					cnn = SqlConnection.dbConnector();
				    PreparedStatement ps = cnn.prepareStatement("INSERT into tbl_category(id,name) values(?,?)");
				    ps.setString(1, txtId.getText());
				    ps.setString(2, txtName.getText());
				    JOptionPane.showMessageDialog(null, "Data Inserted");
				    ps.executeUpdate();
				    txtId.setText(" "); 
					txtName.setText(" ");
					txtId.requestFocus();
				   }catch(Exception ex){
				       ex.printStackTrace();
				   }
			}
		});
		btnSave.setBounds(47, 121, 92, 34);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtId.setText(" "); 
				txtName.setText(" ");
				txtId.requestFocus();
			}
		});
		btnCancel.setBounds(162, 121, 92, 34);
		contentPane.add(btnCancel);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(86, 67, 168, 27);
		contentPane.add(txtName);
	}

}
