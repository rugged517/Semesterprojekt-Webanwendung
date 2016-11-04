package model;

import java.util.ArrayList;
import java.util.List;

//is an user that can subscribe to events and create events

public class Organizer extends Participant {

	private List<Event> events = new ArrayList<Event>();

	public List<Event> getEvents() {
		return this.events;
	}

	public boolean addEvent(Event newEvent){
		return events.add(newEvent);	
	}
	
	public Organizer(String eMail, String password) {
		super(eMail, password);
		EventUserControl.getInstance().addOrganizer(this);
	}

	/**
	 * 
	 * @param event ->from witch event?
	 * 
	 * @param participant -> witch participant should be removed?
	 * 
	 * @param reason -> why is the participant removed? eMail to participant
	 * 
	 */
	public void removeParticipansFromEvent(Event event, Participant[] participants, String reason){
		
		//send Mail to participant and remove from event
		for(Participant participant: participants){			
			Functions.sendEMail(getEMail(), participant.getEMail(), reason);			
			event.removeParticipant(participant);
		}
		
	}
	
}