import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Driver1 {
	public static void main(String[] args) {
		
		/*
		Budget B = new Budget();
		
		B.add_category("Kitchen", 2000.00);
		
		B.add_subcategory("groceries", "Kitchen", 1000);
		B.add_subcategory("new pots", "Kitchen", 100.00);
		//B.print_category_info("Kitchen");
		//add a transaction
		//B.add_transaction("groceries", 100.00);
		//B.print_category_info("Kitchen");
		B.print();
		*/
		
		/*
		 * TEST0: Create a budget tied to a user by their name
		 */
		Budget b1 = new Budget();
		b1.setName("Bob");
		System.out.println("Budget is tied to user: "+b1.getName());
		
		/*
		 * TEST1: Create a some main categories
		 * >be able to rename
		 * >be able to change amount 
		 */
		b1.add_category("Kitchen", 2000.00);
		b1.add_category("Entertianment", 200.00);
		
		
		
		
	}
	
}
