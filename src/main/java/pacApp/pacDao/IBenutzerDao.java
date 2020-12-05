package pacApp.pacDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pacApp.pacModel.Benutzer;

@Repository
public interface IBenutzerDao extends JpaRepository<Benutzer, Long> {
	List<Benutzer> findAllById(long id);
}
