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
		 * TEST0: Create a budget tied to a user by their name, with $10000 net in savings
		 */
		Budget b1 = new Budget();
		b1.setName("Bob");
		System.out.println("Budget is tied to user: "+b1.getName());
		b1.net_a = 10000.00;
		
		/*
		 * TEST1: Create a some main categories
		 * >be able to rename
		 */
		b1.add_category("Kitchen");
		b1.add_category("Entertianment");
		b1.getCategory("Entertianment").setName("Entertainment");
		System.out.println("renamed category to: "+b1.categories.get(b1._find_cat("Entertainment")).getName());
		
		/*
		 * TEST2: Create some subcategories
		 * >be able to change amount allocated 
		 * >should not be able to allocate more money than in savings
		 */
		b1.add_subcategory("Kitchen", "Groceries", 1000.00);
		b1.add_subcategory("Large purchases", "Car", 20000.00);
		Category kitchen = b1.categories.get(b1._find_cat("Kitchen"));
		kitchen.subcategories.get(kitchen._find_subcategory_idx("Groceries")).set_monthly_in(900.00);
		System.out.println("Groceries monthly in: "+kitchen.subcategories.get(0).get_monthly_in());
		
		/*
		 * TEST3: Create a category for large one-time purchases, and add a new goal class to the subcategory Car
		 */
		b1.add_category("Large purchases");
		b1.add_subcategory("Large purchases", "Car", 0);
		Goal car_goal = new Goal();
		car_goal.setAmount(9000);
		car_goal.incAmount(1000);
		Subcategory sub_Car = b1.getSubcategory("Large purchases", "Car");
		sub_Car.setGoal(car_goal);
		System.out.println("Car goal: "+sub_Car.goal.getAmount());
		
		/*
		 * TEST4: Add a transaction to groceries subcategory
		 */
		Subcategory sub_groceries = b1.getSubcategory("Kitchen", "Groceries");
		Transaction t1 = new Transaction(100.00);
		sub_groceries.addTransaction(t1);
		System.out.println("Monthly budget left: "+sub_groceries.get_monthly_in());
		
		
		
	}
	
}
