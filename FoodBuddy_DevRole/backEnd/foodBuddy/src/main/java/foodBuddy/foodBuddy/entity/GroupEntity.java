package foodBuddy.foodBuddy.entity;

import foodBuddy.foodBuddy.appuser.AppUserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String groupId;
    private String groupName;
    // getters and setters
    
    public GroupEntity(String groupId, String groupName) {
    	this.groupId = groupId;
    	this.groupName = groupName;
    }
}
