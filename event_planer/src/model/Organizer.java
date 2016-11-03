package model;

//is an user that can subscribe to events and create events

public class Organizer extends Participant {

	private Event[] events;

	public Event[] getEvents() {
		return this.events;
	}

	public void setEvents(Event[] events) {
		this.events = events;
	}
	
	public Organizer(String eMail, String password) {
		super(eMail, password);
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
			event.removeParticipan(participant);
		}
		
		//remove event from events array
		Event[] newEvents = new Event[events.length - 1];
		for (Event oEvent : events) {
			if (event != oEvent) {
				newEvents[newEvents.length] = oEvent;
			}
		}
		
	}
	
}