package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditEvent")
public class EditEvent extends HttpServlet {
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
	/**
	 * 
	 * @param eventLink
	 */
	public boolean updateEvent(String eventLink) {
		// TODO - implement EditEvent.updateEvent
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param informationID
	 */
	public boolean deleteAdditionalInformation(int informationID) {
		// TODO - implement EditEvent.deleteAdditionalInformation
		throw new UnsupportedOperationException();
	}

}