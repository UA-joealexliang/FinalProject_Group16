import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import javax.swing.*;

import java.awt.*;

public class BudgetGUI extends JFrame {
	private final int WINDOW_WIDTH = 400;  // Window width
	private final int WINDOW_HEIGHT = 300; // Window height
	
	//Menu component variables
	private JMenuBar menuBar;
	//fileMenu components
	private JMenu fileMenu;
	private JMenuItem exitItem;
	private JMenuItem saveDataItem;
	private JMenuItem loadDataItem;
	//categoryMenu components
	private JMenu categoryMenu;
	private JMenuItem addCategoryItem;
	private JMenuItem addSubcategoryItem;
	private JMenuItem addTransactionItem;
	private JMenuItem addPayeeItem;
	private JMenuItem addMonthlyIncomeItem;
	//viewMenu components
	private JMenu viewMenu;
	private JMenuItem printCategoryInfoItem;
	private JMenuItem printTransactionInfoItem;
	
	//addMonthlyIncomeItem
	private JPanel addMonthlyIncomePanel;
	private JLabel monthlyincomeAmountLabel;
	private JTextField monthlyincomeAmountField;
	
	//addPayee components
	private JPanel newPayeePanel;
	private JLabel payeeNameLabel;
	private JLabel payeeDescLabel;
	private JTextField payeeNameField;
	private JTextField payeeDescField;
	
	//addCategory components
	private JPanel newCategoryPanel;
	private JLabel catNameLabel;
	private JTextField catNameField;
	
	//addSubcategory components
	private JPanel newSubcategoryPanel;
	private JLabel subcatNameLabel;
	private JLabel subcatAmountLabel;
	private JLabel subcatParentLabel;
	private JTextField subcatNameField;
	private JTextField subcatAmountField;
	private JTextField subcatParentField;
	
	//addTransaction components
	private JPanel newTransactionPanel;
	private JLabel transIdLabel;
	private JLabel transAmountLabel;
	private JLabel transDateLabel;
	private JLabel transPayeeLabel;
	private JLabel transDescLabel;
	private JLabel transCatLabel;
	private JLabel transSubcatLabel;
	private JTextField transIdField;
	private JTextField transAmountField;
	private JTextField transDateField;
	private JTextField transPayeeField;
	private JTextField transDescField;
	private JTextField transCatField;
	private JTextField transSubcatField;
	
	//Serialized Data
	private Budget budget;

	//for testing and debugging
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//try catch block
				try {
					//create object BudgetGUI
					new BudgetGUI("BudgetGUI", new Budget());
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public BudgetGUI(String windowTitle, Budget budget) {
		//call the JFrame constructor and pass the title
		super(windowTitle);
		//load budget
		this.budget = budget;
		//set the size of this window
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		//specify what happens when the close button is clicked
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//build the menu bar
		buildMenuBar();
		//make the window visible
		setVisible(true);
	}
	
	private void buildMenuBar() {
		//create the menu bar
		menuBar = new JMenuBar();
		//create the file menu
		buildFileMenu();
		//create the students menu
		buildCategoryMenu();
		//create the administrators menu
		buildViewMenu();
		//add menus to the menu bar
		menuBar.add(fileMenu);
		menuBar.add(categoryMenu);
		menuBar.add(viewMenu);
		//set the window's menu bar
		setJMenuBar(menuBar);
	}
	
	//fileMenu
	private void buildFileMenu() {
		//create JMenu object for the File menu
		fileMenu = new JMenu("File");
		//create menu items and add action listeners
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ExitListener());
		saveDataItem = new JMenuItem("Save Data");
		saveDataItem.addActionListener(new SaveDataListener());
		loadDataItem = new JMenuItem("Load Data");
		loadDataItem.addActionListener(new LoadDataListener());
		//add menu items to the file menu
 		fileMenu.add(saveDataItem);
 		fileMenu.add(loadDataItem);
 		fileMenu.add(exitItem);
	}
	private class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	private class SaveDataListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Budget.save_data(budget);
		}
	}
	private class LoadDataListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			budget = Budget.load_data();
		}
	}
	
	//categoryMenu
	private void buildCategoryMenu() {
		//create JMenu object for the Category menu
		categoryMenu = new JMenu("Category");
		//create menu items and add action listeners
		addCategoryItem = new JMenuItem("Add Category");
		addCategoryItem.addActionListener(new AddCategoryListener());
		addSubcategoryItem = new JMenuItem("Add Subcategory");
		addSubcategoryItem.addActionListener(new AddSubcategoryListener());
		addTransactionItem = new JMenuItem("Add Transaction");
		addTransactionItem.addActionListener(new AddTransactionListener());
		addPayeeItem = new JMenuItem("Add Payee");
		addPayeeItem.addActionListener(new AddPayeeListener());
		addMonthlyIncomeItem = new JMenuItem("Add Paycheck");
		addMonthlyIncomeItem.addActionListener(new AddMonthlyIncomeListener());
		//add menu items to the category menu
 		categoryMenu.add(addCategoryItem);
 		categoryMenu.add(addSubcategoryItem);
 		categoryMenu.add(addMonthlyIncomeItem);
 		categoryMenu.add(addTransactionItem);
 		categoryMenu.add(addPayeeItem);
	}
	private class AddCategoryListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			newCategoryPanel = new JPanel();//creates object panel
			newCategoryPanel.setLayout(new BoxLayout(newCategoryPanel, BoxLayout.Y_AXIS)); //vertical
			//create all labels and textfields
			catNameLabel = new JLabel("Category Name:");
			catNameField = new JTextField();
			//add all components to panel
			newCategoryPanel.add(catNameLabel);
			newCategoryPanel.add(catNameField);
			//add category functionality
			int result = JOptionPane.showConfirmDialog(null, newCategoryPanel, "Create New Category", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				//try catch block resolves any format conversion errors
				try {
					String catName = catNameField.getText();
					//create category
					ByteArrayOutputStream bOutput = new ByteArrayOutputStream(); //new storage for outputstream
					PrintStream pStream = new PrintStream(bOutput);
					PrintStream old = System.out; //save old output stream config
					System.setOut(pStream);
					boolean success = budget.add_category(catName);
					System.out.flush(); 
					System.setOut(old); //return to old output stream (console)
					if (success == true) {
						JOptionPane.showMessageDialog(null, "New Category Created: "+catName, "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, bOutput.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	private class AddSubcategoryListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			newSubcategoryPanel = new JPanel();
			newSubcategoryPanel.setLayout(new BoxLayout(newSubcategoryPanel, BoxLayout.Y_AXIS)); //vertical
			subcatParentLabel = new JLabel("Parent Category Name:");
			subcatParentField = new JTextField();
			subcatNameLabel = new JLabel("Subcategory Name:");
			subcatNameField = new JTextField();
			subcatAmountLabel = new JLabel("Amount Assigned Per Month:");
			subcatAmountField = new JTextField();
			newSubcategoryPanel.add(subcatParentLabel);
			newSubcategoryPanel.add(subcatParentField);
			newSubcategoryPanel.add(subcatNameLabel);
			newSubcategoryPanel.add(subcatNameField);
			newSubcategoryPanel.add(subcatAmountLabel);
			newSubcategoryPanel.add(subcatAmountField);
			int result = JOptionPane.showConfirmDialog(null, newSubcategoryPanel, "Create New Subcategory", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				try {
					String subcatParent = subcatParentField.getText();
					String subcatName = subcatNameField.getText();
					double subcatAmount = Double.parseDouble(subcatAmountField.getText());
					ByteArrayOutputStream bOutput = new ByteArrayOutputStream(); //new storage for outputstream
					PrintStream pStream = new PrintStream(bOutput);
					PrintStream old = System.out; //save old output stream config
					System.setOut(pStream);
					boolean success = budget.add_subcategory(subcatParent, subcatName, subcatAmount);
					System.out.flush(); 
					System.setOut(old); //return to old output stream (console)
					if (success == true) {
						JOptionPane.showMessageDialog(null, "New Subcategory Created: \""+subcatName+"\" under parent Category \""+subcatParent+"\" with a monthly budget of $"+subcatAmount, "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, bOutput.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	private class AddMonthlyIncomeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			addMonthlyIncomePanel = new JPanel();
			addMonthlyIncomePanel.setLayout(new BoxLayout(addMonthlyIncomePanel, BoxLayout.Y_AXIS)); //vertical
			monthlyincomeAmountLabel = new JLabel("Paycheck Amount:");
			monthlyincomeAmountField = new JTextField();
			addMonthlyIncomePanel.add(monthlyincomeAmountLabel);
			addMonthlyIncomePanel.add(monthlyincomeAmountField);
			int result = JOptionPane.showConfirmDialog(null, addMonthlyIncomePanel, "Add New Paycheck", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				try {
					double paycheckAmount = Double.parseDouble(monthlyincomeAmountField.getText());
					budget.set_monthly_in(paycheckAmount);
					JOptionPane.showMessageDialog(null, "New paycheck amount $"+paycheckAmount+" has been added. Your new total balance is $"+budget.net_a, "Paycheck Added", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	private class AddTransactionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
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
			transCatLabel = new JLabel("Category:");
			transCatField = new JTextField();
			transSubcatLabel = new JLabel("Subcategory:");
			transSubcatField = new JTextField();
			//add all components to panel
			//newTransactionPanel.add(transIdLabel);
			//newTransactionPanel.add(transIdField);
			newTransactionPanel.add(transCatLabel);
			newTransactionPanel.add(transCatField);
			newTransactionPanel.add(transSubcatLabel);
			newTransactionPanel.add(transSubcatField);
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
					double transAmount = Double.parseDouble(transAmountField.getText());
					String transDate = transDateField.getText();
					String transPayee = transPayeeField.getText();
					String transDesc = transDescField.getText();
					LocalDate date = LocalDate.parse(transDate);
					String transCat = transCatField.getText();
					String transSubcat = transSubcatField.getText();
					//check for any errors before adding transaction
					ByteArrayOutputStream bOutput = new ByteArrayOutputStream(); //new storage for outputstream
					PrintStream pStream = new PrintStream(bOutput);
					PrintStream old = System.out; //save old output stream config
					System.setOut(pStream);
					Subcategory subcategory = budget.getSubcategory(transCat, transSubcat);
					System.out.flush(); 
					System.setOut(old); //return to old output stream (console)
					Payee payee = budget.getPayee(transPayee);
					if (subcategory != null) {
						if (payee != null) {
							//create transaction
							Transaction newTransaction = new Transaction();
							newTransaction.setDate(date);
							newTransaction.setPayee(payee);
							newTransaction.setAmount(transAmount);
							newTransaction.setDescription(transDesc);
							subcategory.addTransaction(newTransaction);
							JOptionPane.showMessageDialog(null, "New Transaction Created:"+"\nDate: "+transDate+"\nPaid To: "+transPayee+"\nAmount Paid: "+transAmount+"\nDescription: "+transDesc, "Success", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							int result2 = JOptionPane.showConfirmDialog(null, "Add \""+transPayee+"\" as a new payee?", "Alert", JOptionPane.OK_CANCEL_OPTION);
							if (result2 == JOptionPane.OK_OPTION) {
								newPayeePanel = new JPanel();
								newPayeePanel.setLayout(new BoxLayout(newPayeePanel, BoxLayout.Y_AXIS)); //vertical
								payeeNameLabel = new JLabel("Enter payee name:");
								payeeNameField = new JTextField(transPayee);
								payeeDescLabel = new JLabel("Enter payee description (optional):");
								payeeDescField = new JTextField();
								newPayeePanel.add(payeeNameLabel);
								newPayeePanel.add(payeeNameField);
								newPayeePanel.add(payeeDescLabel);
								newPayeePanel.add(payeeDescField);
								int result3 = JOptionPane.showConfirmDialog(null, newPayeePanel, "Add New Payee", JOptionPane.OK_CANCEL_OPTION);
								if (result3 == JOptionPane.OK_OPTION) {
									payee = new Payee();
									payee.setName(payeeNameField.getText());
									payee.setDescription(payeeDescField.getText());
									budget.payeeList.add(payee);
									JOptionPane.showMessageDialog(null, "Successfully created new payee \""+payeeNameField.getText()+"\"", "Success", JOptionPane.INFORMATION_MESSAGE);
									Transaction newTransaction = new Transaction();
									newTransaction.setDate(date);
									newTransaction.setPayee(payee);
									newTransaction.setAmount(transAmount);
									newTransaction.setDescription(transDesc);
									subcategory.addTransaction(newTransaction);
									JOptionPane.showMessageDialog(null, "New Transaction Created:"+"\nDate: "+transDate+"\nPaid To: "+transPayee+"\nAmount Paid: "+transAmount+"\nDescription: "+transDesc, "Success", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Transaction could not be created, Category \""+transCat+"\" or Subcategory \""+transSubcat+"\" could not be found", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}	
	private class AddPayeeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			newPayeePanel = new JPanel();
			newPayeePanel.setLayout(new BoxLayout(newPayeePanel, BoxLayout.Y_AXIS)); //vertical
			payeeNameLabel = new JLabel("Enter payee name:");
			payeeNameField = new JTextField();
			payeeDescLabel = new JLabel("Enter payee description (optional):");
			payeeDescField = new JTextField();
			newPayeePanel.add(payeeNameLabel);
			newPayeePanel.add(payeeNameField);
			newPayeePanel.add(payeeDescLabel);
			newPayeePanel.add(payeeDescField);
			int result = JOptionPane.showConfirmDialog(null, newPayeePanel, "Add New Payee", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				Payee payee = new Payee();
				payee.setName(payeeNameField.getText());
				payee.setDescription(payeeDescField.getText());
				budget.payeeList.add(payee);
				JOptionPane.showMessageDialog(null, "Successfully created new payee \""+payeeNameField.getText()+"\"", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	//viewMenu
	private void buildViewMenu() {
		//create JMenu object for the View menu
		viewMenu = new JMenu("View");
		//create menu items and add action listeners
		printCategoryInfoItem = new JMenuItem("Print Category Info");
		printCategoryInfoItem.addActionListener(new PrintCategoryInfoListener());
		printTransactionInfoItem = new JMenuItem("Print Transaction Info");
		printTransactionInfoItem.addActionListener(new PrintTransactionInfoListener());
		//add menu items to the file menu
		viewMenu.add(printCategoryInfoItem);
		viewMenu.add(printTransactionInfoItem);
	}
	private class PrintCategoryInfoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//FIXME
		}
	}
	private class PrintTransactionInfoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//FIXME
		}
	}
	/*
	private class PrintScheduleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//create panel
			printSchedulePanel = new JPanel();
			printSchedulePanel.setLayout(new BoxLayout(printSchedulePanel, BoxLayout.Y_AXIS)); //vertical
			//create all labels and textfields
			psstudentNameLabel = new JLabel("Student Name:");
			psstudentNameField = new JTextField();
			//add all components to panel
			printSchedulePanel.add(psstudentNameLabel);
			printSchedulePanel.add(psstudentNameField);
			//add course functionality
			int result = JOptionPane.showConfirmDialog(null, printSchedulePanel, "Print Student Schedule", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				String studentName = psstudentNameField.getText();
				Student student = new Student();
				boolean studentExists = false;
				for (Department dept : university.departmentList) {
					for (Student s : dept.getStudentList()) {
						if (studentName.equals(s.getName())) {
							student = s;
							studentExists = true;
						}
					}
				}
				if (studentExists == true) {
					ByteArrayOutputStream bOutput = new ByteArrayOutputStream(); //new storage for outputstream
					PrintStream pStream = new PrintStream(bOutput);
					PrintStream old = System.out; //save old output stream config
					System.setOut(pStream);
					student.printSchedule();
					System.out.flush(); 
					System.setOut(old); //return to old output stream (console)
					JOptionPane.showMessageDialog(null, bOutput.toString(), "Student "+studentName+"'s Schedule", JOptionPane.PLAIN_MESSAGE);
				}
				else if (studentExists == false) {
					JOptionPane.showMessageDialog(null,"Student "+studentName+" doesn't exist", "Error Printing Schedule", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	*/
	/*
	private class PrintAllInfoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//create and configure window for printAllInfo
			printAllInfoFrame = new JFrame("PrintAllInfo Window");
			printAllInfoFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
			printAllInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			Container contentPane = printAllInfoFrame.getContentPane();
			//create text pane
			printAllInfoTextPane = new JTextPane();
			//change output to bOutput
			ByteArrayOutputStream bOutput = new ByteArrayOutputStream(); //new storage for outputstream
			PrintStream pStream = new PrintStream(bOutput);
			PrintStream old = System.out; //save old output stream config
			System.setOut(pStream);
			university.printAll();
			System.out.flush(); 
			System.setOut(old); //return to old output stream (console)
			//place text into InfoTextPane
			printAllInfoTextPane.setText(bOutput.toString());
			//add components to menu
			printAllInfoScrollPane = new JScrollPane(printAllInfoTextPane); //set it as a scrollpane
			contentPane.add(printAllInfoScrollPane, BorderLayout.CENTER);
			//make the menu visible
			printAllInfoFrame.setVisible(true);
		}
	}
	*/
}
