package model;

import java.util.List;

public class Admin extends User{
	
	public Admin(String eMail, String password) {
		super(eMail, password);
		EventUserControl.getInstance().addAdmin(this);
	}
	
	//remove
	public void removeEvent(Event oldEvent){
		EventUserControl.getInstance().removeEvent(oldEvent);
	}
	
	public void removeParticipant(Participant oldParticipant){
		EventUserControl.getInstance().removeParticipant(oldParticipant);
	}
	
	public void removeOrganizer(Organizer oldOrganizer){
		EventUserControl.getInstance().removeOrganizer(oldOrganizer);
	}
	
	public void removeAdmin(Admin oldAdmin){
		EventUserControl.getInstance().removeAdmin(oldAdmin);
	}
	
	//getList
	public List<Event> getAllEvents() {
		return EventUserControl.getInstance().getEvents();
	}
	
	public List<Participant> getAllParticipants() {
		return EventUserControl.getInstance().getParticipants();
	}

	public List<Organizer> getAllPOrganizers() {
		return EventUserControl.getInstance().getOrganizers();
	}
	
	public List<Admin> getAllAdmins() {
		return EventUserControl.getInstance().getAdmins();
	}
}
