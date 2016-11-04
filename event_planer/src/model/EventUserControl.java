package model;

import java.util.ArrayList;
import java.util.List;

//IMPORTANT:class is Singleton! -> only one instance
//contains all Events, Participants, Organizer and Admins

public class EventUserControl {

	//Singleton
	  private static EventUserControl instance;
	  private EventUserControl () {}
	  public static synchronized EventUserControl getInstance () {
	    if (EventUserControl.instance == null) {
	    	EventUserControl.instance = new EventUserControl ();
	    }
	    return EventUserControl.instance;
	  }
	//Singleton
	  
	private List<Event> events = new ArrayList<Event>();
	private List<Participant> participants = new ArrayList<Participant>();
	private List<Organizer> organizers = new ArrayList<Organizer>();
	private List<Admin> admins = new ArrayList<Admin>();
	
	public List<Event> getEvents() {
		return events;
	}
	
	public boolean addEvent(Event newEvent){
		return events.add(newEvent);	
	}
	
	public boolean removeEvent(Event oldEvent){
		return events.remove(oldEvent);	
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public boolean addParticipant(Participant newParticipant){
		return participants.add(newParticipant);	
	}
	
	public boolean removeParticipant(Participant oldParticipant){
		return participants.remove(oldParticipant);	
	}

	public List<Organizer> getOrganizers() {
		return organizers;
	}

	public boolean addOrganizer(Organizer newOrganizer){
		return organizers.add(newOrganizer);	
	}
	
	public boolean removeOrganizer(Organizer oldOrganizer){
		return organizers.remove(oldOrganizer);	
	}

	public List<Admin> getAdmins() {
		return admins;
	}

	public boolean addAdmin(Admin newAdmin){
		return admins.add(newAdmin);	
	}
	
	public boolean removeAdmin(Admin oldAdmin){
		return admins.remove(oldAdmin);	
	}
	
}
