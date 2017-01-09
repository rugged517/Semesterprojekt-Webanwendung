package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class XMLFunctions {

	/**
	 * Builds response to webpage using two Lists.
	 * 
	 * @author Florian 
	 */
	public static void sendResponse(HttpServletResponse response, List<String> parameter, List<String> values) {
		response.setContentType("text/xml;charset=UTF-8");
		
		PrintWriter writer = null;
		try {
			
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
			writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			writer.append("<response>");

			for (int i = 0; i < parameter.size(); i++) {
				writer.append("<").append(parameter.get(i)).append(">").append(values.get(i)).append("</").append(parameter.get(i)).append(">");
			}
			
			writer.append("</response>");

	}

}
