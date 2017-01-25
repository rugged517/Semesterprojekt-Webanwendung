package Hilfsklassen;

import model.Event;
import model.Location;
import model.Participant;
import model.PersonalInformation;

public final class ApplicationPK implements java.io.Serializable {
	
	private Participant participant; //Who wants to participate?
	private Location location; //Where is the participant from?
	private PersonalInformation personallInformation; //Answers to the personal information questions.
	private Event event;
	private String email;
	private String eventLink;
	

	public ApplicationPK(){
		
	}
	public ApplicationPK(Participant participant, Event event) {
		this.participant=participant;
		this.event=event;
	}
	
	public String getEmail(){
		return email;
	}
	public void setEmail(String EMail){
		this.email = EMail;
	}
	public String getEventLink(){
		return eventLink;
	}
	public void setEventLink(String eventlink){
		this.eventLink = eventlink;
	}
	public boolean equals(Object refObj){
		if(this == refObj)
			return true;
		else if(!(refObj instanceof ApplicationPK))
			return false;
		ApplicationPK refApplPK = (ApplicationPK) refObj;
		
		return this.email == refApplPK.email && this.eventLink == refApplPK.eventLink;
	}
	
	public int hashCode(){
		return (int)(email.hashCode() ^ eventLink.hashCode());
	}
}
