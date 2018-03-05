package unitProductFormView;

import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ProductInfo extends JFrame {
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductInfo frame = new ProductInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	Connection cnn = null;
	 public void populateTable(){
	        MyQuery mq = new MyQuery();
	        ArrayList<UnitProductClass> list = mq.BindTable();
	        String[] columnName = {"Id","Name","Color","Category","Image","Import_ID"};
	        Object[][] rows = new Object[list.size()][6];
	        for(int i = 0; i < list.size(); i++){
	            rows[i][0] = list.get(i).getID();
	            rows[i][1] = list.get(i).getName();
	            rows[i][2] = list.get(i).getColor();
	            rows[i][3] = list.get(i).getType();
	                
	            if(list.get(i).getImage() != null){
	                
	             ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getImage()).getImage()
	             .getScaledInstance(150, 120, Image.SCALE_SMOOTH) );   
	                
	            rows[i][4] = image;
	            }
	            else{
	                rows[i][4] = null;
	            }
	            rows[i][5] = list.get(i).getImportId();
	        }
	        
	      TheModel model = new TheModel(rows, columnName);
	       table.setModel(model); //errors
	       table.setRowHeight(120);
	       table.getColumnModel().getColumn(4).setPreferredWidth(150);
	    }
	/**
	 * Create the frame.
	 */
	public ProductInfo() {
		setTitle("Unit Product Info");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 694, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 658, 416);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {null, null, null, null},
	                {null, null, null, null},
	                {null, null, null, null},
	                {null, null, null, null}
	            },
	            new String [] {
	                "Title 1", "Title 2", "Title 3", "Title 4"
	            }
	        ));
		populateTable();
	}
}
