package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import Hilfsklassen.ApplicationPK;

//contains informations about an event and an array of applications
@Entity
@Table (name = "Event")
@PersistenceContext (unitName = "eventPlanner")
public class Event implements java.io.Serializable {

	private Participant organizer;
	private String title;
	private String link;
	private String text;
	private String eMail;
	private Location location;
	private Location locationId;
	private Date eventDate;
	private Date deadline;
	private int minParticipants = 0;
	private int maxParticipants = 0;
	private AdditionalInformation additionalInformation= new AdditionalInformation();
	private List<Application> applications = new ArrayList<Application>();
	public EntityManager em;
	
	@ManyToOne
	public Participant getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Participant organizer) {
		this.organizer = organizer;
		em.persist(organizer);
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Id
	public String getLink() {
		return this.link;
	}
	
	public void setLink(String eventlink){
		this.link = eventlink;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getEMail() {
		return this.eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	@ManyToOne
	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getDate() {
		return this.eventDate;
	}

	public void setDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public int getMinParticipants() {
		return this.minParticipants;
	}

	public void setMinParticipants(int minParticipants) {
		this.minParticipants = minParticipants;
	}

	public int getMaxParticipants() {
		return this.maxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}
	
	@OneToOne(cascade ={CascadeType.ALL})
	@JoinColumn(name = "ID_AdditionalInformation")
	public AdditionalInformation getAdditionalInformation() {
		return this.additionalInformation;
	}

	public void setAdditionalInformation(AdditionalInformation additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	@OneToMany (mappedBy = "event")
	public List<Application> getApplications() {
		return this.applications;
	}

	public boolean removeApplication(Application oldApplication) {
		for (Participant participant : getListOfParticipants()) {
		ApplicationPK primKey = new ApplicationPK(participant, this); //!! "this" = This Event!? !!
		Application application = em.find(Application.class, primKey);
		em.remove(application);
		return applications.remove(application);
		}
	}

	/**
	 * 
	 * needs to generate an unique link
	 * 
	 * @param title
	 * @param location
	 * @param date
	 */
	public Event(Organizer organizer, String title, Location location, Date eventDate) {
		
		setLink(Functions.randomString(15));
		Event event = (Event) em.find(Event.class, link);
		setOrganizer(organizer);
		setTitle(title);
		setLocation(location);
		setDate(eventDate);
		em.persist(event);
	}

	public Event() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param reason
	 *            ->why cancel the event?
	 * 
	 * @param message
	 *            ->message to the subscribed participants
	 */
	public boolean cancelEvent(String reason, String message) {

		// send Mail to participants and remove from event
		for (Participant participant : getListOfParticipants()) {
			Participant part = (Participant) em.find(Participant.class, participant.getEmail());
			Functions.sendEMail(eMail, participant.getEMail(), reason);
			if(removeParticipant(participant)){
				em.remove(part);
				return false;
			}
		}
		return true;
	}

	// returns all registered participants
	public List<Participant> getListOfParticipants() {
		List<Participant> participants = new ArrayList<Participant>();

		for (Application application : applications) {
			participants.add(application.getParticipant());
		}
		return participants;
	}

	/**
	 * 
	 * @param application
	 *            ->witch application should be removed?
	 */
	
	/*
	 * TODO DB Remove of Participants Application
	 *
	 * not sure where to delete the Appl in DB
	 * either here or in Participant/Application 
	 *
	 */
	public boolean removeParticipant(Participant participant) {

		for (Application application : applications) {
			if (application.getParticipant() == participant) {
				if (participant.removeApplication(application)) {
					if (applications.remove(application)) {
						return true;
					}
				}
			}
		}
		return false;
	}

}