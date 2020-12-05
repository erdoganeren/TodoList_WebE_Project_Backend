package pacApp.pacController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pacApp.pacDao.IBenutzerDao;
import pacApp.pacDao.ITodoListeDao;
import pacApp.pacModel.Benutzer;
import pacApp.pacModel.Todo;
import pacApp.pacModel.TodoListe;

@RestController
public class TestController {

	@Autowired
	private IBenutzerDao benutzerDao;
	
	@Autowired
	private ITodoListeDao todoListeDao;
	
	@GetMapping("/createB")
	public boolean createTestBenutzer() {		
		Benutzer ben = new Benutzer(); 
		ben.setName("TestUser");
		benutzerDao.save(ben);		
		return false;
	}
	
	@GetMapping("/createTodoListe")
	public boolean createTestTodoListe() {		
		TodoListe tListe = new TodoListe();
		Benutzer ben1 = new Benutzer(); 
		ben1.setName("TestUser1");
		Benutzer ben2 = new Benutzer(); 
		ben2.setName("TestUser2");
		
		List<Benutzer> benList = new ArrayList<Benutzer>();
		benList.add(ben1);
		benList.add(ben2);
		
		Todo todo1 = new Todo();
		todo1.setTitel("TestTitel1");
		todo1.setText("Hallo das ist ein Test");
		List<Todo> todoList = new ArrayList<Todo>();
		tListe.setTitel("Test TodoListe Titel");
		todoList.add(todo1);
		tListe.setBenutzerList(benList);
		tListe.setTodoList(todoList);
		todoListeDao.save(tListe);
		return false;
	}
	
	@GetMapping("/deleteTodoLists")
	public boolean deleteTestTodoListe() {	
		todoListeDao.deleteAll();
		return false;
	}
	
}
