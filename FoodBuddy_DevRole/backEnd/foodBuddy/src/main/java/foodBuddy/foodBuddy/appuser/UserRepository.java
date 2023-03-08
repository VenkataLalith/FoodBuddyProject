package foodBuddy.foodBuddy.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
@Transactional
public interface UserRepository extends JpaRepository<AppUser, Long>  {
    Optional<AppUser> findByEmail(String email);
    
    @Query("SELECT password FROM AppUser WHERE email = ?1")
    String findPasswordByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE AppUser SET groupName = :groupName WHERE email = :userName")
    void UpdateGroupName(@Param("groupName") String groupName,@Param("userName") String userName );
    
}
