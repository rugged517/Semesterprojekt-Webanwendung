package Model;

public class AdditionalInformation {

	private Select[] selectOptions;
	private Checkbox[] checkboxOptions;
	private Text[] textOptions;
	private Date[] dateOptions;

	public Select[] getSelectOptions() {
		return this.selectOptions;
	}

	public void setSelectOptions(Select[] selectOptions) {
		this.selectOptions = selectOptions;
	}

	public Checkbox[] getCheckboxOptions() {
		return this.checkboxOptions;
	}

	public void setCheckboxOptions(Checkbox[] checkboxOptions) {
		this.checkboxOptions = checkboxOptions;
	}

	public Text[] getTextOptions() {
		return this.textOptions;
	}

	public void setTextOptions(Text[] textOptions) {
		this.textOptions = textOptions;
	}

	public Date[] getDateOptions() {
		return this.dateOptions;
	}

	public void setDateOptions(Date[] dateOptions) {
		this.dateOptions = dateOptions;
	}

}