package Hilfsklassen;

import model.AdditionalInformation;

public class InformationPK {
	private AdditionalInformation additionalInformation;
	private String settings;
	private String value;
	private int id = additionalInformation.getId();	

	public InformationPK(int Id, String value, String settings) {
		this.id = id;
		this.value = value;
		this.settings = settings;
	}
	
	public InformationPK() {
		
	}
	public String getSettings() {
		return settings;
	}

	public void setSettings(String settings) {
		this.settings = settings;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
		else if(!(refObj instanceof InformationPK))
			return false;
		InformationPK refInformationPK = (InformationPK) refObj;
		return getId() == refInformationPK.getId() && value == refInformationPK.value && 
				settings == refInformationPK.settings;
	}
	
	public int hashCode(){
		return (int)(additionalInformation.getId() ^ value.hashCode() ^ settings.hashCode());
	}

}
