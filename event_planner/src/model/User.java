package model;

//contains informations about user
import data.DBConnect;

public class User {

	protected String eMail;
	protected String password; //encrypted
	protected boolean exists;

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

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}
	
	/**
	 * 
	 * @param eMail
	 * @param password -> already encrypted
	 * @return 
	 */
	public User(String eMail, String password) {
		this.eMail=eMail;
		this.password=password;
		
		//check if User exists in DB
		//checks if encrypted input equals DB password
		DBConnect dbConnect =DBConnect.getInstance();
		exists =dbConnect.checkForUser(eMail, password);
	}

}