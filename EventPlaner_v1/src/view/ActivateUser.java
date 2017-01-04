package view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActivateUserC;

@WebServlet("/AktivateUser")
public class ActivateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eMail = request.getParameter("eMail");
		String code = request.getParameter("code");
		
		if(ActivateUserC.aktivate(eMail, code)){
			response.sendRedirect("http://localhost:8080/eventPlanner/login.html?note=activationOK");
		}

	}


}
