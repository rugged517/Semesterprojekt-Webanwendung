package model;
import javax.persistence.*;
import Hilfsklassen.*;

//superclass for all additional information fields
@Entity 
@Table (name = "Information")
@IdClass (InformationPK.class)
@PersistenceContext(unitName = "eventPlanner")
public class Information implements java.io.Serializable {
	
	private String description; //description for the input field (checkbox, date usw.)
	private boolean required = false; //is this information necessary to subscribe to the event?
	private AdditionalInformation additionalInformation;
	private String settings;
	private String value;
	private int id = additionalInformation.getId();
	private EntityManager em;
	
	@Id
	public String getSettings() {
		return settings;
	}
	public void setSettings(String settings) {
		this.settings = settings;
	}
	@Id
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Id
	public int getId(){
		return additionalInformation.getId();
	}
	public void setId(int id){
		additionalInformation.setId(id);
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isRequired() {
		return this.required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "AdditionalInformation_ID")
	public AdditionalInformation getAdditionalInformation(){
		return additionalInformation;
	}
	public void setAdditionalInformation(AdditionalInformation a){
		this.additionalInformation = a;
	}

	/**
	 * 
	 * @param description -> Which information should the user enter?
	 */
	public Information(String description) {
		InformationPK primKey = new InformationPK(this.id, this.value, this.settings); 
		Information info = (Information) em.find(Information.class, primKey);
		this.description = description;
		em.persist(info);
	}
	protected Information(){
		
	}

}