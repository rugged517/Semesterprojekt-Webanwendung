package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
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
	 * returns errors in an array. If array=null all details are OK.
	 * @param fields
	 * @param values
	 */
	public String[] checkDetails(String[] fields, String[] values) {
		// TODO - implement EditProfile.checkDetails
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param fields
	 * @param values
	 */
	public void saveProfile(String[] fields, String[] values) {
		// TODO - implement EditProfile.saveProfile
		throw new UnsupportedOperationException();
	}

}