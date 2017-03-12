package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.SignUpEventC;

@WebServlet("/SignUpEvent")
public class SignUpEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// check if user is logged in
		String userID = CookieFunctions.getCookieData(request.getCookies(), "userID");
		if (Login.checkSession(request, response)) {

			String task = request.getParameter("task");
			if (task.equals("buildAddedField")) {
				String event=request.getParameter("event");
				String status=request.getParameter("status");
	
				// if user wants to sing up but event is full send error
				if(status.equals("create")){
					if(!SignUpEventC.canSignUp(event)){						
						XMLFunctions.sendShortResponse(response, "error", "full");
						return;
					}	
				}else if(status.equals("edit")){
					if(!SignUpEventC.canEdit(event, userID)){						
						XMLFunctions.sendShortResponse(response, "error", "noApplication");
						return;
					}	
				}

				response.setContentType("text/html");
				PrintWriter writer = null;
				try {
					writer = response.getWriter();
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}

				List<Hashtable<String, String>> options = SignUpEventC.getAdditionalFieldValues(event);

				for (int i = 0; i < options.size(); i++) {
					writer.append(buildField(options.get(i)));
				}

			}
		}

	}

	public String buildField(Hashtable<String, String> fieldValues) {

		String type = fieldValues.get("type");
		String id = fieldValues.get("id");
		String question = fieldValues.get("question");
		Boolean isRequired = fieldValues.get("isRequired").equals("true");
		
		if (type.equals("text")) {
			return SignUpEvent.createTextfield(id, question, isRequired,fieldValues.get("isTextarea").equals("true"), fieldValues.get("min"), fieldValues.get("max"));
		} else if (type.equals("date")) {
			return SignUpEvent.createDatefield(id, question, isRequired, fieldValues.get("minDate"),
					fieldValues.get("maxDate"));
		} else {

			// put all options in a List and then in a String[]
			List<String> options = new ArrayList<String>();
			int i = 0;
			while (fieldValues.containsKey("option" + i)) {
				options.add(fieldValues.get("option" + i));
				i++;
			}
			
			String[] allValues= new String[options.size()];
			for (int in = 0; in < options.size(); in++) {
				allValues[in]=options.get(in);
			}
			
			if (type.equals("select")) {
				return SignUpEvent.createSelectfield(id, question, isRequired, allValues);
			} else {
				return SignUpEvent.createCheckboxfield(id, question, isRequired, allValues,
						fieldValues.get("min"), fieldValues.get("max"));
			}
		}
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

	public static String createTextfield(String id, String question, Boolean isRequired, boolean isTextarea, String min, String max) {
		String content;
		if (isTextarea) {
			content = "<textarea rows=\"5\" cols=\"50\" id=\"aQ-" + id + "\" name=\"aQ-text-" + id + "\" "
					+ getRequiredString(isRequired) + "></textarea>";
		} else {
			if(min=="0"){
				min="";
			}
			if(max=="0"){
				max="";
			}
			content = "<input min=\""+min+"\" max=\""+max+"\" id=\"aQ-" + id + "\" type=\"text\"placeholder=\"" + question + "\" class=\"form-control\""
					+ getRequiredString(isRequired) + ">";
		}
		return createField(question, id, content);
	}

	public static String createDatefield(String id, String question, Boolean isRequired, String minDate,
			String maxDate) {

		return "<span class=\"hideValues dateID\">"+id+"</span><span class=\"hideValues\" id=\"dateMin-"+id+"\">"+minDate+"</span><span class=\"hideValues\" id=\"dateMax-"+id+"\">"+maxDate+"</span><div class=\"form-group\" id=\"dateAdd-" + id + "\">" + "<div id=\"aQ-" + id
				+ "\" class=\"input-append date\">" + "<label for=\"in_aQ-" + id + "\" >" + question + "</label>"
				+ "<input class=\"date-field\" id=\"in_aQ-" + id + "\" " + getRequiredString(isRequired)
				+ " readonly type=\"text\">" + "<span class=\"add-on date-field\">"
				+ "<i data-time-icon=\"icon-time\" data-date-icon=\"icon-calendar\" class=\"icon-calendar\"></i>"
				+ "</span></div></div>";
	}

	public static String createSelectfield(String id, String question, Boolean isRequired, String[] values) {

		String content = "<select class=\"form-control input-append\" id=\"aQ-" + id + "\"" + getRequiredString(isRequired) + ">";

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
				content = content + "<label class=\"radio-inline\" for=\"aQ-" + id + value + "\"><input type=\"radio\" id=\"aQ-" + id + value + "\" name=\"aQ-" + id + "\" value=\""
						+ value + "\">" +  value + "</label>&nbsp;";
			}
			content = content + "</fieldset>";

		} else {
			content = content + "<span class=\"hideValues\" id=\"aQ-min-" + id + "\">" + min
					+ "</span><span class=\"hideValues\" id=\"aQ-max-" + id + "\">" + max + "</span>";
			for (String value : values) {
				content = content + "<div class=\"checkbox\"><label><input type=\"checkbox\" class=\"aQ-" + id
						+ "\" value=\"" + value + "\">" + value + "</label></div>";
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