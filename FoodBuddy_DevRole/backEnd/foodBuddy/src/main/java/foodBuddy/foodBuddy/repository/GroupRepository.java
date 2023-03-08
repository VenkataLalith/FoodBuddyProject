package foodBuddy.foodBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import foodBuddy.foodBuddy.entity.GroupEntity;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
	
	@Query("SELECT groupName FROM GroupEntity WHERE groupId = ?1")
    String findGroupById(String groupId);
}

