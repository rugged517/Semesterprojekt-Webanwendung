package controller;

import model.User;

public class ResetPasswordC{

	/**
	 * <p>
	 * Generates new aktivationCode (passwordAC) and uses it as new Password
	 * </p>
	 * @author Florian
	 * @param eMail

	 */
	public static String sendResetEMail(String eMail) {

		String result= "noEMail";

		if (CheckFunctionsC.checkEMail(eMail)) {
			// create user
			User user = new User(eMail, null);
			
			result= "noUser";
			if(user.doesExist()){
				
				result= "noActivation";
				if(user.isActivated()){
					
					//TODO send eMail
					//result="ok";
					result=user.updateSession();
				}
			}
		}
		return result;
	}

	public static String resetPassword(String eMail, String code, String password) {
		
		String result= "inputFail";
		
		if (CheckFunctionsC.checkEMail(eMail)&&password!=null) {
			// create user
			User user = new User(eMail, null);

			if(user.isActivated()&&user.getCode().equals(code)){
				result=String.valueOf(user.setPassword(password));
				user.updateSession();
			}
		}
		return result;
	}

}