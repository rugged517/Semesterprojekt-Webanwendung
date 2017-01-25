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
	
	public PersonalInformationPK(){
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
