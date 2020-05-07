package coupon.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import coupon.exeption.ApplicationException;


public class DateUtils {

	public static String getCurrentDateAndTime() {
		// Creating a format for the date
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
		// By calling the date constructor, we get the current date
		Date today = new Date();
		// formatting the date to string
		String currentDateAndTime = dateFormat.format(today);

		return currentDateAndTime;
	}

	public static String getCurrentDate() {

		// creating a matching format for the date as it appears on the Database
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
		// By calling the date constructor, we get the current date
		Date today = new Date();
		// formatting the date to string
		String currentDate = dateFormat.format(today);

		return currentDate;
	}

	public static boolean isDate1BeforeDate2(String date1, String date2) throws ApplicationException, Exception {

		// creating a matching format for the date as it appears on the Database
		DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");

		// formatting the string to date
		Date myDate1ForSql = formatter.parse(date1);
		java.sql.Date sqlDate1 = new java.sql.Date(myDate1ForSql.getTime());

		// formatting the string to date
		Date myDate2ForSql = formatter.parse(date2);
		java.sql.Date sqlDate2 = new java.sql.Date(myDate2ForSql.getTime());

		// We check if date1 is before date2
		if (sqlDate1.before(sqlDate2)) {
			return true;
		}
		return false;
	}
}
