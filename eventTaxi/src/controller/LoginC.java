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
			User user = new User(eMail, password);
			
			result= "noUser";
			if(user.doLogin()){
				result=String.valueOf(user.isActivated());
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
	public static boolean checkSessionID(String userID, String sessionID) {
		
		User user = new User(userID, null);
		
		if(user.getCode().equals(sessionID)){
			return true;
		}
		return false;
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
		
		User user = new User(eMail, null);
		
		if(user.isActivated()){
			return user.updateSession();			
		}
		return null;
	}

}