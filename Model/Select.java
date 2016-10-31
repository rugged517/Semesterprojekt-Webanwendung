package Model;

//contains all possible values for additional select/dropdown fields

public class Select extends Information {

	private string[] values;

	public string[] getValues() {
		return this.values;
	}

	public void setValues(string[] values) {
		this.values = values;
	}

	/**
	 * 
	 * @param description -> inherited from Information
	 * @param values ->all possible values
	 */
	public Select(string description, string[] values) {
		// TODO - implement Select.Select
		throw new UnsupportedOperationException();
	}

}