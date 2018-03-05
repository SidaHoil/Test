package productForms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class RoleForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6320518316544057588L;
	private JPanel roleFrm;
	private JTextField txtRoleId;
	private JTextField txtRoleName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoleForm frame = new RoleForm();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
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
	public RoleForm() {
		setTitle("Roles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 321, 210);
		roleFrm = new JPanel();
		roleFrm.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(roleFrm);
		roleFrm.setLayout(null);
		
		JLabel label = new JLabel("ID");
		label.setBounds(43, 11, 83, 34);
		roleFrm.add(label);
		
		txtRoleId = new JTextField();
		txtRoleId.setColumns(10);
		txtRoleId.setBounds(95, 18, 168, 27);
		roleFrm.add(txtRoleId);
		
		txtRoleName = new JTextField();
		txtRoleName.setColumns(10);
		txtRoleName.setBounds(95, 67, 168, 27);
		roleFrm.add(txtRoleName);
		
		JLabel label_1 = new JLabel("Name");
		label_1.setBounds(43, 60, 83, 34);
		roleFrm.add(label_1);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					cnn = SqlConnection.dbConnector();
				    PreparedStatement ps = cnn.prepareStatement("INSERT into tbl_role(role_id,role_name) values(?,?)");
				    ps.setString(1, txtRoleId.getText());
				    ps.setString(2, txtRoleName.getText());
				    JOptionPane.showMessageDialog(null, "Data Inserted");
				    ps.executeUpdate();
				    txtRoleId.setText(" "); 
					txtRoleName.setText(" ");
					txtRoleId.requestFocus();
				   }catch(Exception ex){
				       ex.printStackTrace();
				   }
			}
		});
		btnSave.setBounds(56, 121, 92, 34);
		roleFrm.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtRoleId.setText(" "); 
				txtRoleName.setText(" ");
				txtRoleId.requestFocus();
			}
		});
		btnCancel.setBounds(171, 121, 92, 34);
		roleFrm.add(btnCancel);
	}

}
