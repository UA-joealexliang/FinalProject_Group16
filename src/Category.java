import java.util.ArrayList;

public class Category {
	private String name; //name of the category
	protected ArrayList<Subcategory> subcategories = new ArrayList<Subcategory>();
	
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
		for (Subcategory sc: this.getSubcategories()) {
			if (sc.getName().equals(category_name)) {
				return sc.get_money_out()	;		
			}
		}
		System.out.println("no matching category found. make sure you spelled it correctly!");
		return 0;
	}
	
	public void add_subcategory(Subcategory sc) {
		this.subcategories.add(sc);
	}
	
	public void print_info() { 
		System.out.println(this.name);
		for (Subcategory sc: this.getSubcategories()) {
			System.out.print("\t");
			sc.print_info();
		}
	}

	public ArrayList<Subcategory> getSubcategories() {
		return subcategories;
	}
	
	protected Integer _find_subcategory_idx(String name) {
		Integer i = 0;
		for (Subcategory c: this.subcategories) {
			if (c.getName().equals(name)) {
				return i;
			}
			i = i +1;
		}
		
		System.out.println("no matching catories found. check spelling or create a new subcategory" + name);
		return -1;
	}
}
