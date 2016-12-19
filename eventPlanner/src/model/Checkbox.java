package model;

//contains settings and values for additional checkboxes

public class Checkbox extends Select {

	private int maxOptions = 1;
	private int minOptions = 1;

	public int getMaxOptions() {
		return maxOptions;
	}

	public void setMaxOptions(int maxOptions) {
		this.maxOptions = maxOptions;
	}

	public int getMinOptions() {
		return minOptions;
	}

	public void setMinOptions(int minOptions) {
		this.minOptions = minOptions;
	}
	
	/**
	 * 
	 * @param description -> inherited from Information
	 * @param values -> inherited from Select (all possible values)
	 */
	 public Checkbox(String description, String[] values) {
		super(description, values);
	}
}