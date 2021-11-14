import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.*;
public class MainGUI extends JFrame {
	//declare the private variables
	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//try catch block
				try {
					//create object MainGUI
					MainGUI frame = new MainGUI(); 
					
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
		contentPane = new JPanel();//creates object pannel
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
		
		
		//add buttons to contentPane
		contentPane.add(AddTransaction);//transaction button
		//add components to the frame 
		frame.getContentPane().add(BorderLayout.SOUTH, contentPane);//sets button to the bottom
	}
}
