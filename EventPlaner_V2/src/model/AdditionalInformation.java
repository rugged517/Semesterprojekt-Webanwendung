package model;

import javax.persistence.*;
//contains all additional information for one application

@Entity
@Table (name = "AdditionalInformation")
@PersistenceContext (unitName = "eventPlanner")
public class AdditionalInformation implements java.io.Serializable {

	private Select[] selectOptions;
	private Checkbox[] checkboxOptions;
	private Text[] textOptions;
	private DateInformation[] dateOptions;
	private int id;
	private Event event;
	private EntityManager em;
	
	protected AdditionalInformation(){
		
	}
	
	public AdditionalInformation(int Id, Select[] selectOptions, Checkbox[] checkboxOptions, Text[] textOptions, DateInformation[] dateOptions){
		AdditionalInformation additional = (AdditionalInformation) em.find(AdditionalInformation.class, id);
		this.selectOptions = selectOptions;
		this.checkboxOptions = checkboxOptions;
		this.textOptions = textOptions;
		this.dateOptions = dateOptions;
		em.persist(additional);
	}
	
	@OneToOne(mappedBy = "additionalInformation")
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@OneToOne(cascade = {CascadeType.ALL})
	public Select[] getSelectOptions() {
		return this.selectOptions;
	}

	public void setSelectOptions(Select[] selectOptions) {
		this.selectOptions = selectOptions;
	}

	@OneToOne(cascade = {CascadeType.ALL})
	public Checkbox[] getCheckboxOptions() {
		return this.checkboxOptions;
	}

	public void setCheckboxOptions(Checkbox[] checkboxOptions) {
		this.checkboxOptions = checkboxOptions;
	}

	@OneToOne(cascade = {CascadeType.ALL})
	public Text[] getTextOptions() {
		return this.textOptions;
	}

	public void setTextOptions(Text[] textOptions) {
		this.textOptions = textOptions;
	}

	@OneToOne(cascade = {CascadeType.ALL})
	public DateInformation[] getDateOptions() {
		return this.dateOptions;
	}

	public void setDateOptions(DateInformation[] dateOptions) {
		this.dateOptions = dateOptions;
	}

}