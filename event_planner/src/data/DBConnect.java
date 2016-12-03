package data;

public class DBConnect {

	private static DBConnect instance;

	private DBConnect() {}

	public static synchronized DBConnect getInstance() {
		 if (DBConnect.instance == null) {
			 DBConnect.instance = new DBConnect ();
		    }
		 return DBConnect.instance;
	}


	public boolean checkForUser(String eMail, String password) {
		//check in DB if eMail + password returns 1 value
		String returnValues[]={"userMail"};
		
		if(returnValues.length==1){
			return true;
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
	
}
