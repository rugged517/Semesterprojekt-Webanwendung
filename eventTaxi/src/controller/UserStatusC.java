package controller;

import model.Event;
import model.Participant;
import model.User;

public class UserStatusC {

	/**
	 * 
	 * checks if the user is admin
	 * 
	 * @param userID
	 *            - eMail
	 * 
	 * @return true - is admin
	 * @return false - is no admin
	 * 
	 * @author Florian
	 */
	public static boolean checkAdminRights(String userID) {
		User user = new User(userID, null);
		return user.isAdmin();
	}

	/**
	 * 
	 * gives user admin rights
	 * 
	 * @param userID
	 *            - eMail
	 * @return true - if is admin
	 * 
	 * @author Florian
	 */
	public static boolean setAdminRights(String userEMail) {
		User user = new User(userEMail, null);
		return user.setAdmin(true);
	}

	/**
	 * 
	 * checks if the user is organizer
	 * 
	 * @param userID
	 *            - eMail
	 * 
	 * @return true - is admin
	 * @return false - is no admin
	 * 
	 * @author Florian
	 */
	public static boolean checkOrganizerRights(String userID) {
		Participant participant = new Participant(userID, null);
		return participant.isOrganizer();
	}

	/**
	 * 
	 * gives user organizer rights
	 * 
	 * @param userID
	 *            - eMail
	 * @return true - if is organizer
	 * 
	 * @author Florian
	 */
	public static boolean setOrganizerRights(String userEMail) {
		Participant participant = new Participant(userEMail, null);
		if (participant.setOrganizer(true)) {
			return participant.setAdmin(false);
		}
		return false;
	}

	/**
	 * 
	 * removes admin and organizer rights from user
	 * 
	 * @param userID
	 *            - eMail
	 * @return true - if is no admin and no organizer
	 * 
	 * @author Florian
	 */
	public static boolean setParticipantRights(String userEMail) {
		Participant participant = new Participant(userEMail, null);
		if (participant.setOrganizer(false)) {
			return participant.setAdmin(false);
		}
		return false;
	}

	public static boolean deleteUser(String userEMail) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 * activates user without activation code or deactivates user.
	 * 
	 * @param userID
	 *            - eMail
	 * @return true - if user is activated
	 * 
	 * @author Florian
	 */
	public static boolean activateUser(String userEMail) {
		User user = new User(userEMail, null);
		if(user.isActivated()){
			return user.activateUser(false);
		}
		return user.activateUser(true);
	}
	
	
	/**
	 * 
	 * check if user is allowed to edit event
	 * @param link - event link
	 * @param userID
	 *            - eMail	 
	 * @return true - if user is admin or event creator
	 * 
	 * @author Florian
	 */
	public static boolean checkEditEvent(String link, String userID) {
		
		if(!checkAdminRights(userID)){
			return true;
		}else{
			Event event= new Event(link);
			if(event.getOrganizerMail().equals(userID)){
				return true;
			}
		}
		return false;
	}

}