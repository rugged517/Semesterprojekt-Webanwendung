package controller;

import model.User;

public class ActivateUserC {

	public static boolean aktivate(String eMail, String code) {

		if (CheckFunctionsC.checkEMail(eMail)) {
			// create user
			User user = new User(eMail, null);

			if (user.getCode().equals(code)&& !user.isActivated()) {
				return user.activateUser(true);
			}
		}
		return false;
	}

}
