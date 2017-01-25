package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


//is an user that can subscribe to events and create events
@Entity
@Table (name = "Organizer") //INHERITANCE??? 
@PersistenceContext (unitName = "EventPlaner_v1")
public class Organizer extends Participant implements Serializable {

	private List<Event> events = new ArrayList<Event>();
	private String title;
	private Date date;
	public EntityManager em;
	
	@Id
	public String getEmail(){
		return this.getEMail();
	}
	
	public void setEmail(String eMail){
		this.setEMail(eMail);
	}
	@OneToMany (mappedBy = "organizer")
	public List<Event> getEvents() {
		return this.events;
	}

	public boolean addEvent(Event newEvent /*String email*//*used for adding organizer to event!?*/){
		return events.add(newEvent);	
	}
	
	public Organizer(String eMail, String password) {
		super(eMail, password);
		
		//find Organizer with this eMail in DB Table Organizer 
		if(em.find (Participant.class, eMail)!= null){
		doLogin = true;
		}
		else{
			doLogin = false;
		}
		}
	protected Organizer(){
		
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
			Participant part = (Participant) em.find(Participant.class, participant.getEmail());
			event.removeParticipant(participant);
			em.remove(part);
		}
		
	}
	
}