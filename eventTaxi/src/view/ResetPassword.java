package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ResetPasswordC;


@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Trys to send a password reset E-Mail. Returns false to html page if no E-Mail was send.
	 * 
	 * @author Florian
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String task = request.getParameter("task");

		if (task.equals("eMail")) {
			XMLFunctions.sendShortResponse(response, "startus", ResetPasswordC.sendResetEMail(request.getParameter("eMail")));
		}else {
			XMLFunctions.sendShortResponse(response, "startus", ResetPasswordC.resetPassword(request.getParameter("eMail"),request.getParameter("code"),request.getParameter("password")));
		}
	}

}