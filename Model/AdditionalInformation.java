package Model;

//contains all additional information for one application

public class AdditionalInformation {

	private Select[] selectOptions;
	private Checkbox[] checkboxOptions;
	private Text[] textOptions;
	private DateInformation[] dateOptions;

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

	public DateInformation[] getDateOptions() {
		return this.dateOptions;
	}

	public void setDateOptions(DateInformation[] dateOptions) {
		this.dateOptions = dateOptions;
	}

}