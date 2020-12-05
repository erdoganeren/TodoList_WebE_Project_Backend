package pacApp.pacModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Todo")
public class Todo extends DbBaseModel {
	
	@Column(name = "titel", length = 64, nullable = false)
	private String titel; 

	@Column(name = "text", length = 64, nullable = false)
	private String text; 

	@Column(name = "isErledigt", nullable = false)
	private boolean isErledigt;

	@Column(name = "todoListeId")
	private Long todoListeId;
	
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
