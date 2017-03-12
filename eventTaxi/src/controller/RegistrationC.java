package controller;

import model.User;

import controller.CheckFunctionsC;

public class RegistrationC {

	/**
	 * <p>
	 * Result: status (true/false) and optional error (knownEMail/noEMail).<br>
	 * Sends aktivationCode for verification mail
	 * </p>
	 * <p>
	 * Errors:<br>
	 * knownEMail -> eMail is known to system<br>
	 * noEMail -> eMail String is not a correct eMail
	 * </p>
	 * 
	 * @author Florian
	 * @return
	 * 
	 */
	public static String[] createNewUser(String eMail, String password) {

		String[] result = new String[2];
		result[0] = "false";
		
		if (CheckFunctionsC.checkEMail(eMail)) {
			// create user
			User newUser = new User(eMail, password);

			// check if eMail is new to system
			if (!newUser.doesExist()) {

				// save User to DB
				if (newUser.createNewUser()) {

					
					result[0] = "true";
					// TODO only for Demo!
					result[1] = newUser.getCode();
					//

					// get activation code and send verification mail
					sendVerificationMail(newUser.getCode(), eMail);

				} else {
					result[1] = "internalError";
					
				}
			} else {
				result[1] = "knownEMail";
			}
		} else {
			// if eMail String is not a correct eMail
			result[1] = "noEMail";
		}
		return result;
	}

	/**
	 * 
	 * Send eMail with aktivationCode to user
	 * 
	 * @param aktivationCode
	 * 
	 */
	public static void sendVerificationMail(String aktivationCode, String eMail) {
		// TODO
	}

}