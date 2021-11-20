import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Driver3 {
	public static void main(String[] args) {
	GoalByDate g = new GoalByDate(12, 18, 2021);
	g.set_amt(100);
	double montlhy_amt = g.get_amt_m();
	System.out.printf("you need to assign : $%.2f to reach your goal of %.2f" ,  montlhy_amt, g.get_amount());
	}
}
