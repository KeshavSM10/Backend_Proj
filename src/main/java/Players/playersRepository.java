package Players;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface playersRepository extends JpaRepository<playersRegistered, Integer> {

	@Query("SELECT MAX(p.ID) FROM playersRegistered p")
	int getLastID();

}
