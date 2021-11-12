import java.util.ArrayList;
import java.util.Collections;

public class Budget {
	public ArrayList<Subcategory> Categories = new ArrayList<Subcategory>();
	public double total_income; 
	public double unassigned_balance;
	public double total_assigned;
	
	public double get_total_balance( ) {
		return 2;
	}
	public double add_category(String name, Double amount ) {
		return 2;
	}
	public void add_subcategory(String parent, String name, double amount) {
		return;
	}
	
}
