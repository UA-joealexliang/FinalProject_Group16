import java.util.ArrayList;
import java.util.Collections;

public class Budget {
	public ArrayList<Category> categories = new ArrayList<Category>();
	public double total_income; 
	public double unassigned_balance;
	public double total_assigned;
	
	public double get_total_balance( ) {
		return 2;
	}
	public void add_category(String name, Double amount ) {
		Category c = new Category(name);
		this.categories.add(c);
	}
	
	public void add_subcategory(String name, String parent, double amount) {
		int i = 0;
		for (Category c: this.categories) {
			if (c.getName() == name) {
				c.add_subcategory(name);
			}
			i = i+1; 
		}
		System.out.println("no matching catories found. check spelling or create a new subcategory" + name);
	}
	
}


