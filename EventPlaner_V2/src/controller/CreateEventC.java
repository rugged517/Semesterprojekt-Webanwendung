package controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CreateEventC {

	/**
	 * Checks if input string is correct E-Mail
	 * 
	 * @author Florian
	 * @return true if input is correct
	 */
	public static boolean isEMail(String eMail) {
		if (CheckFunctionsC.checkEMail(eMail)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if all required values for additional textfields are correct.
	 * 
	 * @author Florian
	 * @return true if all values are correct
	 */
	public static Boolean CheckTextfield(String question, Boolean isRequired, String min, String max) {

		if (!question.equals("") && CheckFunctionsC.checkInt(min) && CheckFunctionsC.checkInt(max)) {

			if (isRequired) {
				return (min != "0");
			}
			return true;
		}

		return false;

	}

	/**
	 * Checks if all required values for additional datefields are correct.
	 * 
	 * @author Florian
	 * @return true if all values are correct
	 */
	public static Boolean CheckDatefield(String question, String min, String max) {

		if (!question.equals("")) {

			if (!min.equals("") && !max.equals("")) {
				Date minDate = CheckFunctionsC.getDate(min);
				Date maxDate = CheckFunctionsC.getDate(max);

				if (minDate == null || maxDate == null || minDate.after(maxDate)) {
					return false;
				}

			} else if (!min.equals("")) {
				Date minDate = CheckFunctionsC.getDate(min);
				if (minDate == null) {
					return false;
				}
			} else if (!max.equals("")) {
				Date maxDate = CheckFunctionsC.getDate(max);
				if (maxDate == null) {
					return false;
				}
			}

			return true;
		}

		return false;

	}

	/**
	 * Checks if all select/checkbox values for additional datefields are
	 * correct and unique
	 * 
	 * @author Florian
	 * @return true if all values are correct
	 */
	public static Boolean CheckOptions(String question, String[] values) {
		if (!question.equals("")) {
			Set<String> set = new HashSet<String>();

			for (String value : values) {
				if (value.isEmpty() || value == null || value.equals("") || set.add(value) == false) {
					return false;
				}
			}

			return true;
		}
		return false;
	}

}