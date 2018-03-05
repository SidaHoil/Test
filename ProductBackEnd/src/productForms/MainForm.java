package productForms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import unitProductFormView.ProductInfo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainForm {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 831, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		JMenu mnInsert = new JMenu("Insert");
		mnFile.add(mnInsert);
		
		JMenuItem mntmPosition = new JMenuItem("Role");
		mntmPosition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoleForm rolfrm= new RoleForm();
				rolfrm.setVisible(true);
				rolfrm.setLocationRelativeTo(null);
			}
		});
		mnInsert.add(mntmPosition);
		
		JMenuItem mntmEmployee = new JMenuItem("Employee");
		mnInsert.add(mntmEmployee);
		
		JMenuItem mntmTypeOfProduct = new JMenuItem("Category");
		mntmTypeOfProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoryForm catfrm = new CategoryForm();
				catfrm.setVisible(true);
				catfrm.setLocationRelativeTo(null);
			}
		});
		mnInsert.add(mntmTypeOfProduct);
		
		JMenuItem mntmCompany = new JMenuItem("Company");
		mnInsert.add(mntmCompany);
		
		JMenuItem mntmColor = new JMenuItem("Color");
		mntmColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ColorForm colfrm= new ColorForm();
				colfrm.setVisible(true);
				colfrm.setLocationRelativeTo(null);
			
			}
		});
		mnInsert.add(mntmColor);
		
		JMenuItem mntmImportProducts = new JMenuItem("Import products");
		mnInsert.add(mntmImportProducts);
		
		JMenuItem mntmImportUnitProdcut = new JMenuItem("Import unit prodcut");
		mntmImportUnitProdcut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UnitProForm upfrm = new UnitProForm();
				upfrm.setVisible(true);
				upfrm.setLocationRelativeTo(null);
			}
		});
		mnInsert.add(mntmImportUnitProdcut);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmUndo = new JMenuItem("Undo");
		mnEdit.add(mntmUndo);
		
		JMenuItem mntmCut = new JMenuItem("Cut");
		mnEdit.add(mntmCut);
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mnEdit.add(mntmCopy);
		
		JMenuItem mntmPaste = new JMenuItem("Paste");
		mnEdit.add(mntmPaste);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem mntmCategory = new JMenuItem("Category");
		mnView.add(mntmCategory);
		
		JMenuItem mntmPosition_1 = new JMenuItem("Position");
		mnView.add(mntmPosition_1);
		
		JMenuItem mntmRole = new JMenuItem("Role");
		mnView.add(mntmRole);
		
		JMenuItem mntmColors = new JMenuItem("Colors");
		mnView.add(mntmColors);
		
		JMenuItem mntmCompany_1 = new JMenuItem("Company");
		mnView.add(mntmCompany_1);
		
		JMenuItem mntmUnitProduct = new JMenuItem("Unit Product");
		mntmUnitProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductInfo profrm = new ProductInfo();
				profrm.setVisible(true);
				profrm.setLocationRelativeTo(null);
			}
		});
		
		mnView.add(mntmUnitProduct);
		
		JMenuItem mntmImportProductsInfo = new JMenuItem("Import Products Info");
		mnView.add(mntmImportProductsInfo);
		
		JMenuItem mntmEmployeeInfo = new JMenuItem("Employee Info");
		mnView.add(mntmEmployeeInfo);
	}

}
