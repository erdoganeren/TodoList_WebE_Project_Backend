package pacApp.pacModel;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * https://stackabuse.com/a-guide-to-jpa-with-hibernate-relationship-mapping/
 */

@Entity
@Table(name = "TodoListe")
public class TodoListe extends DbBaseModel {

	@Column(name = "titel", length = 64, nullable = false)
	private String titel; 
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "todoListeId", referencedColumnName ="id")
	private List<Benutzer> benutzerListArray; 
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "todoListeId", referencedColumnName ="id")
	private List<Todo> todoListArray;
	
	
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public List<Benutzer> getBenutzerList() {
		return benutzerListArray;
	}
	public void setBenutzerList(List<Benutzer> benutzerListArray) {
		this.benutzerListArray = benutzerListArray;
	}
	public List<Todo> getTodoList() {
		return todoListArray;
	}
	public void setTodoList(List<Todo> todoListArray) {
		this.todoListArray = todoListArray;
	}
		
}
