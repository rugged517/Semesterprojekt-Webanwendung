package controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import model.Application;
import model.CheckboxField;
import model.DateField;
import model.Event;
import model.GetAll;
import model.SelectField;
import model.TextField;

public class SignUpEventC {

	public static List<Hashtable<String, String>> getAdditionalFieldValues(String eventLink) {

		List<Hashtable<String, String>> allFields = new ArrayList<Hashtable<String, String>>();

		List<Object> fields = GetAll.getAllFields(eventLink, "text");
System.out.println(fields.size()+"t");
		
		for (int i = 0; i < fields.size(); i++) {
			Hashtable<String, String> values = new Hashtable<String, String>();
			TextField infoObject = (TextField) fields.get(i);

			values.put("id", String.valueOf(infoObject.getId()));
			values.put("type", "text");
			values.put("question", infoObject.getDescription());
			values.put("isRequired", String.valueOf(infoObject.isRequired()));

			values.put("isTextarea", String.valueOf(infoObject.isTextarea()));
			values.put("min", String.valueOf(infoObject.getMin()));
			values.put("max", String.valueOf(infoObject.getMax()));

			allFields.add(values);
		}

		fields = GetAll.getAllFields(eventLink, "select");
		System.out.println(fields.size()+"s");
		for (int i = 0; i < fields.size(); i++) {
			Hashtable<String, String> values = new Hashtable<String, String>();
			SelectField infoObject = (SelectField) fields.get(i);

			values.put("id", String.valueOf(infoObject.getId()));
			values.put("type", "select");
			values.put("question", infoObject.getDescription());
			values.put("isRequired", String.valueOf(infoObject.isRequired()));

			String[] options = infoObject.getValues();

			for (int in = 0; in < options.length; in++) {
				values.put("option" + in, options[in]);
			}
			allFields.add(values);
		}

		fields = GetAll.getAllFields(eventLink, "checkbox");
		System.out.println(fields.size()+"c");
		for (int i = 0; i < fields.size(); i++) {
			Hashtable<String, String> values = new Hashtable<String, String>();
			CheckboxField infoObject = (CheckboxField) fields.get(i);

			values.put("id", String.valueOf(infoObject.getId()));
			values.put("type", "checkbox");
			values.put("question", infoObject.getDescription());
			values.put("isRequired", String.valueOf(infoObject.isRequired()));

			values.put("min", String.valueOf(infoObject.getMin()));
			values.put("max", String.valueOf(infoObject.getMax()));

			String[] options = infoObject.getValues();

			for (int in = 0; in < options.length; in++) {
				values.put("option" + in, options[in]);
			}
			allFields.add(values);
		}

		fields = GetAll.getAllFields(eventLink, "date");
		System.out.println(fields.size()+"d");
		for (int i = 0; i < fields.size(); i++) {
			Hashtable<String, String> values = new Hashtable<String, String>();
			DateField infoObject = (DateField) fields.get(i);

			values.put("id", String.valueOf(infoObject.getId()));
			values.put("type", "date");
			values.put("question", infoObject.getDescription());
			values.put("isRequired", String.valueOf(infoObject.isRequired()));

			values.put("minDate", String.valueOf(infoObject.getMinDate()));
			values.put("maxDate", String.valueOf(infoObject.getMaxDate()));

			allFields.add(values);
		}
		System.out.println(allFields.size()+"all");
		return allFields;
	}

	public static boolean canSignUp(String eventLink) {

		Event event = new Event(eventLink);
		// check if max = applications
		int max = event.getMax();
		if (max != 0) {
			List<Application> applications = GetAll.getAllApplications(eventLink, null);

			if (applications.size() >= max) {
				return false;
			}
		}
		return true;

	}

	public static boolean canEdit(String event, String userID) {
		
		
		return true;
	}

}