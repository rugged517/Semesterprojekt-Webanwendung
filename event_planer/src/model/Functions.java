package model;

import java.security.SecureRandom;

public class Functions {

	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	public static String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param oldApplication
	 *            ->witch application should be removed?
	 * 
	 * @param applications
	 *            -> all applications
	 * 
	 * @return all applications without the old one
	 */
	public static Application[] removeApplication(Application oldApplication, Application[] applications) {

		Application[] newApplications = new Application[applications.length - 1];

		for (Application application : applications) {
			if (application != oldApplication) {
				newApplications[newApplications.length] = application;
			}
		}
		return newApplications;
	}

	
	/**
	 * 
	 * @param from -> who sends the mail?
	 * 
	 * @param to -> the eMail to be sent to
	 * 
	 * @param content -> text content of the mail
	 */
	public static void sendEMail(String from, String to, String content) {
//TODO
	}

}
