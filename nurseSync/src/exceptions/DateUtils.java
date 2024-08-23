package exceptions;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {

	/**
	 * Parses a date string in the format yyyy-MM-dd and returns a java.sql.Date
	 * object.
	 * 
	 * @param dateString the date string to parse
	 * @return the parsed java.sql.Date object
	 * @throws ParseException if the date string is not in the expected format
	 */

	public static Date parseSqlDate(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate = sdf.parse(dateString);
		return new Date(utilDate.getTime());
	}
}
