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
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ColorForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5717668748108823765L;
	/**
	 * 
	 */
	private JPanel colorFrm;
	private JTextField txtColorId;
	private JTextField txtColorName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ColorForm frame = new ColorForm();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	public ColorForm() {
		setTitle("Colors");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 321, 214);
		colorFrm = new JPanel();
		colorFrm.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(colorFrm);
		colorFrm.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(37, 11, 83, 34);
		colorFrm.add(lblId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(37, 60, 83, 34);
		colorFrm.add(lblName);
		
		txtColorId = new JTextField();
		txtColorId.setBounds(89, 18, 168, 27);
		colorFrm.add(txtColorId);
		txtColorId.setColumns(10);
		
		txtColorName = new JTextField();
		txtColorName.setColumns(10);
		txtColorName.setBounds(89, 67, 168, 27);
		colorFrm.add(txtColorName);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					cnn = SqlConnection.dbConnector();
				    PreparedStatement ps = cnn.prepareStatement("INSERT into tbl_color(color_id,color_name) values(?,?)");
				    ps.setString(1, txtColorId.getText());
				    ps.setString(2, txtColorName.getText());
				    JOptionPane.showMessageDialog(null, "Data Inserted");
				    ps.executeUpdate();
				    txtColorId.setText(" "); 
					txtColorName.setText(" ");
					txtColorId.requestFocus();
				   }catch(Exception ex){
				       ex.printStackTrace();
				   }
			}
		});
		btnSave.setBounds(50, 121, 92, 34);
		colorFrm.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtColorId.setText(" "); 
				txtColorName.setText(" ");
				txtColorId.requestFocus();
			}
		});
		btnCancel.setBounds(165, 121, 92, 34);
		colorFrm.add(btnCancel);
		
		
	}
	

}
