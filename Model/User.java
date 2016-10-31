package Model;

//contains informations about user

public class User {

	private string eMail;
	private string password; //encrypted
	private string name;
	private Location location;
	private string phoneNumber;

	public string getEMail() {
		return this.eMail;
	}

	public void setEMail(string eMail) {
		this.eMail = eMail;
	}

	public string getPassword() {
		return this.password;
	}

	public void setPassword(string password) {
		this.password = password;
	}

	public string getName() {
		return this.name;
	}

	public void setName(string name) {
		this.name = name;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public string getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(string phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 
	 * @param eMail
	 * @param password -> already encrypted
	 */
	public void User(string eMail, string password) {
		// TODO - implement User.User
		throw new UnsupportedOperationException();
	}

	/**
	 * checks if encrypted input equals password
	 * 
	 * @param input
	 */
	public boolean isCorrectPassword(string input) {
		// TODO - implement User.isCorrectPassword
		throw new UnsupportedOperationException();
	}

}