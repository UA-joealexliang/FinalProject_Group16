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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//set default operation
		setBounds(100, 100, 450, 300); //sets bounds of frame
		contentPane = new JPanel();//creates object pannel
		contentPane.setBorder(new EmptyBorder(5,5,5,5));//sets border
		setContentPane(contentPane); //set contentpane
		contentPane.setLayout(null); //set null
		JButton AddTransaction = new JButton("Add Transaction");
	}
}
