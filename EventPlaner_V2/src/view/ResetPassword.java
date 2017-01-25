package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.*;

import controller.ResetPasswordC;


@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			//response.setContentType("text/xml;charset=UTF-8");
			
			PrintWriter writer = response.getWriter();
			//writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			//writer.append("<response><startus>");
			
			if (ResetPasswordC.sendResetEMail(request.getParameter("eMail"))) {
				//writer.append("true</startus>");
				
				//TODO DELETE! Only for Demo!
				writer.append("<link>"+ResetPasswordC.getPWLINK((request.getParameter("eMail")))+"</link>");

								
			} else {
				writer.append("false</startus>");
			}

			writer.append("</response>");
			
		} catch (IOException|NoSuchAlgorithmException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}

}