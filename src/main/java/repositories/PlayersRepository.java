package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersRepository extends JpaRepository<PlayersRegistered, Integer> {

	@Query("SELECT MAX(p.ID) FROM PlayersRegistered p")
	int getLastID();

}
