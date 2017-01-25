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
