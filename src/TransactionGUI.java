import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class TransactionGUI extends JFrame{
	//declare the private variables
	private JPanel newTransactionPanel;
	private JLabel transIdLabel;
	private JLabel transAmountLabel;
	private JLabel transDateLabel;
	private JLabel transPayeeLabel;
	private JLabel transDescLabel;
	private JTextField transIdField;
	private JTextField transAmountField;
	private JTextField transDateField;
	private JTextField transPayeeField;
	private JTextField transDescField;
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//try catch block
				try {
					//create object TransactionGUI
					new TransactionGUI(); 
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public TransactionGUI() {
		newTransactionPanel = new JPanel();//creates object pannel
		newTransactionPanel.setLayout(new BoxLayout(newTransactionPanel, BoxLayout.Y_AXIS)); //vertical
		//create all labels and textfields
		//transIdLabel = new JLabel("Transaction ID:");
		//transIdField = new JTextField();
		transAmountLabel = new JLabel("Amount Paid:");
		transAmountField = new JTextField();
		transDateLabel = new JLabel("Transaction Date (YYYY-MM-DD):");
		transDateField = new JTextField();
		transPayeeLabel = new JLabel("Paid To:");
		transPayeeField = new JTextField();
		transDescLabel = new JLabel("Description (Optional):");
		transDescField = new JTextField();
		//add all components to panel
		//newTransactionPanel.add(transIdLabel);
		//newTransactionPanel.add(transIdField);
		newTransactionPanel.add(transDateLabel);
		newTransactionPanel.add(transDateField);
		newTransactionPanel.add(transPayeeLabel);
		newTransactionPanel.add(transPayeeField);
		newTransactionPanel.add(transAmountLabel);
		newTransactionPanel.add(transAmountField);
		newTransactionPanel.add(transDescLabel);
		newTransactionPanel.add(transDescField);
		//add transaction functionality
		int result = JOptionPane.showConfirmDialog(null, newTransactionPanel, "Add New Transaction", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			//try catch block resolves any format conversion errors
			try {
				//int transId = Integer.parseInt(this.transIdField.getText());
				double transAmount = Double.parseDouble(this.transAmountField.getText());
				String transDate = this.transDateField.getText();
				String transPayee = this.transPayeeField.getText();
				String transDesc = this.transDescField.getText();
				LocalDate date = LocalDate.parse(transDate);
				//create transaction
				Transaction newTransaction = new Transaction();
				newTransaction.setDate(date);
				//newTransaction.setPayee(transPayee); //FIXME
				newTransaction.setAmount(transAmount);
				newTransaction.setDescription(transDesc);
				JOptionPane.showMessageDialog(null, "New Transaction Created:"+"\nDate: "+transDate+"\nPaid To: "+transPayee+"\nAmount Paid: "+transAmount+"\nDescription: "+transDesc, "Success", JOptionPane.PLAIN_MESSAGE);
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
