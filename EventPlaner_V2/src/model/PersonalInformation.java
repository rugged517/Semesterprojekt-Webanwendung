package model;

import java.sql.Date;
import javax.persistence.*;
import Hilfsklassen.*;

import Hilfsklassen.PersonalInformationPK;

//contains all additional information from one participant for one application
@Entity
@Table (name = "PersonalInformation")
@IdClass (PersonalInformationPK.class)
@PersistenceContext( unitName = "EventPlaner_V2")

/*????????????????????????????????????
 * 
 * Question:
 * Needed as a Table or already saved in AdditionalInformation? 
 * 
 * ???????????????????????????????????
 */

public class PersonalInformation implements java.io.Serializable {

	private int[] selectValueIds; //IDs of the selected values in dropdown menus
	private String[] textValues;
	private int[] checkboxValueIds; //IDs of the checked checkboxes
	private Date[] dateValues;
	private Participant participant;
	private Event event;
	private AdditionalInformation additionalInformation;

	protected PersonalInformation(){
		
	}
	
//	public PersonalInformation(){ }
	
	@OneToOne(cascade = {CascadeType.ALL})
	public AdditionalInformation getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(AdditionalInformation additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	
	@Id
	public String getEmail() {
		return participant.getEMail();
	}
	public void setEmail(String email) {
		participant.setEMail(email);
	}
	
	@Id
	public String getLink() {
		return event.getLink();
	}
	public void setLink(String link) {
		event.setLink(link);
	}
	
	@Id
	public int getId() {
		return additionalInformation.getId();
	}
	public void setId(int id) {
		additionalInformation.setId(id);
	}
	
	
	public int[] getSelectValueIds() {
		return selectValueIds;
	}
	public void setSelectValueIds(int[] selectValueIds) {
		this.selectValueIds = selectValueIds;
	}
	public String[] getTextValues() {
		return textValues;
	}
	public void setTextValues(String[] textValues) {
		this.textValues = textValues;
	}
	public int[] getCheckboxValueIds() {
		return checkboxValueIds;
	}
	public void setCheckboxValueIds(int[] checkboxValueIds) {
		this.checkboxValueIds = checkboxValueIds;
	}
	public Date[] getDateValues() {
		return dateValues;
	}
	public void setDateValues(Date[] dateValues) {
		this.dateValues = dateValues;
	}

}