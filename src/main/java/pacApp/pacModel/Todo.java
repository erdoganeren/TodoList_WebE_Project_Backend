package pacApp.pacModel;

public class Todo extends DbBaseModel {

	private String titel; 
	private String text; 
	private boolean isErledigt;
	
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isErledigt() {
		return isErledigt;
	}
	public void setErledigt(boolean isErledigt) {
		this.isErledigt = isErledigt;
	}
	
}
