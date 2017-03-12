package model;

//contains all needed information about one participant for one event

public class Application {

	private Participant participant=null; //Who wants to participate?
	private Location location=null; //Where is the participant from?
	private PersonalInformation personallInformation=null; //Answers to the personal information questions.
	private Event event=null;
	
	public Participant getParticipant() {
		return this.participant;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public PersonalInformation getPersonallInformation() {
		return this.personallInformation;
	}

	public void setPersonallInformation(PersonalInformation personallInformation) {
		this.personallInformation = personallInformation;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	/**
	 * 
	 * @param reason -> Why does the participant want to unsubscribe?
	 */
	public void signOut(String reason) {
		
		//TODO send Mail to organizer with 'reason' and remove application from DB
	}

	/**
	 * 
	 * @param participantEMail -> Who wants to subscribe?
	 */
	public Application(String participantEMail, String eventLink) {
		
	}




}