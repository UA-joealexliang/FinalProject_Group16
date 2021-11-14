import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.*;
public class MainGUI extends JFrame {
	//declare the private variables
	private JPanel contentPane;
	private JPanel categoryPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//try catch block
				try {
					//create object MainGUI
					MainGUI frame = new MainGUI(); 
					frame.setVisible(true);
					
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//create frame
	public MainGUI() {//constructor
		setTitle("Budget");//set frame title
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//set default operation
		setBounds(100, 100, 450, 300); //sets bounds of frame
		
		//for transaction
		contentPane = new JPanel();//creates object panel
		contentPane.setBorder(new EmptyBorder(5,5,5,5));//sets border
		setContentPane(contentPane); //set contentpane
		contentPane.setLayout(null); //set null
		JButton AddTransaction = new JButton("Add Transaction");//setting transaction button 
		AddTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionGUI frame = new TransactionGUI();//calls object TransactionGUI and sets it visible to true
				frame.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //set default close operation
			}
		});
		
		//for Category
		categoryPane = new JPanel();//creates object panel
		categoryPane.setBorder(new EmptyBorder(5,5,5,5));//set categoryPane
		JLabel Label1 = new JLabel("Categories");
		JLabel Label2 = new JLabel("Assigned");
		JLabel Label3 = new JLabel("Available");
		JButton AddCategory = new JButton("Add Category"); //setting category button
		AddCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoryGUI frame = new CategoryGUI();//calls object CategoryGUI and sets it visible to true
				frame.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //set default close operation
			}
		});
		//AddTransaction.setBounds(180,195, 78, 25)
		
		
		
		//add buttons to contentPane
		contentPane.add(AddTransaction);//transaction button
		//add for categoryPane
		categoryPane.add(Label1);
		categoryPane.add(Label2);
		categoryPane.add(Label3);
		categoryPane.add(AddCategory);
		//add components to the frame 
		frame.getContentPane().add(BorderLayout.SOUTH, contentPane);//sets button to the bottom
		frame.getContentPane().add(BorderLayout.NORTH, categoryPane);//sets button to the bottom
	}
}
