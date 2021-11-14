import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class TransactionGUI extends JFrame{
	//declare the private variables
		private JPanel contentPane;
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					//try catch block
					try {
						//create object TransactionGUI
						TransactionGUI frame = new TransactionGUI(); 
						frame.setVisible(true);
						
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	public TransactionGUI() {
		setTitle("New Transaction");//set frame title
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//set default operation
		setBounds(100, 100, 450, 300); //sets bounds of frame
		contentPane = new JPanel();//creates object pannel
		contentPane.setBorder(new EmptyBorder(5,5,5,5));//sets border
		setContentPane(contentPane); //set contentpane
		contentPane.setLayout(null); //set null
		
		
	}
}
