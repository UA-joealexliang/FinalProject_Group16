
public class Driver1 {
	public static void main(String[] args) {

		Budget B = new Budget();
		B.add_category("Kitchen", 1000.00);
		B.add_subcategory("groceries", "Kitchen", 100);
		B.print_category_info("Kitchen");
	}
	
}
