package model;

//contains informations about user

public class User {

	private String eMail;
	private String password; //encrypted
	private String name;
	private Location location;
	private String phoneNumber;

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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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