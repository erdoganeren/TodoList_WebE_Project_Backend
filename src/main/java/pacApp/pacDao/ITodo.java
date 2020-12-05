package pacApp.pacDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pacApp.pacModel.Todo;

@Repository
public interface ITodo extends JpaRepository<Todo, Long> {
	List<Todo> findAllById(long id);
}
