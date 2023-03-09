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


    public String CreateGroup(AppGroup appGroup){
        boolean groupExists = groupRepository.findByGroupName(appGroup.getGroupName()).isPresent();
        if (!groupExists){
            groupRepository.save(appGroup);
            /*
            Need to add the user who is creating the group into the group automatically.
             */
            return "Group Created Successfully";
        }
        else {
            return "GroupName Exists";
        }
    }

    public String joinGroup(GroupJoinRequest request) {
        try {
            boolean groupExists = groupRepository.findGroupByCode(request.getGroupCode()).isBlank();
            String userName = request.getUserName();
            String groupName = groupRepository.findGroupByCode(request.getGroupCode());
            if (!groupExists){
                userRepository.UpdateGroupName(groupName,userName);
                return "Joined successfully";
            }
            else {
                return "please verify the groupCode: Unable to join";
            }
        }
        catch (NullPointerException e){
            return "please verify the groupCode: Unable to join\"";
        }

    }
}
