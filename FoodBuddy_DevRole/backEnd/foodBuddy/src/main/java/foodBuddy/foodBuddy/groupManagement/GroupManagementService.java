package foodBuddy.foodBuddy.groupManagement;

import org.springframework.stereotype.Service;

import foodBuddy.foodBuddy.entity.GroupEntity;
import foodBuddy.foodBuddy.repository.GroupRepository;


@Service
public class GroupManagementService {
	private final GroupRepository groupRepository;
	
	public GroupManagementService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
	
	public String createGroup(GroupCreationRequest request) {
		GroupEntity groupEntity = new GroupEntity(request.groupId, request.groupName);
		String groupName = groupRepository.findGroupById(groupEntity.getGroupId());
	        if (!(groupName.isEmpty())){
	            throw new IllegalStateException("Group Code Exists");
	        }
	        
	        groupRepository.save(groupEntity);
	        return "done";
    }

}
