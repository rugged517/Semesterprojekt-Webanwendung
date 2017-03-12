package controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import model.CheckboxField;
import model.DateField;
import model.Event;
import model.SelectField;
import model.TextField;

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
	public static Boolean checkTextfield(String question, Boolean isRequired, String min, String max) {

		if (!question.equals("") && CheckFunctionsC.checkInt(min) && CheckFunctionsC.checkInt(max)) {

			if (isRequired) {
				return (min != "0");
			}
			return true;
		}
		return false;
	}

	/**
	 * Checks if all required values for additional datefields are correct and
	 * midDate < maxDate
	 * 
	 * @param min
	 *            - 2016
	 * @param min
	 *            - 2017
	 * 
	 * @return true if all values are correct
	 * 
	 * @author Florian
	 */
	public static Boolean checkDatefield(String question, String min, String max) {

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
	public static Boolean checkOptions(String question, String[] values) {
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

	/**
	 * Checks if all required values for an event are okay.
	 * 
	 * @return true if all values are correct
	 * 
	 * @author Florian
	 */
	public static boolean checkEvent(Hashtable<String, String> eventValues, String[] eventParameter) {
		List<String> requiredParam = Arrays.asList("title", "street", "postcode", "city", "start", "text");
		try {
			System.out.println("try");
			// check length of each value
			for (int i = 0; i < eventParameter.length; i++) {
				String value = eventValues.get(eventParameter[i]);
				if (value != null) {
					// i=12 -> text for event
					if (value.length() > 100 || i == 12 && value.length() > 2000) {
						return false;
					}
				} else {
					value = "";
					eventValues.put(eventParameter[i], "");
				}

				if (requiredParam.contains(eventParameter[i]) && value.equals("")) {
					// false if required Value is null
					return false;
				}
			}
			if (!isEMail(eventValues.get("eMail"))) {
				return false;
			}
			System.out.println("nr");
			String[] numbers = { "phonenumber", "postcode", "min", "max" };
			// check if values are numbers
			for (int i = 0; i < numbers.length; i++) {
				String value = eventValues.get(numbers[i]);
				// if not "" and not a number
				if (!value.equals("") && !CheckFunctionsC.checkInt(value)) {
					return false;
				}
			}

			// check if min < max
			int min = Integer.parseInt(eventValues.get("min"));
			int max = Integer.parseInt(eventValues.get("max"));
			if (min > max && max != 0) {
				return false;
			}
			System.out.println("cd");
			// check date values with CheckDatefield function
			String startDate = eventValues.get("start");
			String endDate = eventValues.get("end");
			String deadDate = eventValues.get("dead");

			boolean result = true;
			
			if (CheckFunctionsC.getDate(startDate) != null) {
				System.out.println("null");
				// check Date and if date = 1970-01-01T00:00:00.000Z replace with startDate
				if (!endDate.equals("1970-01-01T00:00:00.000Z")) {
					result = checkDatefield("-", startDate, endDate);
				}else{
					eventValues.put("end", startDate);
				}
				if (!deadDate.equals("1970-01-01T00:00:00.000Z") && result) {
					result = checkDatefield("-", deadDate, startDate);
				}else{
					eventValues.put("dead", startDate);
				}

				return result;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Save event to DB
	 * 
	 * @return event link
	 * 
	 * @author Florian
	 */
	public static String createEvent(String userID, Hashtable<String, String> eventValues) {
		Event event = new Event(userID, eventValues);
		return event.getLink();
	}

	/**
	 * Save Textfield in DB
	 * 
	 * @author Florian
	 */
	public static void saveTextfield(String link, String question, Boolean isRequired, boolean isTextarea, String min, String max) {
		new TextField(link, question, isRequired, isTextarea, Integer.parseInt(min), Integer.parseInt(max));		
	}

	/**
	 * Save Datefield in DB
	 * 
	 * @author Florian
	 */
	public static void saveDatefield(String link, String question, Boolean isRequired, String minDate,
			String maxDate) {		
		new DateField(link, question, isRequired, minDate, maxDate);		
	}

	/**
	 * Save Selectfield in DB
	 * 
	 * @author Florian
	 */
	public static void saveSelectfield(String link, String question, Boolean isRequired,
			String[] options) {
		
		new SelectField(link, question, isRequired, options);	
		
	}

	/**
	 * Save Checkboxfield in DB
	 * 
	 * @author Florian
	 */
	public static void saveCheckboxfield(String link, String question, Boolean isRequired,
			String[] options, String min, String max) {
		
		new CheckboxField(link, question, isRequired, options, Integer.parseInt(min), Integer.parseInt(max));
	}

	
	public static Hashtable<String, String> getEventData(String link) {
		Event event= new Event(link);		
		return event.getEventData();		
	}


}