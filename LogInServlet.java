
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.*;
//import javax.persistence.*;


/* Servlet implementation class LogInServlet
 */
//@WebServlet(description = "Log In Servlet", urlPatterns = { "/LogInServlet" })

public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogInServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String email = request.getParameter("E-Mail");
		String password = request.getParameter("password");
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:myswl:localhost:3306/javademo", "root", "" );
		PreparedStatement prep = con.prepareStatement("select user, password from testdb where user=? and password=?");
		prep.setString(1, "user");
		prep.setString(2, "password");
		ResultSet rs = prep.executeQuery();
		if(rs.next()){
			System.out.println("Correct Login");
		}
		else{
			System.out.println("Incorrect Login");
		}
		}catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); 
		}
	
	}

}
