package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class XMLFunctions {

	/**
	 * Puts two Stings in ArrayList to use sendResponse function
	 * 
	 * @author Florian
	 */
	public static void sendShortResponse(HttpServletResponse response, String parameter, String value) {

		List<String> parameterL = new ArrayList<String>();
		List<String> valueL = new ArrayList<String>();
		parameterL.add(parameter);
		valueL.add(value);

		sendResponse(response, parameterL, valueL);
	}

	/**
	 * Builds response with normal parameter to webpage using two Lists.
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
			writer.append("<").append(parameter.get(i)).append(">").append(values.get(i)).append("</")
					.append(parameter.get(i)).append(">");
		}
		writer.append("</response>");
	}

	/**
	 * Builds response with objects and parameter to webpage using two Lists.
	 * <br>
	 * example:<br>
	 * &lt;objectName&gt;<br>
	 * &nbsp;&lt;parameter1&gt;value1&lt;/parameter1&gt;<br>
	 * &nbsp;&lt;parameter2&gt;value2&lt;/parameter2&gt;<br>
	 * &lt;/objectName&gt;<br>
	 * 
	 * @param objElNr - number of elements in object
	 * 
	 * @author Florian
	 */
	public static void sendResponseLarge(HttpServletResponse response, List<String> parameter, List<String> values,
			String objectName, int objElNr) {
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
			// if start of object open with objectName
			if ((i % objElNr) == 0) {
				writer.append("<").append(objectName).append(">");
			}
			writer.append("<").append(parameter.get(i)).append(">").append(values.get(i)).append("</")
					.append(parameter.get(i)).append(">");

			// if end of object close with objectName.
			// (i+1)%objectElements is 0 if next i starts new object
			if (((i + 1) % objElNr) == 0) {
				writer.append("</").append(objectName).append(">");
			}

		}
		writer.append("</response>");
	}

}
