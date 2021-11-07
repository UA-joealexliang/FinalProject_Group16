
public class Category {
	private String name;
	private double balance;
	private double goal;
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setGoal(double goal) {
		this.goal = goal;
	}
	public double getGoal() {
		return this.goal;
	}
	
	public String getBalance() {
		//calculate based on summing up transactions
	}
	
}
