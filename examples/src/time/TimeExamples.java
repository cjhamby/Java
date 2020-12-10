/* too many ways to say today */

package time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeExamples {

	public static void main(String[] args) {
		
		/* what is today */
		Date today = new Date();
		System.out.println("new Date()\t\t" + today);
		
		/* date doesn't have to be today */
		long ms = 1943143141;
		Date someDay = new Date(ms);
		System.out.println("new Date(ms)\t\t" + someDay);
		System.out.println();
		
		/* time zone here */
		LocalDate todayLocalDate = LocalDate.now();
		LocalTime todayLocalTime = LocalTime.now();
		LocalDateTime todayLocal = LocalDateTime.now();
		System.out.println("Local Time\t\t" + todayLocalTime);
		System.out.println("Local Date\t\t" + todayLocalDate);
		System.out.println("LOCAL EVERYTHING\t" + todayLocal);
		System.out.println();
		
		/* time zone elsewhere */
		LocalDate todayEuropeDate = LocalDate.now(ZoneId.of("Europe/Paris"));
		LocalTime todayEuropeTime = LocalTime.now(ZoneId.of("Europe/Paris"));
		LocalDateTime todayEurope = LocalDateTime.now(ZoneId.of("Europe/Paris"));
		System.out.println("Europe/Paris time\t" + todayEuropeTime);
		System.out.println("Europe/Paris date\t" + todayEuropeDate);
		System.out.println("EUROPE EVERYTHING\t" + todayEurope);
		System.out.println();
		
		/* formatting can be cleaner */
		SimpleDateFormat neatDate = new SimpleDateFormat();
		System.out.println("Simple Date Format\t" + neatDate.format(today));
		System.out.println();
		
		/* Calendar class does the formatting 
		 * GregorianCalendar is the calendar format */
		Calendar cal = new GregorianCalendar();
		System.out.println("Calendar\t\t" + cal.get(Calendar.MONTH) +  " " + cal.get(Calendar.YEAR) );
		System.out.println();
		
		
		/* Time Periods */
		LocalDate oldDate = LocalDate.of(1996, 4, 10);
		Period period = Period.between(oldDate, todayLocalDate);
		System.out.println("Time Alive\t\t" + period.getYears() + " Years, " 
				+ period.getMonths() + " Months, " 
				+ period.getDays() + " Days" );
		
	}

}
