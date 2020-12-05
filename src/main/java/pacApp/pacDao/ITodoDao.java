package pacApp.pacDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pacApp.pacModel.Benutzer;
import pacApp.pacModel.Todo;

@Repository
public interface ITodoDao extends JpaRepository<Todo, Long> {
	List<Todo> findAllById(long id);
	Todo findById(long id);
	List<Todo> findByTodoListeId(long id);
}
