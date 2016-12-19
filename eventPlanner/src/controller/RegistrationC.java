package controller;

import model.User;
import controller.CheckFunctionsC;


public class RegistrationC{

	/**
	 * <p>
	 * Result: status (true/false) and optional error
	 * (knownEMail/noEMail).<br>
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
	public static String[] createNewUser(String eMail, String password){

		String[] result= new String[2];

		if (CheckFunctionsC.checkEMail(eMail)) {
			// create user
			User newUser = new User(eMail, password);
			result[0]=String.valueOf(!newUser.doLogin());

			// check if eMail is new to system
			if (!newUser.doLogin()) {
				// create aktivationCode and send verification mail
				sendVerificationMail(newUser.createAktivationCode());
				
				//TODO only for Demo!
				result[1]=newUser.getAktivationCode();
				//
			} else {
				result[1]="knownEMail";
			}
		} else {
			// if eMail String is not a correct eMail
			result[0]="false";
			result[1]="noEMail";
		}
		return result;
	}

	/**
	 * @param aktivationCode
	 * 
	 */
	public static void sendVerificationMail(String aktivationCode) {
		// TODO
		//throw new UnsupportedOperationException();
	}

}