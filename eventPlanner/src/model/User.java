package model;

//contains informations about user
import data.DBConnect;

public class User {

	protected String eMail;
	protected String password; //encrypted
	protected String aktivationCode;
	protected boolean doLogin;
	protected boolean activated=false;
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

	public String getAktivationCode() {
		return this.aktivationCode;
	}
	
	public boolean doLogin() {
		return doLogin;
	}
	
	public boolean doesExists() {
		return exists;
	}
	
	public boolean isActivated() {
		return activated;
	}
	
	/**
	 * @author Florian
	 * @return aktivationCode
	 * 
	 * creates and returns aktivationCode
	 */
	public String createAktivationCode() {
		aktivationCode=Functions.randomString(15);
		System.out.println(aktivationCode);
		DBConnect dbConnect =DBConnect.getInstance();
		dbConnect.getUser().add(eMail);
		dbConnect.getAc().add(aktivationCode);
		dbConnect.getPassword().add(password);
		return this.aktivationCode;
	}
	
	public void setAC(String ac) {
		DBConnect dbConnect =DBConnect.getInstance();
		dbConnect.setACforUser(eMail, ac);
		this.aktivationCode = ac;
	}

	/**
	 * @author Florian
	 * @param eMail
	 * @param password -> already encrypted
	 */
	public User(String eMail, String password) {
		this.eMail=eMail;
		this.password=password;
		
		//check if User exists in DB
		//checks if encrypted input equals DB password
		DBConnect dbConnect =DBConnect.getInstance();
		exists=dbConnect.checkForUser(eMail, this);		
		doLogin =dbConnect.checkForLogin(eMail, password);
		if(doLogin&&(aktivationCode==null)){
			activated=true;
		}
	}


}