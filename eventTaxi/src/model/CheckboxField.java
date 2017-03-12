package model;

//contains settings and values for additional checkboxes

public class CheckboxField extends SelectField {

	private int min;
	private int max;
	
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}

	/**
	 * 
	 * @param description - inherited from Information
	 * @param values - inherited from Select (all possible values)
	 */
	public CheckboxField(String link, String description, Boolean isRequired, String[] options, int min,
			int max) {		
		super(link, description, isRequired, options);

			//construcktor of SelectFields sets type=select
			updateType("checkbox");
			
			this.setMin(min);
			this.setMax(max);
			
			insertSetting("min", min);
			insertSetting("max", max);
		
	}
	public CheckboxField(int id) {		
		super(id);
		
		this.min = Integer.parseInt(getSettingsOption("min"));
		this.max = Integer.parseInt(getSettingsOption("max"));
		
	}

}