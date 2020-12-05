package pacApp.pacDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pacApp.pacModel.TodoListe;

@Repository
public interface ITodoListeDao extends JpaRepository<TodoListe, Long> {
	List<TodoListe> findAllById(long id);
}