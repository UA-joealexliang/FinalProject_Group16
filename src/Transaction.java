
public class Transaction {
	private double amount;
	private String date;
	private Payee payee;
	private String description;
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getAmount() {
		return this.amount;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return this.date;
	}
	
	public void setPayee(Payee payee) {
		this.payee = payee;
	}
	public Payee getPayee() {
		return this.payee;
	}
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	public String getDescription() {
		return this.description;
	}
}
