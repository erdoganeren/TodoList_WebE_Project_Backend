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

import pacApp.pacDao.ITodoDao;
import pacApp.pacModel.Todo;
import pacApp.pacModel.pacResponse.GenericResponse;

@RestController
public class TodoController {

	@Autowired
	private ITodoDao todoDao;
	
	@PostMapping("/create/todo")
	public ResponseEntity<GenericResponse> createTodoListTodo(@RequestBody Todo todo) {
		GenericResponse response = null;
		try {
			todoDao.save(todo);
			response = new GenericResponse(HttpStatus.OK.value(), "Todo hinzgefügt!");
		} catch (IllegalArgumentException e) {
			response = new GenericResponse(HttpStatus.BAD_REQUEST.value(), "Todo wurde nicht hinzgefügt!");
			e.printStackTrace();
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/get/todo/{id}")
	public  ResponseEntity<Todo> getTodoListeTodo(@PathVariable(value="id") String id) {
		try {
			Todo todo = todoDao.findById(Long.parseLong(id)); 
			return new ResponseEntity<Todo>(todo, HttpStatus.OK);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Todo>(new Todo(), HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/get/all/todo/{id}")
	public ResponseEntity<List<Todo>> getAllTodoByTodoListeId(@PathVariable(value="id") String id) {
		try {
			List<Todo> todoList = todoDao.findByTodoListeId(Long.parseLong(id)); 
			return new ResponseEntity<List<Todo>>(todoList, HttpStatus.OK);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Todo>>(new ArrayList<Todo>(), HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/delete/todo/{id}")
	public ResponseEntity<GenericResponse> deleteTodoListeTodo(@PathVariable(value="id") String id) {
		GenericResponse response = null;
		try {
			todoDao.deleteById(Long.parseLong(id));
			response = new GenericResponse(HttpStatus.OK.value(), "Todo wurde gelöscht!");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response = new GenericResponse(HttpStatus.OK.value(), "Todo wurde nicht gelöscht!");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getTodoJsonStruct")
	public String getTodoJsonStruct() {		
		ObjectMapper mapper = new ObjectMapper();
		try {
		  String json = mapper.writeValueAsString(new Todo());
		  return json;
		} catch (JsonProcessingException e) {
		   e.printStackTrace();
		}		
		return "error";
	}
}
