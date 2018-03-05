package forms;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InputToForm extends JFrame{
	public JTextField tname=new JTextField(),
					   tsex=new JTextField(),
					   troom=new JTextField(),
					   tmajor=new JTextField();
	public JTextArea showInfo=new JTextArea();
	public String firsMenu="1. Enroll\n2. View\n3. Update\n4.Delete\n5. Exit";
	public String sortMenu="1. Acending\n2. Descending\n 3. Back";
	public String heder="Name\tSex\tRoom\tMajor";
	public Object obj[]= { "Name : ",tname,
							"Sex : ",tsex,
							"Room : ",troom,
							"Major : ",tmajor
	};
	public ArrayList<Person> persons=new ArrayList<Person>();
	public int whichMenu=-1,more=-1,missing=-1;
	
	public InputToForm() {
		Person p=new Person();
		//System.out.println(whichMenu);
//		do {
//			whichMenu=Integer.parseInt(Menu(firsMenu));
//			switch(whichMenu) {
//			case 1://Enroll
//				try {
//					do {
//						missing=Input(obj,"Input Person Information");
//						
//						//boolean condition=Name!=null && Sex!=null && Room!=null && Major!=null;
//						if(missing==JOptionPane.OK_OPTION ) {
////							System.out.println(Name+ " He");
////							if(Name==null||Name=="") {
////								System.out.println("name null");
////							}
////							else if(Sex==null) {
////								System.out.println("sex null");
////							}
////							else if(Room==null) {
////								System.out.println("room null");
////							}
////							else if(Major==null) {
////								System.out.println("major is null");
////							}
////							else {
//								//insert 
//								System.out.println("Ok");
//								more=Question("");
////							}
//						}
//						else if(missing==JOptionPane.CANCEL_OPTION) {
//							break;
//						}
//						if(more==2)break;
//					}while(more!=JOptionPane.CANCEL_OPTION);	
//				}catch (Exception e) {	
//				}
//				break;
//			case 2://View
//				
//				break;
//			case 3://Update
//				break;
//			case 4://Delete
//				break;
//			case 5://Exit
//				break;
//			}
//			
//		}while(whichMenu!=5);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
	}
	public void Sort(ArrayList<Person> allp) {
		Person p=new Person();
		for(int i=0;i<allp.size();i++) {
			if(allp.get(i).getName().compareTo(allp.get(i+1).getName())>0) {
				p=allp.get(i);
				//allp.get(i)=allp.get(i+1);
				
			}
		}
	}
	public String Menu(String s) {
		return JOptionPane.showInputDialog(s);
	}
	public int Input(Object ob,String s) {
		return JOptionPane.showConfirmDialog(null, ob,s,
				JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
	}
	public int Question(String s) {
		return JOptionPane.showConfirmDialog(null, s,"Information",
				JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
	}
	public void ShowInfo(String s) {
		showInfo.setEditable(false);
		showInfo.setFont(new Font("Toppan Bunkyu Midashi Gothic Extrabold",Font.PLAIN, 20));
		showInfo.setBackground(new Color(238, 238, 238));
		showInfo.setForeground(Color.blue);
		showInfo.setText(s);
		JOptionPane.showMessageDialog(null, showInfo,
				"All Persons Info",JOptionPane.PLAIN_MESSAGE);;
	}
}
