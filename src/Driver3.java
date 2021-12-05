import java.text.SimpleDateFormat;  

public class Driver3 {
	public static void main(String[] args) {
	GoalByDate g = new GoalByDate(100.00, 12, 18, 2021);
	Double montlhy_amt = g.get_amt_m();
	//System.out.printf("you need to assign : $%.2f to reach your goal of %.2f "
	//		+ " by " + g.get_date().toString(),  montlhy_amt, g.get_amount());
	String s = g.get_message();
	System.out.println(s);
	
	}
	
}
