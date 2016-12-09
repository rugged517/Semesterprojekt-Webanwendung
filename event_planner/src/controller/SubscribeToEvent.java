package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SubscribeToEvent")
public class SubscribeToEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("<b>Served at: </b>").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

	public String[] getRequestedFields() {
		// TODO - implement subscribeToEvent.getRequestedFields
		throw new UnsupportedOperationException();
	}

	/**
	 * returns fields with errors. returns null if no errors and participant was added
	 * @param fields
	 * @param values
	 */
	public String[] addParticipant(String[] fields, String[] values) {
		// TODO - implement subscribeToEvent.addParticipant
		throw new UnsupportedOperationException();
	}

}