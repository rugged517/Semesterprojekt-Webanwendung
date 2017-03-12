package view;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieFunctions {

	/**
	 * 
	 * Gets given parameter from cookies.
	 * 
	 * @param cookies
	 *            - session cookies. Cookie[] cookies=request.getCookies()
	 * 
	 * @return value of the given parameter
	 * 
	 * @author Florian
	 */
	static String getCookieData(Cookie[] cookies, String parameter) {

		Cookie cookie = null;

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals(parameter)) {
					return cookie.getValue();
				}
			}
		}

		return null;
	}

	/**
	 * 
	 * Adds given parameter and value to response as cookies.
	 * 
	 * @param response
	 *            - HttpServletResponse
	 * @param name
	 *            - cookie parameter name
	 * @param value
	 *            - cookie parameter value
	 * 
	 * @author Florian
	 */
	public static void setCookie(HttpServletResponse response, String name, String value) {
		Cookie cookie = new Cookie(name, value);
		response.addCookie(cookie);
	}

}
