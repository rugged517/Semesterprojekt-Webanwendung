package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//contains informations about an event and an array of applications

public class Event {

	private Organizer organizer;
	private String title;
	private String link;
	private String text;
	private String eMail;
	private Location location;
	private Date eventDate;
	private Date deadline;
	private int minParticipants = 0;
	private int maxParticipants = 0;
	private AdditionalInformation additionalInformation= new AdditionalInformation();
	private List<Application> applications = new ArrayList<Application>();

	public Organizer getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return this.link;
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

	public AdditionalInformation getAdditionalInformation() {
		return this.additionalInformation;
	}

	public void setAdditionalInformation(AdditionalInformation additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public List<Application> getApplications() {
		return this.applications;
	}

	public boolean removeApplication(Application oldApplication) {
		return applications.remove(oldApplication);
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

		this.link = Functions.randomString(15);
		// TODO check DB if link is unique

		this.organizer = organizer;
		this.title = title;
		this.location = location;
		this.eventDate = eventDate;
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
			Functions.sendEMail(eMail, participant.getEMail(), reason);
			if(removeParticipant(participant)){
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