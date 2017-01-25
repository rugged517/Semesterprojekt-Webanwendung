package model;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import Hilfsklassen.ApplicationPK;

//contains all needed information about one participant for one event
@Entity
@Table (name = "Application")
@IdClass (ApplicationPK.class)
public class Application implements java.io.Serializable {

	private Participant participant; //Who wants to participate?
	private Location location; //Where is the participant from?
	private PersonalInformation personallInformation; //Answers to the personal information questions.
	private Event event;
	private String eventLink;
	private String eMail;
	private EntityManager em;
	
	@Id
	public String getEmail(){
		return eMail;
	}
	public void setEmail(String email){
		this.eMail = email;
	}
	
	@ManyToOne
	public Participant getParticipant() {
		return this.participant;
	}
	
	@ManyToOne
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
	
	@Id
	public String getEventLink(){
		return eventLink;
	}
	public void setEventLink(String link){
		this.eventLink = link;
	}
	
	@ManyToOne
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	/**
	 * @param reason -> Why do
	 *	import Hilfsklassen.*;
	 *	es the participant want to unsubscribe?
	 *
	 *  ??
	 */
	public void signOut(String reason) {
		ApplicationPK primKey = new ApplicationPK(this.getParticipant(), this.getEvent());
		Application appl = (Application) em.find(Application.class, primKey);
		Functions.sendEMail(event.getOrganizer().getEMail(), participant.getEMail(), reason);
		event.removeApplication(this);
		em.remove(appl);
		//TODO send Mail to organizer with 'reason'
	}

	/**
	 * @param participant -> Who wants to subscribe?
	 */
	public Application(Participant participant, Event event) {
		ApplicationPK primKey = new ApplicationPK();
		Application appl = (Application) em.find(Application.class, primKey);
		/*
		 * Dont know if this:
		 * appl.participant = participant;
		 * 
		 * Or this:
		 * this.participant=participant;
		 */
		this.participant=participant;
		this.event=event;
		em.persist(appl);
	}
	public Application(){
		
	}



}