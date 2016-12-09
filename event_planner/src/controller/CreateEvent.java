package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateEvent")
public class CreateEvent extends HttpServlet {
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
	 * returns event link
	 */
	public String createEmptyEvent() {
		// TODO - implement CreateEvent.createEmptyEvent
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param values
	 * @param eventLink
	 */
	public boolean updateEventSettings(String[] values, String eventLink) {
		// TODO - implement CreateEvent.updateEventSettings
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param eventLink
	 * @param description
	 * @param required
	 * @param type
	 */
	public boolean saveAdditionalFields(String eventLink, String description, boolean required, int type) {
		// TODO - implement CreateEvent.saveAdditionalFields
		throw new UnsupportedOperationException();
	}

}