package Day5;

import java.time.LocalDate;

public class LocalDate_test {

	public static void main(String[] args) {
		LocalDate date = LocalDate.now();
		LocalDate date1 = LocalDate.of(2016, 10, 31);
		LocalDate date2 = LocalDate.parse("2016-10-31");

		System.out.println("Current: " + date);
		LocalDate yesterday = date.minusDays(1);
		System.out.println("Yesterday: " + yesterday);
		System.out.println("Tomorrow: " + date.plusDays(1));
		
		System.out.println("Year: " + date.getYear());
		System.out.println("Month: " + date.getMonthValue() );
	} 

}
