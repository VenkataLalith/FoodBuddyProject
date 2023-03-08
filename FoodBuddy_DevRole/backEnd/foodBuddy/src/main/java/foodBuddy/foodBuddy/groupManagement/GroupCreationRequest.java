package foodBuddy.foodBuddy.groupManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class GroupCreationRequest {
	public String groupName;
	public String groupId;
	
}
