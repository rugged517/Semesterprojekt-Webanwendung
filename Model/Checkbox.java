package Model;

//contains settings and values for additional checkboxes

public class Checkbox extends Select {

	private int maxOptions = 1;
	private int minOptions = 1;

	/**
	 * 
	 * @param description -> inherited from Information
	 * @param values -> inherited from Select (all possible values)
	 */
	 public Checkbox(string description, string[] values) {
		super(string description, string[] values);
		throw new UnsupportedOperationException();
	}
}