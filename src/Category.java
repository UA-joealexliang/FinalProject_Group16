import java.util.ArrayList;

public class Category {
	private String name; //name of the category
	private double charges; //charges is the total sum of all transactions 
	private Goal goal;
	private ArrayList<Subcategory> subcategories = new ArrayList<Subcategory>();
	public Category(String name) {
		this.name = name;
	}
	
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
	
	public double category_spending(String category_name) { //finds category_name, then sums 
		for (Subcategory sc: this.subcategories) {
			if (sc.getName().equals(category_name)) {
				return sc.getCharges()	;		
			}
		}
		System.out.println("no matching category found. make sure you spelled it correclty!");
		return 0;
	}
	
	public double calculateBalanceLeft() {
		return this.goal.amount - this.charges;
	}
	public String displayBalanceLeft() { //FIX ME
		String balanceLeft = "";
		return balanceLeft;
	}
}
