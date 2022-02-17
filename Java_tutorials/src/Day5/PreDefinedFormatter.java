package Day5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PreDefinedFormatter {

	public static void main(String[] args) {
		DateTimeFormatter f1 = DateTimeFormatter.ISO_LOCAL_DATE;
		DateTimeFormatter f2 = DateTimeFormatter.ISO_LOCAL_DATE;
		DateTimeFormatter f3 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		
		LocalDateTime dateTime = LocalDateTime.now();
		
		System.out.println("Dafault: " + date);
		System.out.println("LOCAL_DATE: " + date.format(f1));
		

	}

}
