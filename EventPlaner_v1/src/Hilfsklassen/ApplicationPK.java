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
	private String EMail;
	private String eventlink;
	
	public ApplicationPK(Participant participant, Event event) {
		this.participant=participant;
		this.event=event;
	}
	protected ApplicationPK(){
		
	}
	
	public String getEmail(){
		return participant.getEMail();
	}
	public void setEmail(String EMail){
		participant.setEMail(EMail);
	}
	public String getEventLink(){
		return event.getLink();
	}
	public void setEventLink(String eventlink){
		event.setLink(eventlink);
	}
	public boolean equals(Object refObj){
		if(this == refObj)
			return true;
		else if(!(refObj instanceof ApplicationPK))
			return false;
		ApplicationPK refApplPK = (ApplicationPK) refObj;
		
		return this.EMail == refApplPK.EMail && this.eventlink == refApplPK.eventlink;
	}
	
	public int hashCode(){
		return (int)(EMail.hashCode() ^ eventlink.hashCode());
	}
}
