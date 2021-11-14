import java.util.ArrayList;

public class Category {
	private String name; //name of the category
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
	
	public double spending(String category_name) { // sums all transactions in the category 
		for (Subcategory sc: this.subcategories) {
			if (sc.getName().equals(category_name)) {
				return sc.getCharges()	;		
			}
		}
		System.out.println("no matching category found. make sure you spelled it correctly!");
		return 0;
	}
	
	public void add_subcategory(String subcategory_name) {
		Subcategory sc = new Subcategory(subcategory_name) ;
		this.subcategories.add(sc);
	}
	
	public void print_info() { 
		return;
	}
}
