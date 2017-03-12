package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.RegistrationC;


@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String eMail;
	private String password;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		eMail = request.getParameter("eMail");
		password = request.getParameter("password");

		// try to create new user and get status
		String[] status = RegistrationC.createNewUser(eMail, password);
		
		if (status[0].equals("true")) {
			XMLFunctions.sendShortResponse(response, "code", status[1]);
		} else {
			XMLFunctions.sendShortResponse(response, "error", status[1]);
		}

	}

}