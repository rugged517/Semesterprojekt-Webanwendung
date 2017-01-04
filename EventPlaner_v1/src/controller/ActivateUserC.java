package controller;

import model.User;

public class ActivateUserC {

	public static boolean aktivate(String eMail, String code) {

		if (CheckFunctionsC.checkEMail(eMail)) {
			// create user
			User user = new User(eMail, null);
			System.out.println("testCode");

			if (user.getAktivationCode().equals(code)) {
				user.setAC(null);

				return true;
			}
		}
		return false;
	}

}
