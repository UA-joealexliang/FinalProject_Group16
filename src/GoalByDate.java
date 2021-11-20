import java.util.Date;
import java.util.GregorianCalendar;
import java.time.LocalDate;
import java.util.Calendar;

public class  GoalByDate extends Goal {
	private Date goalDate;
	private double amt_m; //the monthly amount to be allocated in order to reach the goal by the given date
	
	GoalByDate(Integer month, Integer day, Integer year){ 
		Calendar calendar = new GregorianCalendar(year, month-1, day);
		/*
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		*/
		this.goalDate = calendar.getTime();
	}
	
	private int _days_between() { //return number of days between current date and goaldate
		Date today = GregorianCalendar.getInstance().getTime();
		Integer difference =  (int) ((goalDate.getTime()-today.getTime())/86400000);
        return  Math.abs(difference)+1;
	}
	private double calc_amt_m() { //calls _days_between() to find the monthly amount needed to reach the goal by the given date
		int days = _days_between() ;
		return this.amount / days;
	}
	public double get_amt_m() {
		return this.amt_m;
	}
	public void set_amt(double amount) {
		this.amount = amount;
		this.amt_m = calc_amt_m();
	}
}
