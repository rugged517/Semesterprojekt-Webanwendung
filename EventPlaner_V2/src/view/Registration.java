package view;

import java.io.IOException;
import java.io.PrintWriter;

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

		
		try {
			response.setContentType("text/xml;charset=UTF-8");
			PrintWriter writer = response.getWriter();

			writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			writer.append("<response><startus>");

			// try to create new user and get status
			String[] status = RegistrationC.createNewUser(eMail, password);

			writer.append(status[0]).append("</startus>");

			if (status[0] != "true") {
				writer.append("<error>").append(status[1]).append("</error>");
			}

			else {
				writer.append("<code>").append(status[1]).append("</code>");

			}

			writer.append("</response>");



		} catch (IOException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			e.printStackTrace();
		}
	}

}