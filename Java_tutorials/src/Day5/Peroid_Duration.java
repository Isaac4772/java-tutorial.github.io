package Day5;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class Peroid_Duration {

	public static void main(String[] args) {
		LocalDate startDate = LocalDate.parse("2019-09-25");
		LocalDate endDate = LocalDate.parse("2021-10-21");
		
		Period diff = Period.between(startDate, endDate);
		
		System.out.println("Year: " + diff.getYears());
		System.out.println("Month: " + diff.getMonths());
		System.out.println("Days: " + diff.getDays());
		
		LocalTime startTime = LocalTime.parse("11:30");
		LocalTime endTime = LocalTime.parse("12:00");
		
		Duration timeDiff = Duration.between(startTime, endTime);
		
	}

}
