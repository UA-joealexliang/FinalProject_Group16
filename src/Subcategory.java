import java.io.Serializable;
import java.util.ArrayList;
//NAMING CONVENTIONS:
// variable_m denotes monthly  
// variable_a denotes all time  
// net_ dentoes net 
public class Subcategory implements Serializable{
	private String name; //name of the category
	protected Double out_a = 0.00; //out_a : money out SINCE CATEGORY INCEPTION
	protected Double out_m = 0.00; 
	protected Double in_a = 0.00; //money in all time
	protected Double in_m = 0.00; //money that's in_m this month
	protected GoalByDate goal; //goal is optional, used to save up money for things; goals doesn't actually assign money though. 

	// better explained mathematically:
	private ArrayList<Transaction> alltransactionList = new ArrayList<Transaction>();
	private ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
	//rename availableFunds to net_a?? 
	
	public Subcategory(String name, Double amount) {
		this.name = name;
		this.in_m = amount;
		this.in_a = amount;
	}
	
	public Subcategory(String name) {
		this.name = name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	public void set_goal(Double amount, Integer month, Integer day, Integer year) {
		this.goal = new GoalByDate(amount, month, day, year);
	}
	
	public Double get_available() {
		return this.in_a - this.out_a;
	}
	
	public void addTransaction(Transaction transaction) {
		this.transactionList.add(transaction);
		//this.in_a -= transaction.getAmount();
		this.out_m += transaction.getAmount();
		this.out_a += transaction.getAmount();
		
	}
	public ArrayList<Transaction> getTransactionList() {
		return this.transactionList;
	}
	protected Goal getGoal() {
		return this.goal;
	}
	
	//2nd way to keep track of monthly spending 
	public Double get_money_out() {  
		//calculate based on summing up transactions
		for (Transaction transaction : this.transactionList) {
			this.out_a = this.out_a + transaction.getAmount();
		}
		return this.out_a;
	}
	
	
	public String displayBalanceLeft() {
		String balanceLeft = "";
		this.get_money_out();
		Double leftOver = this.goal.amount - this.out_a;
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
	
	public void print() { //name, amount in_m this month, amount spent, amount available
		Double net_a = this.in_a - this.out_a;
		
		if (this.goal == null) {
//									name					in_m 						spent 					avaialble					goal							
			System.out.println(
					this.format_name() + "\t" + this.in_m.toString() + "\t\t" + out_m.toString() + "\t\t" + net_a.toString());
		}
		else {
//									name					in_m 						spent 					avaialble					goal							
			System.out.println(
					this.format_name() + "\t" + this.in_m.toString() + "\t\t" + out_m.toString() + "\t\t" + net_a.toString() + "\t\t" + this.goal.get_amt_m().toString());
		}
		
	}
	
	public void reset() {
		this.in_m = 0.00;
		this.out_m = 0.00;
		//System.out.println(this.getName()+" reset");
		for (Transaction t : this.transactionList) {
			this.alltransactionList.add(t);
			this.transactionList.remove(t);
		}
	}
	
	private String format_name() { //name, amount in_m this month, amount spend, amount available
		if (this.getName().length() < 9) {
			return this.getName() +  "       ";
		}
		return this.name;
	}
	
	public void move(Subcategory src) {
		//src.move(sc) moves sc's activity to this's fields. 
		//dst.move(
		//move transactions
		for (Transaction t: this.transactionList) {
			src.addTransaction(t);
		}
		src.in_m += this.in_m;
		src.out_m += this.out_m;
		src.out_a += this.in_a;
		src.in_a += this.in_a;

		//delete this's fields just in case, even tho we're deleting it from category and budget. 
		/*
		src.goal = null;
		src.in_a = 0.00;
		src.in_m = 0.00;
		src.out_a = 0.00;
		src.out_m = 0.00;
		src.name = " ";
		src.transactionList = null;
		*/
	}
	
	
	public String display_goal_info() {
		if (this.goal != null) {
			return goal.get_message();
		}
		return "there is no goal associated with this subcategory.";
	}
}
