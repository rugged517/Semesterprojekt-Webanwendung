package controller;

import model.User;

public class LoginC {

	/**
	 * 
	 * @param eMail
	 * @param password
	 */
	public boolean getUserStatus(String eMail, String password) {
		// TODO - implement Login.getUserStatus
		throw new UnsupportedOperationException();
	}

	public void sendCookie() {
		// TODO - implement Login.sendCookie
		throw new UnsupportedOperationException();
	}

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

}