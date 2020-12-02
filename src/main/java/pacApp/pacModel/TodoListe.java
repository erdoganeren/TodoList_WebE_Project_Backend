package pacApp.pacModel;

import java.util.List;

public class TodoListe extends DbBaseModel {

	private List<Benutzer> benutzerList; 
	private List<Todo> todoList;
	
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
