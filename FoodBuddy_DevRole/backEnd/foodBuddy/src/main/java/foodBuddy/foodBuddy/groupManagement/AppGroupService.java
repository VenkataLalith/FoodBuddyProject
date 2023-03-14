package foodBuddy.foodBuddy.groupManagement;

import foodBuddy.foodBuddy.appuser.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppGroupService {
    @Autowired
    private final GroupRepository groupRepository;
    @Autowired
    private final UserRepository userRepository;


    public GroupCreationResponse CreateGroup(AppGroup appGroup){
        GroupCreationResponse response = new GroupCreationResponse();
        boolean groupExists = groupRepository.findByGroupName(appGroup.getGroupName()).isPresent();
        if (!groupExists){
            groupRepository.save(appGroup);
            /*
            Need to add the user who is creating the group into the group automatically.
             */
            response.setMessage("Group created successfully");
            response.setStatus("success");
            return response;
        }
        else {
            response.setMessage("GroupName Exists");
            response.setStatus("failure");
            return response;
        }
    }

    public GroupJoinResponse joinGroup(GroupJoinRequest request) {
        GroupJoinResponse response = new GroupJoinResponse();
        try {
            boolean groupExists = groupRepository.findGroupByCode(request.getGroupCode()).isBlank();
            String userName = request.getUserName();
            String groupName = groupRepository.findGroupByCode(request.getGroupCode());
            if (!groupExists){
                userRepository.UpdateGroupName(groupName,userName);
                response.setMessage("Joined successfully");
                response.setStatus("success");
                return response;
            }
            else {
                response.setMessage("please verify the groupCode: Unable to join");
                response.setStatus("failure");
                return response;
            }
        }
        catch (NullPointerException e){
            response.setMessage("please verify the groupCode: Unable to join");
            response.setStatus("failure");
            return response;
        }

    }
}
