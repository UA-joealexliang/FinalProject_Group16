import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

import javax.swing.*;
public class CategoryGUI extends JFrame{
	//declare the private variables
	private JPanel newCategoryPanel;
	private JLabel catNameLabel;
	private JTextField catNameField;
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//try catch block
				try {
					//create object CategoryGUI
					new CategoryGUI();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public CategoryGUI() {
		newCategoryPanel = new JPanel();//creates object pannel
		newCategoryPanel.setLayout(new BoxLayout(newCategoryPanel, BoxLayout.Y_AXIS)); //vertical
		//create all labels and textfields
		catNameLabel = new JLabel("Category Name:");
		catNameField = new JTextField();
		//add all components to panel
		newCategoryPanel.add(catNameLabel);
		newCategoryPanel.add(catNameField);
		//add transaction functionality
		int result = JOptionPane.showConfirmDialog(null, newCategoryPanel, "Create New Category", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			//try catch block resolves any format conversion errors
			try {
				String catName = this.catNameField.getText();
				//create transaction
				Category category = new Category();
				budget.add_category(catName);
				JOptionPane.showMessageDialog(null, "New Transaction Created:"+"\nDate: "+transDate+"\nPaid To: "+transPayee+"\nAmount Paid: "+transAmount+"\nDescription: "+transDesc, "Success", JOptionPane.PLAIN_MESSAGE);
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
