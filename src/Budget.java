import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
		int idx = _find_category_idx(parent);
		if (idx != -1 ) {
			Subcategory sc = new Subcategory(name, amount); 
			this.categories.get(idx).add_subcategory(sc);
		}
		
	}
	public void print_category_info(String cat) {
		Integer idx = _find(cat);
		if (idx == -1) {
			return;
		}
		else {
			this.categories.get(idx).print_info();
		}
	}
	
	private Integer _find(String name) {
		Integer i = 0;
		for (Category c: this.categories) {
			if (c.getName().equals(name)) {
				return i;
			}
			i = i +1;
		}
		
		System.out.println("no matching catories found. check spelling or create a new subcategory" + name);
		return -1;
	}
	
	public void add_transaction(String subcat, double amount) {
		Transaction T = new Transaction(amount);
		int idx = 0;
		for (Category c: this.categories) {
			idx = c._find_subcategory_idx(subcat);
				if (idx == -1) { return;}
			else {
				c.getSubcategories().get(idx).addTransaction(T);
			}
		}
		
	}
	private  int _find_category_idx(String cat_name) {
		int i = 0;
		
		for (Category c: this.categories) {
			if (c.getName().equals(cat_name)) {
				return i;
			}
			i = i+1; 
		}
		System.out.println("no matching categories found. check spelling or create a new subcategory" + cat_name);
		return -1;
	}
	
	//check if it's the 1st of the month
	private boolean _is1st() { 
		boolean is1st = false;
		//getting the date
		LocalDate now = LocalDate.now();	
		Integer day_of_month = now.getDayOfMonth();
		if (day_of_month.equals(1)) { //if it's the 1st
				is1st = true;
			}
		return is1st;
		}
	
	//check if it's before a certain  hour   
	private boolean _is_before(int hour) {
		boolean is_past_hour = false;
		//getting the hour
		Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
		Integer now_hour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
		if (now_hour < hour) { //if it's after hour
			is_past_hour = true;
		}
		return is_past_hour;
	}
	
	
	//send a cascading reset to category, which will send to subcategory
	// if it't the 1st of the month and before some early hour, like 1:00 am. 
	public boolean is_reset_time() { 
		while(true) {
			boolean reset = false;
			int hour = 1;
			boolean is_past_hour = _is_before(1);
			boolean is1st = _is1st();
			if (is_past_hour & is1st ) {
				reset = true;
			}
			return reset;
		}
	}
	
}//end class def 


