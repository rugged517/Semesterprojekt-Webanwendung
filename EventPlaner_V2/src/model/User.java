package model;
import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.*;

@Entity
@Table (name = "User")
@PersistenceContext(unitName = "EventPlaner_V2")
public class User implements java.io.Serializable {

	protected String eMail;
	protected String password; //encrypted
	protected String aktivationCode;
	protected boolean doLogin;
	protected boolean activated=false;
	protected boolean exists;
	private EntityManager em;
	
	protected User(){
		
	}
	@Id
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
		
		/*
		 * For what is that for?
		 *	dbConnect.getUser().add(eMail);
		 *
		 */
		User user = (User) em.find(User.class, this.eMail);
		user.setAC(aktivationCode);
		user.setPassword(password);
		em.persist(user);
		
		return this.aktivationCode;
	}
	
	public void setAC(String ac) {
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
		User user = (User) em.find(User.class, this.eMail);
		if(user.getPassword().equals(password)){
			doLogin = true;
			exists = true;
		}
		/*
		 * For what is this for?
		 * doLogin =dbConnect.checkForLogin(eMail, password);
		 */
		if(doLogin&&(aktivationCode==null)){
			activated=true;
		}
		em.persist(user);
	}


}