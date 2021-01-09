package pacApp.pacController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pacApp.pacDao.ITodoListeDao;
import pacApp.pacModel.Benutzer;
import pacApp.pacModel.Todo;
import pacApp.pacModel.TodoListe;
import pacApp.pacModel.pacResponse.GenericResponse;

@RestController
public class TodoListeController {

	@Autowired
	private ITodoListeDao todoListeDao;
	
	@GetMapping("/create/emptytodoliste/{titel}")
	public ResponseEntity<GenericResponse> createEmptyTodoListe(@PathVariable(value="titel") String titel){
		GenericResponse response = null;
		try {
			TodoListe todoListeNeu = new TodoListe();
			todoListeNeu.setTitel(titel);
			TodoListe returnTodoListe = todoListeDao.save(todoListeNeu);
			response = new GenericResponse(HttpStatus.OK.value(), "TodoListe mit der id: "+returnTodoListe.getId()+" wurde hinzugefügt!");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			response = new GenericResponse(HttpStatus.OK.value(), "TodoListe konnte nicht hinzugefügt werden!");
			new ResponseEntity<>(response, HttpStatus.OK); 
		}
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	
	@GetMapping("/delete/todoListe/{id}")
	public ResponseEntity<GenericResponse> delteTodoList(@PathVariable(value="id") String id){
		GenericResponse response = null; 
		try {
			todoListeDao.deleteById(Long.parseLong(id));
			response = new GenericResponse(HttpStatus.OK.value(), "TodoListe wurde gelöscht!");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response = new GenericResponse(HttpStatus.OK.value(), "TodoListe wurde nicht gelöscht!");			
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//Todo add Benutzer to TodoListe
		
	//Todo add Todo to TodoListe
	
	@GetMapping("/get/all/todoListe")
	public ResponseEntity<List<TodoListe>> getTodoList(){
		try {
			List<TodoListe> todoListe = todoListeDao.findAll();
			return new ResponseEntity<List<TodoListe>>(todoListe, HttpStatus.OK);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}
	}
	
	@GetMapping("/get/todoListe/{id}")
	public ResponseEntity<TodoListe> getTodoListById(@PathVariable(value="id") String id){
		try {
			TodoListe todoListe = todoListeDao.findById(Long.parseLong(id));
			return new ResponseEntity<TodoListe>(todoListe, HttpStatus.OK);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new TodoListe(), HttpStatus.OK);		
		}
	}
	
	@GetMapping("/get/todoListeByBenutzerId/{id}")
	public ResponseEntity<TodoListe> getTodoListByBenutzerId(@PathVariable(value="id") String id){
		try {
			List<TodoListe> tl  = todoListeDao.findAll(); 
			
			for (TodoListe t : tl) {
				List<Benutzer> bl = t.getBenutzerList();
				for (Benutzer b : bl) {
					if (b.getId() == Long.parseLong(id))
							return new ResponseEntity<TodoListe>(t, HttpStatus.OK);
				}
				
			} 
			return new ResponseEntity<TodoListe>(HttpStatus.NOT_FOUND);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new TodoListe(), HttpStatus.INTERNAL_SERVER_ERROR);		
		}
	}
	
	@PostMapping("/create/todoliste")
	public ResponseEntity<GenericResponse> createTodoListe(@RequestBody TodoListe todoListe){
		GenericResponse response = null;
		try {
			todoListeDao.save(todoListe);
			response = new GenericResponse(HttpStatus.OK.value(), "TodoListe wurde hinzugefügt!");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			response = new GenericResponse(HttpStatus.OK.value(), "TodoListe konnte nicht hinzugefügt werden!");
			new ResponseEntity<>(response, HttpStatus.OK); 
		}
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	
	@GetMapping("/create/exampleTodoListe")
	public boolean createTestTodoListe() {		
		TodoListe tListe = new TodoListe();
		Benutzer ben1 = new Benutzer(); 
		ben1.setName("TestUser1");
		ben1.setPassword("pwd1");
		Benutzer ben2 = new Benutzer(); 
		ben2.setName("TestUser2");
		ben2.setPassword("pwd2");
		
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
		return true;
	}
	
	@GetMapping("/getTodoListeJsonStruct")
	public ResponseEntity<String> getBenutzerJsonStruct() {		
		ObjectMapper mapper = new ObjectMapper();
		try {
		  String json = mapper.writeValueAsString(new TodoListe());
		  System.out.println("ResultingJSONstring = " + json);
		  return new ResponseEntity<>(json, HttpStatus.OK) ;
		} catch (JsonProcessingException e) {
		   e.printStackTrace();
		}		
		 return new ResponseEntity<>(new String("Error"), HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
}
 