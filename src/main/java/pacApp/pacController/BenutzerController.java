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

import pacApp.pacDao.IBenutzerDao;
import pacApp.pacModel.Benutzer;
import pacApp.pacModel.pacResponse.GenericResponse;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
public class BenutzerController {

	@Autowired
	private IBenutzerDao benutzerDao;
	
	@CrossOrigin(origins = "*")
	@PostMapping("/create/benutzer")
	public ResponseEntity<GenericResponse> createTodoListBenutzer(@RequestBody Benutzer ben) {
		GenericResponse response = null;
		try {
			benutzerDao.save(ben);
			response = new GenericResponse(HttpStatus.OK.value(), "Benutzer hinzgefügt!");
		} catch (IllegalArgumentException e) {
			response = new GenericResponse(HttpStatus.BAD_REQUEST.value(), "Benutzer wurde nicht hinzgefügt!");
			e.printStackTrace();
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/get/benutzer/{id}")
	public ResponseEntity<Benutzer> getTodoListeBenutzer(@PathVariable(value="id") String id) {
		try {
			Benutzer ben = benutzerDao.findById(Long.parseLong(id)); 
			return new ResponseEntity<Benutzer>(ben, HttpStatus.OK);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Benutzer>(new Benutzer(), HttpStatus.BAD_REQUEST);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/get/all/benutzer")
	public ResponseEntity<List<Benutzer>> getAllBenutzer() {
		try {
			List<Benutzer> benList = benutzerDao.findAll();
			return new ResponseEntity<List<Benutzer>>(benList, HttpStatus.OK);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Benutzer>>(new ArrayList<Benutzer>(), HttpStatus.BAD_REQUEST);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/get/all/benutzer/{id}")
	public ResponseEntity<List<Benutzer>> getAllBenutzerByTodoListeId(@PathVariable(value="id") String id) {
		try {
			List<Benutzer> benList = benutzerDao.findByTodoListeId(Long.parseLong(id)); 
			return new ResponseEntity<List<Benutzer>>(benList, HttpStatus.OK);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Benutzer>>(new ArrayList<Benutzer>(), HttpStatus.BAD_REQUEST);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/delete/benutzer/{id}")
	public ResponseEntity<GenericResponse> deleteTodoListeBenutzer(@PathVariable(value="id") String id) {
		GenericResponse response = null;
		try {
			benutzerDao.deleteById(Long.parseLong(id));
			response = new GenericResponse(HttpStatus.OK.value(), "Benutzer wurde gelöscht!");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response = new GenericResponse(HttpStatus.OK.value(), "Benutzer wurde nicht gelöscht!");			
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/getBenutzerJsonStruct")
	public String getBenutzerJsonStruct() {		
		ObjectMapper mapper = new ObjectMapper();
		try {
		  String json = mapper.writeValueAsString(new Benutzer());
		  System.out.println("ResultingJSONstring = " + json);
		  return json;
		} catch (JsonProcessingException e) {
		   e.printStackTrace();
		}		
		return "error";
	}
	
}
