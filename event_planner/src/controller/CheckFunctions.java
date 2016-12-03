package controller;

import org.apache.commons.validator.routines.EmailValidator;

public class CheckFunctions {

	/**
	 * @author Florian
	 * @param eMail
	 */
	public static boolean checkEMail(String eMail) {
		EmailValidator validator = EmailValidator.getInstance();
		if (validator.isValid(eMail)) {
			return true;
		}
		return false;
	}

	/**
	 * @author Florian
	 * if input is strong password return true
	 * @param password
	 */
	public static boolean checkPassword(String password) {
		// TODO - implement CheckFunctions.checkPassword
		throw new UnsupportedOperationException();
	}

	/**
	 * @author Florian
	 * if input is int returns true, else returns false
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