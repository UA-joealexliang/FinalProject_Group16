import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Budget implements Serializable{
	public ArrayList<Category> categories = new ArrayList<Category>();
	public ArrayList<Payee> payeeList = new ArrayList<Payee>();
	public Double net_a = 0.00; //net all time
	public Double in_m = 0.00; //monthly income
	public Double in_unassigned = 0.00;
	public String name = "Unknown";
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public double get_total_balance( ) {
		return 2; //for loop
	}
	
	public boolean add_category(String cat ) {	
		//returns true if category was succesfully added, false else
		//check if it exists
		if (this.categories.size() != 0) {
			Integer exists = this._find_cat_idx_c(cat);
			if (exists == -1) {
				Category c = new Category(cat);
				this.categories.add(c);	
				return true;
			}
			else {
				System.out.println("the category "+ cat + " already exists.");
				return false;
			}
		}
		else {
			Category c = new Category(cat);
			this.categories.add(c);
			return true;
		}
	}
	public boolean add_subcategory(String parent, String name, double amount) {
		if (this.net_a >= amount) { //check if we even have this much money in the account			
			int idx = _find_category_idx_c(parent);
			if (idx != -1 ) { //category parentd name exists 
				Subcategory sc = new Subcategory(name, amount); 
				this.categories.get(idx).add_subcategory(sc);
				this.in_unassigned -= amount;
				return true;
			}
			else { //need to create a new category, and add subcat to it 
				System.out.println("No matching category " + parent + " found.");
				//FIX ME: need to add user input
				//Category c = new Category(parent);
				//Subcategory sc = new Subcategory(name, amount);
				//c.subcategories.add(sc);
				//this.categories.add(c);	
				//this.in_unassigned -= amount;
				//return true;
				return false;
			}	
		}
		else { //we do not have this amount. 
			System.out.println("You have " + this.net_a.toString() + " . You can't assign what you don't have");
			System.out.println("Options: move money from another category, or assign at most " + this.net_a.toString());
			return false;
		}
	}
	
	public boolean move_money(String src, String dst) {
		Subcategory Src = this._find_subcat(src);
		Subcategory Dst = this._find_subcat(dst);
		if (Src == null) {
			System.out.println("the subcategory " + src + " doesn't exist.");
			return false;
		}
		if (Dst == null) {
			System.out.println("the subcategory " + dst + " doesn't exist.");
			return false;
		}
		else {
			Src.in_a += Dst.in_a - Dst.out_a;
			return true;
		}
	}
	
	
	public void assign(String subcategory, double amount ) {
		if (this.net_a >= amount) {
			for (Category c: this.categories) {
				for (Subcategory sc: c.subcategories) {
					if (subcategory.equals(sc.getName())) {
						sc.set_monthly_in(amount);
						this.in_unassigned -= amount;
						break;
					}
				}
			}
		}
		else {
			System.out.println("You have " + this.net_a.toString() + " . You can't assign what you don't have");
			System.out.println("Options: move money from another category, or assign at most " + this.net_a.toString());
		}
		
	}
	
	public void print_category_info(String cat) {
		Integer idx = _find_cat_idx_c(cat);
		if (idx == -1) {
			return;
		}
		else {
			this.categories.get(idx).print();
		}
	}
	
	public Integer _find_cat_idx_c(String cat) { //finds the index of the category whith the name cat. 
		Integer i = 0;
		for (Category c: this.categories) {
			if (c.getName().equals(cat)) {
				return i;
			}
			i = i +1;
		}
		
		//System.out.println("no matching catories found. check spelling or create a new subcategory" + cat);
		return -1;
	}
	public Category _find_cat(String subcat) { //finds the index of the CATEGORY that holds subcat
		Subcategory sc = null;
		for (Category c: this.categories) {
			 sc = c._find_subcategory(subcat);
			if (sc==null) { 
				continue;
			}
			return c;
		}
		 //subcategory was not found in any of the categories 
			//System.out.println("no matching categories found. check spelling or create a new subcategory" + subcat);
			return null;
	}
	
	public Subcategory _find_subcat(String subcat) { //finds the index of the CATEGORY that holds subcat
		Subcategory sc = null;
		Category cc = null;
		for (Category c: this.categories) {
			 sc = c._find_subcategory(subcat);
			if (sc==null) { 
				continue;
			}
			cc = c;
			return cc._find_subcategory(subcat);
		}
		 return null;
	}
	public void rename_subcategory(String oldname, String newname) {
		Integer idx;
		for (Category c: this.categories) {
			idx = c._find_subcategory_idx(oldname);
			if (!idx.equals(-1)) {
				c.subcategories.get(idx).setName(newname);
				return;
			}
		}
		System.out.println("subcateogry " + oldname + " does not exist.");
	}
	//FIXME: if we delete a subcateogry, we need to ask the user what they want to do with the money in that account
	//something like: before you delete this subcateogry, you'll need to reassign your past activity to a new 
	public boolean delete_subcategory(String to_del, String to_move) { 
		Subcategory ToDel = this._find_subcat(to_del);
		Subcategory ToMove = this._find_subcat(to_move);
		if (ToDel == null ) { //sc DNE
			System.out.println( to_del + " does not exist in budget.");
			return false;
		}
		if (ToMove == null ) { //to_move DNE
			System.out.println( to_move + " does not exist in budget.");
			return false;
		}
		else {
			ToDel.move(ToMove);
			//now, actually delete the category from budget
			Integer cat_idx = this._find_cat_idx_s(to_move);
			this.categories.get(cat_idx).delete_subcategory(to_del);
			return true;
		}		
	}
	
	public Category getCategory(String name) {
		Category c = null;
		for (Category category : this.categories) {
			if (category.getName() == name) {
				c = category;
			}
		}
		return c;
	}
	
	public Subcategory getSubcategory(String parent, String child) {
		Subcategory sc = null;
		for (Category category : this.categories) {
			if (parent.equals(category.getName())) {
				for (Subcategory subcategory : category.subcategories) {
					if (child.equals(subcategory.getName())) {
						sc = subcategory;
					}
				}
			}
		}
		return sc;
	}
	
	
	public Integer _find_cat_idx_s(String subcat) { //finds the index of the CATEGORY that holds subcat
		Integer subcat_idx = 0; 
		Integer catidx = 0;
		for (Category c: this.categories) {
			subcat_idx = c._find_subcategory_idx(subcat);
			if (subcat_idx.equals(-1)) { 
				catidx += 1;
				continue;
			}
			return catidx;
		}
		if (subcat_idx.equals( -1)) { //subcategory was not found in any of the categories 
			System.out.println("no matching categories found. check spelling or create a new subcategory" + subcat);
		}
		return catidx;
	}
	
	public Payee getPayee(String payeeName) {
		Payee p = null;
		for (Payee payee : this.payeeList) {
			if (payeeName.equals(payee.getName())) {
				p = payee;
			}
		}
		return p;
	}

	public void add_transaction(String subcat, double amount) {
		Transaction T = new Transaction(amount);
		int idx = 0;
		for (Category c: this.categories) {
			idx = c._find_subcategory_idx(subcat);
				if (idx == -1) { return;}
			else {
				
				c.getSubcategories().get(idx).addTransaction(T);
				//update subcategory fields
				c.getSubcategories().get(idx).out_a += amount;
				c.getSubcategories().get(idx).out_m += amount;
				break;
			}
		}
		this.net_a -= amount;
	}
	
	private  int _find_category_idx_c(String cat_name) {
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
			boolean is_past_hour = _is_before(1);
			boolean is1st = _is1st();
			if (is_past_hour & is1st ) {
				reset = true;
			}
			return reset;
		}
	}
	
	public void reset() {
		boolean reset = is_reset_time();
		if (reset) {
			for (Category c: this.categories) {
				c.reset();
			}
		}
	}
	public void set_monthly_in(Double monthly_income) {
		this.in_m = monthly_income;
		this.net_a += monthly_income;
		this.in_unassigned += monthly_income;
	}
	
	public void print() {
		System.out.println("Monthly Income" + "\t\t\t "+ "Unassigned Money\t");
		System.out.println( "   " + this.in_m.toString() + "  " + "\t\t\t\t\t" + this.in_unassigned.toString());
		for (Category c: this.categories) {
			c.print();
		}
	}
	public static void save_data(Budget B) {
		FileOutputStream fileOut = null;
		ObjectOutputStream objOut = null;
		
		try {
			fileOut = new FileOutputStream("Budget_"+B.getName()+".ser");
			objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(B);
			objOut.close();
			fileOut.close();
		}
		catch(IOException i ){
			i.printStackTrace();
		}
	}
	public static Budget load_data(String budgetUser) {
		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;
		Budget B = null;
		try {
			fileIn = new FileInputStream("Budget_"+budgetUser+".ser");
			objIn = new ObjectInputStream(fileIn);
			B = (Budget)objIn.readObject();
			objIn.close();
			fileIn.close();
		}
		catch(IOException i ){
			i.printStackTrace();
		}
		catch(ClassNotFoundException c ){ //check if class exists
			c.printStackTrace();
		}
		return B;
	}
	
	public void printCategoryInfo() {
		for (Category c : this.categories) {
			System.out.println("Category: "+c.getName());
			for (Subcategory sc : c.subcategories) {
				System.out.println("  "+"Subcategory: "+sc.getName());
				for (Transaction t : sc.getTransactionList()) {
					System.out.println("    "+t.getDate()+"  "+t.getPayee().getName()+"  {"+t.getDescription()+"}  $"+t.getAmount());
				}
			}
			System.out.println("----------------------------------------------------------------");
		}
	}
	
	
}//end class def 


