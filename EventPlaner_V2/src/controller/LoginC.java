package controller;

import model.User;

public class LoginC {

	/**
	 * 
	 * @param eMail
	 * @param password
	 */
	public static String tryLogin(String eMail, String password) {

		String result= "noEMail";

		if (CheckFunctionsC.checkEMail(eMail)) {
			// create user
			User newUser = new User(eMail, password);
			
			result= "noUser";
			if(newUser.doLogin()){
				result=String.valueOf(newUser.isActivated());
			}
			
		}
		return result;

	}

	
	/**
	 * 
	 * checks if the sessionID is equal to the session ID from the DB
	 * 
	 * @author Florian
	 * 
	 * @param userID
	 * @param sessionID
	 */
	public static String checkSessionID(String userID, String sessionID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * Generates session ID and inserts it into DB
	 * 
	 * @author Florian
	 * 
	 * @param eMail
	 */
	public static String getSessionID(String eMail) {
		// TODO Auto-generated method stub
		return null;
	}

}