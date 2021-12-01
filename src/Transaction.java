import java.time.LocalDate;
import java.util.Date;

public class Transaction {
	private int id;
	private double amount;
	private LocalDate date;
	private Payee payee;
	private String description;
	
	//constructors
	Transaction() {}
	Transaction(double amt){
		this.amount = amt;
	}
	
	//id is unimplemented, and may not be needed
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getAmount() {
		return this.amount;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalDate getDate() {
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
