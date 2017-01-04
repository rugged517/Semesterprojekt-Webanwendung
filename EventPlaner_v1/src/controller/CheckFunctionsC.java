package controller;

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
	 * @author Florian if input is int returns true, else returns false
	 * @param str
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

}