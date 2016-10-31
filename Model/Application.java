package Model;

//contains all needed information about one participant for one event

public class Application {

	private Participant participant; //Who wants to participate?
	private Location location; //Where is the participant from?
	private PersonalInformation personallInformation; //Answers to the personal information questions.

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

	/**
	 * 
	 * @param reason -> Why does the participant want to unsubscribe?
	 */
	public void signOut(string reason) {
		// TODO - implement Application.signOut
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param participant -> Who wants to subscribe?
	 */
	public Application(Participant participant) {
		// TODO - implement Application.Application
		throw new UnsupportedOperationException();
	}

}