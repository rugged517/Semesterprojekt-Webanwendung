package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.LoginC;
import controller.UserStatusC;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String task = request.getParameter("task");
		if (task.equals("logout")) {
			logout(request, response);			
			response.sendRedirect(getServletContext().getContextPath()+"/login.html?note=logout");
		}	
	}

	/**
	 * 
	 * Checks if SessionID is correct and validates login data.
	 * 
	 * @author Florian
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String task = request.getParameter("task");
		String value = null;

		boolean cookieStatus=checkSession(request, response);
		
		if (task.equals("checkCookie")) {

			value = String.valueOf(cookieStatus);

		} else if (task.equals("checkOrganizer")&&cookieStatus) {

			value = String.valueOf(
					UserStatusC.checkOrganizerRights(CookieFunctions.getCookieData(request.getCookies(), "userID")));

		} else if (task.equals("checkAdmin")&&cookieStatus) {

			value = String.valueOf(
					UserStatusC.checkAdminRights(CookieFunctions.getCookieData(request.getCookies(), "userID")));

		}else if (task.equals("checkEditEvent")&&cookieStatus) {

			value = String.valueOf(
					UserStatusC.checkEditEvent(request.getParameter("evlink"),CookieFunctions.getCookieData(request.getCookies(), "userID")));

		} else if (task.equals("logout")) {
			logout(request, response);			
			response.sendRedirect(getServletContext().getContextPath()+"/login.html?note=logout");
		} else if(task.endsWith("login")){

			String eMail = request.getParameter("eMail");
			String password = request.getParameter("password");

			value = LoginC.tryLogin(eMail, password);

			if (value.equals("true")) {
				CookieFunctions.setCookie(response, "userID", eMail);
				CookieFunctions.setCookie(response, "sessionID", LoginC.getSessionID(eMail));

			}
		}
		XMLFunctions.sendShortResponse(response, "status", value);
	}

	static boolean checkSession(HttpServletRequest request, HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();

		String userID = CookieFunctions.getCookieData(cookies, "userID");
		String sessionID = CookieFunctions.getCookieData(cookies, "sessionID");

		if (userID == null || sessionID == null) {
			return false;
		} else {
			boolean status = LoginC.checkSessionID(userID, sessionID);
			if (!status) {
				logout(request, response);
			}
			return status;
		}
	}

	static void logout(HttpServletRequest request, HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();

		for (int i = 0; i < cookies.length; i++) {
			cookies[i].setMaxAge(0);
			cookies[i].setValue(null);
            response.addCookie(cookies[i]);
		}
	}

}