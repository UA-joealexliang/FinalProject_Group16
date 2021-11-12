import java.util.ArrayList;

public class Subcategory {
	private String name; //name of the category
	private double charges; //charges is the total sum of all transactions 
	private Goal goal;
	private ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setGoal(Goal goal) {
		this.goal = goal;
	}
	public Goal getGoal() {
		return this.goal;
	}
	
	public void addTransaction(Transaction transaction) {
		this.transactionList.add(transaction);
	}
	public ArrayList<Transaction> getTransactionList() {
		return this.transactionList;
	}
	
	public double getCharges() {
		//calculate based on summing up transactions
		this.charges = 0;
		for (Transaction transaction : this.transactionList) {
			this.charges = this.charges + transaction.getAmount();
		}
		return this.charges;
	}
	public double calculateBalanceLeft() {
		return this.goal.amount - this.charges;
	}
	public String displayBalanceLeft() {
		String balanceLeft = "";
		this.getCharges();
		double leftOver = this.goal.amount - this.charges;
		if (leftOver > 0) {
			balanceLeft = "There is $"+leftOver+" remaining for the category "+this.name;
		}
		else if (leftOver == 0) {
			balanceLeft = "There is no remaining budget for the category "+this.name;
		}
		else {
			leftOver = leftOver*-1;
			balanceLeft = "You have overspent $"+leftOver+" for the category "+this.name;
		}
		return balanceLeft;
	}
	
}
