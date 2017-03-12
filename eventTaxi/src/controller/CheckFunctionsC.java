package controller;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckFunctionsC {

	/**
	 * @author Florian
	 * @param eMail
	 */
	public static boolean checkEMail(String eMail) {
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(eMail);
		return matcher.matches();

	}

	/**
	 * @author Florian 
	 * @return if input is int returns true, else returns false
	 */
	public static boolean checkInt(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

	/**
	 * @author Florian
	 * @return if input is Date returns date, else returns null
	 */
	public static Date getDate(String str) {	
		System.out.println("cd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
		try{
	    TemporalAccessor accessor = timeFormatter.parse(str);
	    System.out.println(Date.from(Instant.from(accessor)).toString());
	    return Date.from(Instant.from(accessor));
		}catch (DateTimeParseException e){
			return null;
		}
	}
}