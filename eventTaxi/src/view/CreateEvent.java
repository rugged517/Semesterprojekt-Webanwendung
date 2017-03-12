package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CreateEventC;
import controller.UserStatusC;

@WebServlet("/CreateEvent")
public class CreateEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Checks user input (E-Mails, Dates etc.) and creates a html preview of an
	 * event registration
	 * 
	 * @author Florian
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// check if user has rights to create an event
		String userID = CookieFunctions.getCookieData(request.getCookies(), "userID");
		if (Login.checkSession(request, response) && UserStatusC.checkOrganizerRights(userID)) {
			
			String task = request.getParameter("task");


			// checks if input is correct eMail
			if (task.equals("checkEMails")) {
				String[] ids = request.getParameterValues("id");
				String[] eMails = request.getParameterValues("eMail");

				List<String> parameter = new ArrayList<String>();
				List<String> values = new ArrayList<String>();
				
				for (int i = 0; i < eMails.length; i++) {

					if (!CreateEventC.isEMail(eMails[i])) {
						parameter.add("error");
						values.add(ids[i]);
					}
				}
				XMLFunctions.sendResponse(response, parameter, values);

				// checks and creates additional html fields for event
				// registration
			} else if (task.equals("buildAddedField")) {

				if (checkAdditionalField(request)) {

					response.setContentType("text/html");
					PrintWriter writer = null;
					try {
						writer = response.getWriter();
					} catch (IOException e) {
						e.printStackTrace();
						return;
					}
					String type = request.getParameter("type");
					String id = request.getParameter("id");
					String question = request.getParameter("question");
					Boolean isRequired = request.getParameter("isRequired").equals("true");

					if (type.equals("text")) {
						writer.append(SignUpEvent.createTextfield(id, question, isRequired,
								request.getParameter("isTextarea").equals("true"), request.getParameter("min"), request.getParameter("max")));
					} else if (type.equals("date")) {
						writer.append(SignUpEvent.createDatefield(id, question, isRequired,
								request.getParameter("minDate"), request.getParameter("maxDate")));
					} else if (type.equals("select")) {
						writer.append(SignUpEvent.createSelectfield(id, question, isRequired,
								request.getParameterValues("options[]")));
					} else {
						writer.append(SignUpEvent.createCheckboxfield(id, question, isRequired,
								request.getParameterValues("options[]"), request.getParameter("min"),
								request.getParameter("max")));
					}

				} else {
					XMLFunctions.sendShortResponse(response, "error", request.getParameter("id"));
				}
			} else if (task.equals("saveAddedField")) {

				if (checkAdditionalField(request)) {
					String link = request.getParameter("link");
					String type = request.getParameter("type");
					String question = request.getParameter("question");
					Boolean isRequired = request.getParameter("isRequired").equals("true");

					if (type.equals("text")) {
						CreateEventC.saveTextfield(link, question, isRequired,
								request.getParameter("isTextarea").equals("true"), request.getParameter("min"),
								request.getParameter("max"));
					} else if (type.equals("date")) {
						CreateEventC.saveDatefield(link, question, isRequired,
								request.getParameter("minDate"), request.getParameter("maxDate"));
					} else if (type.equals("select")) {
						CreateEventC.saveSelectfield(link, question, isRequired,
								request.getParameterValues("options[]"));
					} else {
						CreateEventC.saveCheckboxfield(link, question, isRequired,
								request.getParameterValues("options[]"), request.getParameter("min"),
								request.getParameter("max"));
					}
					
				} else {
					XMLFunctions.sendShortResponse(response, "error", request.getParameter("id"));
				}
			} else if (task.equals("saveEvent") || task.equals("buildEvent")) {
				Hashtable<String, String> eventValues = new Hashtable<String, String>();
				String[] eventParameter = { "title", "surname", "name", "eMail", "phonenumber", "company", "street",
						"postcode", "city", "min", "max", "start", "end", "dead", "text"};
				// get all event parameter from response
				for (int i = 0; i < eventParameter.length; i++) {
					eventValues.put(eventParameter[i], request.getParameter(eventParameter[i]));
				}
				if(CreateEventC.checkEvent(eventValues, eventParameter)){
					if(task.equals("saveEvent")){
						//save event and send event link as response
						XMLFunctions.sendShortResponse(response, "status", CreateEventC.createEvent(userID, eventValues));
					}else {
						// TODO if task.equals("buildEvent") use ShowEvent functions to build a test page
						// TODO send html page as response
						XMLFunctions.sendShortResponse(response, "status", "ok");
					}				
					
				}else{
					XMLFunctions.sendShortResponse(response, "error", "false");
				}
			} else if (task.equals("getEventData")) {
				if(UserStatusC.checkEditEvent(request.getParameter("evlink"),CookieFunctions.getCookieData(request.getCookies(), "userID"))){
					
					Hashtable<String, String> eventValues=CreateEventC.getEventData(request.getParameter("evlink"));
					
					List<String> parameter = new ArrayList<String>();
					List<String> values = new ArrayList<String>();
					
					
					Enumeration<String> e = eventValues.keys();
				    while (e.hasMoreElements()) {			    	
				    	String key = (String) e.nextElement();			    	
				    	parameter.add(key);
						values.add(eventValues.get(key));
				    }
				    XMLFunctions.sendResponse(response, parameter, values);
				}
				
				
			}
		}
	}

	/**
	 * Checks user created input fields and returns true if all settings are
	 * correct.
	 * 
	 * @return true if field settings are okay
	 * 
	 * @author Florian
	 */
	private Boolean checkAdditionalField(HttpServletRequest request) {
		String type = request.getParameter("type");
		String question = request.getParameter("question");
		Boolean isRequired = request.getParameter("isRequired").equals("true");

		Boolean isCorrect = false;

		if (type.equals("text")) {
			isCorrect = CreateEventC.checkTextfield(question, isRequired, request.getParameter("min"),
					request.getParameter("max"));
		} else if (type.equals("date")) {
			isCorrect = CreateEventC.checkDatefield(question, request.getParameter("minDate"),
					request.getParameter("maxDate"));
		} else {
			isCorrect = CreateEventC.checkOptions(question, request.getParameterValues("options[]"));
			if (type.equals("checkbox") && isCorrect) {
				isCorrect = CreateEventC.checkTextfield(question, isRequired, request.getParameter("min"),
						request.getParameter("max"));
			}
		}
		return isCorrect;
	}

}