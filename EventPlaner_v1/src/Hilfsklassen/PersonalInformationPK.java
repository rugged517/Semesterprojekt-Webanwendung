package Hilfsklassen;

import model.AdditionalInformation;
import model.Event;
import model.Participant;

public final class PersonalInformationPK implements java.io.Serializable {

	private Participant participant;
	private Event event;
	private AdditionalInformation additionalInformation;
	private String email;
	private String link;
	private int id;
	
	protected PersonalInformationPK(){
	}
	
	public String getEmail() {
		return participant.getEMail();
	}
	public void setEmail(String email) {
		participant.setEMail(email);
	}
	
	public String getLink() {
		return event.getLink();
	}
	public void setLink(String link) {
		event.setLink(link);
	}
	
	public int getId() {
		return additionalInformation.getId();
	}
	public void setId(int id) {
		additionalInformation.setId(id);
	}
	
	public boolean equals(Object refObj){
		if(this == refObj)
			return true;
		else if(!(refObj instanceof PersonalInformationPK))
			return false;
		PersonalInformationPK refPersonalInformationPK = (PersonalInformationPK) refObj;
		
		return email == refPersonalInformationPK.email && link == refPersonalInformationPK.link &&
				id == refPersonalInformationPK.id;
	}
	public int hashCode(){
		return (int)(id ^ email.hashCode()  ^ link.hashCode());
	}
	
}
