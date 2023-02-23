package foodBuddy.foodBuddy.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<AppUser, Long>  {
    Optional<AppUser> findByEmail(String email);
    
    @Query("SELECT password FROM AppUser WHERE email = ?1")
    String findPasswordByEmail(String email);
    
    
}
