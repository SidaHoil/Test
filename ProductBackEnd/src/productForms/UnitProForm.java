package productForms;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class UnitProForm extends JFrame {

	private JPanel unitProFrm;
	private JTextField txtId;
	private JTextField txtName;
	JLabel lblImage;
	String s;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnitProForm frame = new UnitProForm();
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
	
	public UnitProForm() {
		setTitle("Unit Products");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 389, 386);
		unitProFrm = new JPanel();
		unitProFrm.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(unitProFrm);
		unitProFrm.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(41, 11, 73, 30);
		unitProFrm.add(lblId);
		
		txtId = new JTextField();
		txtId.setBounds(151, 11, 152, 30);
		unitProFrm.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(41, 52, 73, 30);
		unitProFrm.add(lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(151, 52, 152, 30);
		unitProFrm.add(txtName);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(41, 93, 73, 30);
		unitProFrm.add(lblColor);
		
		JLabel lblType = new JLabel("Category");
		lblType.setBounds(41, 134, 73, 30);
		unitProFrm.add(lblType);
		
		JComboBox<String> cbColor = new JComboBox<String>();
		try{
			cnn = SqlConnection.dbConnector();
		   PreparedStatement ps = cnn.prepareStatement("SELECT * FROM tbl_color");
		   ResultSet rs = ps.executeQuery();
		   
		   while(rs.next())
		   {
			   String cbo = rs.getString("color_name");
			   cbColor.addItem(cbo);
		   }
		   }catch(Exception ex){
		       ex.printStackTrace();
		   }
		
		cbColor.setBounds(151, 93, 152, 30);
		unitProFrm.add(cbColor);
		
		JComboBox<String> cbType = new JComboBox<String>();
		cbType.setBounds(151, 139, 152, 30);
		unitProFrm.add(cbType);
		try{
			cnn = SqlConnection.dbConnector();
		   PreparedStatement ps = cnn.prepareStatement("SELECT * FROM tbl_category");
		   ResultSet rs = ps.executeQuery();
		   
		   while(rs.next())
		   {
			   String cbo = rs.getString("name");
			   cbType.addItem(cbo);
		   }
		   }catch(Exception ex){
		       ex.printStackTrace();
		   }
		 
		JLabel label = new JLabel("Image");
		label.setBounds(41, 194, 73, 30);
		unitProFrm.add(label);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		         fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
		         fileChooser.addChoosableFileFilter(filter);
		         int result = fileChooser.showSaveDialog(null);
		         if(result == JFileChooser.APPROVE_OPTION){
		             File selectedFile = fileChooser.getSelectedFile();
		             String path = selectedFile.getAbsolutePath();
		             lblImage.setIcon(ResizeImage(path));
		             s = path;
		              }
		         else if(result == JFileChooser.CANCEL_OPTION){
		             System.out.println("No Data");
		         }
			}
		});
		
		
		
		btnBrowse.setBounds(151, 198, 89, 23);
		unitProFrm.add(btnBrowse);
		
		
		lblImage = new JLabel("Photo");
		lblImage.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblImage.setForeground(Color.LIGHT_GRAY);
		lblImage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBounds(251, 180, 64, 62);		
		unitProFrm.add(lblImage);		
		  
		JComboBox cbImportPro = new JComboBox();
		cbImportPro.setBounds(151, 250, 152, 30);
		unitProFrm.add(cbImportPro);
		cbImportPro.addItem(001);
		cbImportPro.addItem(002);
		
		JLabel lblImportProductId = new JLabel("Import Product ID");
		lblImportProductId.setBounds(41, 245, 100, 30);
		unitProFrm.add(lblImportProductId);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(202, 310, 89, 26);
		unitProFrm.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					cnn = SqlConnection.dbConnector();
				    PreparedStatement ps = cnn.prepareStatement("INSERT into tbl_unit_product(unit_product_id,unit_product_name,unit_product_color,unit_product_type,unit_product_image,import_product_id) values(?,?,?,?,?,?)");
				    InputStream is = new FileInputStream(new File(s));
				    ps.setString(1, txtId.getText());
				    ps.setString(2, txtName.getText());
				    ps.setString(3, cbColor.getSelectedItem().toString());
				    ps.setString(4, cbType.getSelectedItem().toString());
				    ps.setBlob(5,is);
				    ps.setString(6, cbImportPro.getSelectedItem().toString());
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
		btnSave.setBounds(97, 310, 89, 26);
		unitProFrm.add(btnSave);
	}
	 //Methode To Resize The ImageIcon
    public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(lblImage.getWidth()+15, lblImage.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }
}
