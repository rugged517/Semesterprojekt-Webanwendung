package model;

//contains informations about user

public class User {

	private String eMail;
	private String password; //encrypted


	public String getEMail() {
		return this.eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * 
	 * @param eMail
	 * @param password -> already encrypted
	 */
	public User(String eMail, String password) {
		this.eMail=eMail;
		this.password=password;
	}

	/**
	 * checks if encrypted input equals password
	 * 
	 * @param input
	 */
	public boolean isCorrectPassword(String input) {
		if(password==input){
			return true;
		}
		return false;
	}

}