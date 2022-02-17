package Assignmets;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Assignment5 {
	
	public static int getDayNumber(LocalDate date) {
	    DayOfWeek day = date.getDayOfWeek();
	    return day.getValue();
	}
	
	public static String checkWeekendOrWeekday(int day) {
				String dayOfWeek;
				switch(day) {
				case 1,2,3,4,5 -> dayOfWeek = "Weekday";
				case 6,7 -> dayOfWeek = "Weekend";
				default -> dayOfWeek = "";
				};
				return dayOfWeek;
	}

	public static void main(String[] args) {
		LocalDate current = LocalDate.now();
//		System.out.println(current);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
		System.out.println(current.format(format));
		
		int dayNumber = getDayNumber(current);		
		if(checkWeekendOrWeekday(dayNumber).equals("Weekday"))
			System.out.println("I have no time. I am studying Programming Language!");
		else
			System.out.println("Today is my holiday");
		
		
	}

}
