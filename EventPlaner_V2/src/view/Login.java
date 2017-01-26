package view;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.LoginC;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Checks if SessionID is correct and validates login data.
	 * 
	 * @author Florian
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String task = request.getParameter("task");
		List<String> parameter = new ArrayList<String>();
		List<String> values = new ArrayList<String>();

		if (task.equals("checkCookie")) {

			Cookie[] cookies = request.getCookies();
			Cookie cookie = null;
			String userID = "";
			String sessionID = "";

			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					cookie = cookies[i];
					if (cookie.getName().equals("userID")) {
						userID = cookie.getValue();
					} else if (cookie.getName().equals("sessionID")) {
						sessionID = cookie.getValue();
					}
				}
			}

			String status = LoginC.checkSessionID(userID, sessionID);
			if (status.equals("false")) {
				for (int i = 0; i < cookies.length; i++) {
					cookie = cookies[i];
					cookie.setMaxAge(0);
				}
			}

			parameter.add("status");
			values.add(status);

		} else {
			String eMail = request.getParameter("eMail");
			String password = request.getParameter("password");

			String status = LoginC.tryLogin(eMail, password);
			parameter.add("login");
			values.add(status);

			if (status.equals("true")) {
				Cookie userID = new Cookie("userID", eMail);
				Cookie sessionID = new Cookie("sessionID", LoginC.getSessionID(eMail));
				response.addCookie(userID);
				response.addCookie(sessionID);
			}
		}

		XMLFunctions.sendResponse(response, parameter, values);
	}

}