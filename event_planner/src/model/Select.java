package model;

//contains all possible values for additional select/dropdown fields

public class Select extends Information {

	private String[] values;

	public String[] getValues() {
		return this.values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	/**
	 * 
	 * @param description -> inherited from Information
	 * @param values ->all possible values
	 */
	public Select(String description, String[] values) {
		super(description);
		this.values=values;
	}

}