package pacApp.pacModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Benutzer")
public class Benutzer extends DbBaseModel {
	
	@Column(name = "Full_Name", length = 64, nullable = false)
	private String fullName;

	@Column(name = "todoListeId")
	private Long todoListeId;
	
	public String getName() {
		return fullName;
	}

	public void setName(String fullName) {
		this.fullName = fullName;
	}
}
