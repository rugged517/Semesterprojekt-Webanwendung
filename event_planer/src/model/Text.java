package model;

//contains settings for textfields

public class Text extends Information {

	private int minLength = 0;
	private int maxLength = 0;
	private boolean isTextfield = false;

	public int getMinLength() {
		return this.minLength;
	}

	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	public int getMaxLength() {
		return this.maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	
	public boolean isTextfield() {
		return this.isTextfield;
	}

	public void SetIsTextfield(boolean isTextfield) {
		this.isTextfield = isTextfield;
	}

	public Text(String description) {
		super(description);
	}
	
}