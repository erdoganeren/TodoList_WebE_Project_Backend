package pacApp.pacModel;

import java.util.List;

public class TodoListe extends DbBaseModel {

	private String titel; 
	private List<Benutzer> benutzerList; 
	private List<Todo> todoList;
	
	
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public List<Benutzer> getBenutzerList() {
		return benutzerList;
	}
	public void setBenutzerList(List<Benutzer> benutzerList) {
		this.benutzerList = benutzerList;
	}
	public List<Todo> getTodoList() {
		return todoList;
	}
	public void setTodoList(List<Todo> todoList) {
		this.todoList = todoList;
	}
	
	
}
