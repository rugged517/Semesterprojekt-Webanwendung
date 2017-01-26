package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CreateEventC;

@WebServlet("/CreateEvent")
public class CreateEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/**
	 * Checks user input (E-Mails, Dates etc.) and creates a html preview for an event registration
	 * 
	 * @author Florian
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String task = request.getParameter("task");
		List<String> parameter = new ArrayList<String>();
		List<String> values = new ArrayList<String>();

		//checks if input is correct eMail
		if (task.equals("checkEMails")) {
			String[] ids = request.getParameterValues("id");
			String[] eMails = request.getParameterValues("eMail");

			for (int i = 0; i < eMails.length; i++) {

				if (!CreateEventC.isEMail(eMails[i])) {
					parameter.add("error");
					values.add(ids[i]);
				}

			}
			XMLFunctions.sendResponse(response, parameter, values);
		
			//checks and creates additional html fields for event registration
		} else if (task.equals("buildAddedField")) {
			
			if (!checkAdditionalField(request)) {
			
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
				writer.append(SubscribeToEvent.createTextfield(id, question, isRequired, request.getParameter("isTextarea").equals("true")));
			} else if (type.equals("date")) {
				writer.append(SubscribeToEvent.createDatefield(id, question, isRequired, request.getParameter("minDate"), request.getParameter("maxDate")));
			} else if (type.equals("select")){
				writer.append(SubscribeToEvent.createSelectfield(id, question, isRequired, request.getParameterValues("options[]")));
			}else{
				writer.append(SubscribeToEvent.createCheckboxfield(id, question, isRequired, request.getParameterValues("options[]"),request.getParameter("min"),request.getParameter("max")));
			}
			
			}else{
				parameter.add("error");
				values.add(request.getParameter("id"));
				XMLFunctions.sendResponse(response, parameter, values);
			}
		}
	}
	
	/**
	 * Checks user created input fields and returns true if all settings are correct.
	 * 
	 * @author Florian
	 */
	private Boolean checkAdditionalField(HttpServletRequest request){
		String type = request.getParameter("type");
		String question = request.getParameter("question");
		Boolean isRequired = request.getParameter("isRequired").equals("true");

		Boolean hasError = false;

		if (type.equals("text")) {
			hasError = !CreateEventC.CheckTextfield(question, isRequired, request.getParameter("min"),
					request.getParameter("max"));
		} else if (type.equals("date")) {
			hasError = !CreateEventC.CheckDatefield(question, request.getParameter("minDate"),
					request.getParameter("maxDate"));
		} else {
			hasError = !CreateEventC.CheckOptions(question, request.getParameterValues("options[]"));
			if (type.equals("checkbox") && !hasError) {
				hasError = !CreateEventC.CheckTextfield(question, isRequired, request.getParameter("min"),
						request.getParameter("max"));
			}
		}
		return hasError;
	}

}