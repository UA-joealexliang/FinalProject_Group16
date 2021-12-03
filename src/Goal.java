import java.io.Serializable;

public class Goal implements Serializable{
	public double amount;
	
	public void set_amount(double amount) {
		this.amount = amount;
	}
	public double get_amount() {
		return this.amount;
	}
	
	
}
