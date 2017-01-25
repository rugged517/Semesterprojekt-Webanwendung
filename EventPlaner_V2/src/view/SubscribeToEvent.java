package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SubscribeToEvent")
public class SubscribeToEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public String[] getRequestedFields() {
		// TODO - implement subscribeToEvent.getRequestedFields
		throw new UnsupportedOperationException();
	}

	/**
	 * returns fields with errors. returns null if no errors and participant was
	 * added
	 * 
	 * @param fields
	 * @param values
	 */
	public String[] addParticipant(String[] fields, String[] values) {
		// TODO - implement subscribeToEvent.addParticipant
		throw new UnsupportedOperationException();
	}

	public static String createField(String question, String id, String content) {
		return "<div class=\"form-group\"><label for=\"aQ-" + id + "\">" + question + "</label>" + content + "</div>";

	}

	public static String getRequiredString(Boolean isRequired) {
		String required = "";
		if (isRequired) {
			required = " required";
		}
		return required;
	}

	public static String createTextfield(String id, String question, Boolean isRequired, boolean isTextarea) {
		String content;
		if(isTextarea){
			content ="<textarea rows=\"5\" cols=\"50\" id=\"aQ-" + id + "\" name=\"aQ-text-" + id + "\" " + getRequiredString(isRequired) + "></textarea>";
		}else{		
			content = "<input id=\"aQ-" + id + "\" type=\"text\"placeholder=\"" + question
				+ "\" class=\"form-control\"" + getRequiredString(isRequired) + ">";
		}
		return createField(question, id, content);

	}

	public static CharSequence createDatefield(String id, String question, Boolean isRequired, String minDate,
			String maxDate) {

		return "<div onload=\"activateDateField('" + minDate + "', '" + maxDate
				+ "')\" class=\"form-group\" id=\"dateAdd-" + id + "\">" + "<div id=\"aQ-" + id
				+ "\" class=\"input-append date\">" + "<label for=\"in_aQ-" + id + "\" >" + question + "</label>"
				+ "<input class=\"date-field\" id=\"in_aQ-" + id + "\" " + getRequiredString(isRequired)
				+ " readonly type=\"text\">" + "<span class=\"add-on date-field\">"
				+ "<i data-time-icon=\"icon-time\" data-date-icon=\"icon-calendar\" class=\"icon-calendar\"></i>"
				+ "</span></div></div>";

	}

	public static String createSelectfield(String id, String question, Boolean isRequired, String[] values) {

		String content = "<select class=\"form-control\" id=\"aQ-" + id + "\"" + getRequiredString(isRequired) + ">";

		if (!isRequired) {
			content = content + "<option></option>";
		}

		for (String value : values) {
			content = content + "<option>" + value + "</option>";
		}

		content = content + "</select>";
		return createField(question, id, content);

	}

	public static String createCheckboxfield(String id, String question, Boolean isRequired, String[] values,
			String min, String max) {
		String content = "<span class=\"hideValues\" id=\"aQ-required-" + id + "\">" + isRequired + "</span>";
		if (min.equals("1") && max.equals("1")) {

			content = content + "<fieldset>";

			for (String value : values) {
				content = content + "<input type=\"radio\" id=\"aQ-" + id + value + "\" name=\"aQ-" + id + "\" value=\""
						+ value + "\">" + "<label for=\"aQ-" + id + value + "\">&nbsp;" + value + "</label><br>";
			}
			content = content + "</fieldset>";

		} else {
			content = content + "<span class=\"hideValues\" id=\"aQ-min-" + id + "\">" + min + "</span><span class=\"hideValues\" id=\"aQ-max-" + id + "\">" + max
					+ "</span>";
			for (String value : values) {
				content = content + "<div class=\"checkbox\"><label><input type=\"checkbox\" class=\"aQ-" + id
						+ " value=\"" + value + "\">" + value + "</label></div>";
			}
			String minMaxQ = "";
			if (!min.equals("")) {
				minMaxQ = "(mindestens " + min;
			} else if (!max.equals("")) {
				if (!minMaxQ.equals("")) {
					minMaxQ = minMaxQ + "; ";
				} else {
					minMaxQ = minMaxQ + "(";
				}
				minMaxQ = minMaxQ + "maximal " + min;
			}

			if (!minMaxQ.equals("")) {
				minMaxQ = minMaxQ + ")";
			}
			question = question + "";
		}
		return createField(question, id, content);
	}

}