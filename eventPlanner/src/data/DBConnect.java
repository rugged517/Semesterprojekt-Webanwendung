package data;

import java.util.ArrayList;

import model.User;

public class DBConnect {

	public static ArrayList<String> userDB =new ArrayList<String>();
	public static ArrayList<String> passwordDB=new ArrayList<String>();
	public static ArrayList<String> acDB=new ArrayList<String>();
	
	public static ArrayList<String> getUser() {
		return userDB;
	}

	public static void setUser(ArrayList<String> useri) {
		userDB = useri;
	}

	public static ArrayList<String> getPassword() {
		return passwordDB;
	}

	public static void setPassword(ArrayList<String> passwordi) {
		passwordDB = passwordi;
	}

	public static ArrayList<String> getAc() {
		return acDB;
	}

	public static void setAc(ArrayList<String> aci) {
		acDB = aci;
	}

	private static DBConnect instance;

	private DBConnect() {}

	public static synchronized DBConnect getInstance() {
		 if (DBConnect.instance == null) {
			 DBConnect.instance = new DBConnect ();
		    }
		 return DBConnect.instance;
	}

//TODO remove user
	public boolean checkForUser(String eMail, User user) {
		//check in DB if eMail returns 1 value
		for( int i = 0; i < userDB.size(); i++ ){
			if(userDB.get(i).equals(eMail)){
				user.setAC(acDB.get(i));
				return true;				
			}
		}
		return false;

	}
	
	public boolean checkForParticipant(String eMail) {
		//check in DB if table participants contains eMail
		String returnValues[]={"userMail"};
		
		if(returnValues.length==1){
			return true;
		}
		
		return false;
	}
	
	public boolean checkForOrganizer(String eMail) {
		//check in DB if table participants contains eMail with isOrganizer=true
		String returnValues[]={"userMail"};
		
		if(returnValues.length==1){
			return true;
		}
		
		return false;
	}

	public boolean checkForLogin(String eMail, String password) {
		//check in DB if eMail + password returns 1 value

		for( int i = 0; i < userDB.size(); i++ ){
			if(userDB.get(i).equals(eMail)&&passwordDB.get(i).equals(password)){
				return true;				
			}
		}
		
		return false;
	}


	public void setACforUser(String eMail, String ac) {
		
		for( int i = 0; i < userDB.size(); i++ ){
			if(userDB.get(i).equals(eMail)){
				acDB.set(i, ac);	
			}
		}
	}
	
}
