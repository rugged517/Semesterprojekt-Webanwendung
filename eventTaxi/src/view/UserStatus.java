package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.UserStatusC;

@WebServlet("/UserStatus")
public class UserStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Checks if session is correct. If user is admin edits the rights of the
	 * given userEMail parameter. In demo userEMail=userID.
	 * 
	 * @author Florian
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String task = request.getParameter("task");
		String value = "noLogin";

		String userEMail = request.getParameter("userEMail");
		if (userEMail != null) {
			if (userEMail.equals("")) {
				userEMail = CookieFunctions.getCookieData(request.getCookies(), "userID");
			}
		}

		// first check if userID and sessionID are okay
		if (Login.checkSession(request, response)) {
			String userID = CookieFunctions.getCookieData(request.getCookies(), "userID");
			// second check if user has rights to edit user (is admin)

			// TODO
			// if (UserStatusC.checkAdminRights(userID)) {
			// TODO delete! only for Demo! In this demo every user can edit its
			// own rights.
			value = "noRights";
			if (true) {
				// give rights to user
				if (task.equals("admin")) {
					value = String.valueOf(UserStatusC.setAdminRights(userEMail));
				} else if (task.equals("organizer")) {
					value = String.valueOf(UserStatusC.setOrganizerRights(userEMail));
				} else if (task.equals("participant")) {
					value = String.valueOf(UserStatusC.setParticipantRights(userEMail));
				} else if (task.equals("activate")) {
					value = String.valueOf(UserStatusC.activateUser(userEMail));
				} else if (task.equals("delete")) {
					value = String.valueOf(UserStatusC.deleteUser(userEMail));
					List<String> parameterL = new ArrayList<String>();
					List<String> valueL = new ArrayList<String>();
					parameterL.add("status");
					valueL.add(value);
					parameterL.add("target");

					// logout if admin has deleted own account
					if (userEMail.equals(userID) && value.equals("true")) {
						Login.logout(request, response);
						valueL.add("user");
					} else {
						valueL.add("self");
					}
					XMLFunctions.sendResponse(response, parameterL, valueL);
					return;
				}
			} else if (task.equals("delete") && userID.equals(userEMail)) {
				// if user wants to delete own account
				Login.logout(request, response);
				value = "true";
			}
		}
		XMLFunctions.sendShortResponse(response, "status", value);
	}

}