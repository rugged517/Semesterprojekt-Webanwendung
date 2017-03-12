package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ListObjectsC;
import controller.UserStatusC;

@WebServlet("/ListObjects")
public class ListObjects extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String task = request.getParameter("task");

		// first check if userID and sessionID are okay
		if (Login.checkSession(request, response)) {
			String userID = CookieFunctions.getCookieData(request.getCookies(), "userID");

			if (task.equals("user") && UserStatusC.checkAdminRights(userID)) {
				// only admin can see the user list
				buildListXML(ListObjectsC.getUserList(),response);				
			} else if (task.equals("allEvents")) {
				buildListXML(ListObjectsC.getAllEventsList(userID),response);	
			} else if (task.equals("applications")) {

			}
		}
	}
	
	protected void buildListXML(String [][] resultList, HttpServletResponse response){
		
		List<String> parameter = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		
		// fills parameter (ex. param0, param1, etc) and value List with result from getListX
		for(int i=0; i<resultList.length;i++){		
			for(int in=0; in<resultList[i].length;in++){
				parameter.add("param"+in);
				values.add(resultList[i][in]);
			}
		}
		XMLFunctions.sendResponseLarge(response, parameter, values, "list", resultList[0].length);
	}

}
