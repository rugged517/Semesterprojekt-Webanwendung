package controller;

import java.security.*;
import java.math.*;

import model.User;

public class ResetPasswordC{

	/**
	 * <p>
	 * Generates new aktivationCode (passwordAC) and uses it as new Password
	 * </p>
	 * @author Florian
	 * @param eMail
	 * @throws NoSuchAlgorithmException
	 *             if md5 hash can't be created
	 */
	public static boolean sendResetEMail(String eMail) throws NoSuchAlgorithmException {
		User user = new User(eMail, "");

		if (user.doesExists()) {
			// create aktivationCode and send verification mail
			String passwordAC = user.createAktivationCode();

			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(passwordAC.getBytes(), 0, passwordAC.length());
			user.setPassword(new BigInteger(1, m.digest()).toString(16));

			// TODO send Mail with passwordAC;
			return true;
		} else {
			return false;
		}
	}

	public static String getPWLINK(String eMail) {
		User user = new User(eMail, null);
		
		return user.getAktivationCode();
		// TODO Auto-generated method stub
		
	}

}