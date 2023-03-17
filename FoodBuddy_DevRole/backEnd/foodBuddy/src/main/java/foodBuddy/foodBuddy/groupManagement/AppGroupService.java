package foodBuddy.foodBuddy.groupManagement;

import foodBuddy.foodBuddy.appuser.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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
            String groupCode = request.getGroupCode();
            if (!groupExists){
                userRepository.UpdateGroupName(groupCode,userName);
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

    public ViewGroupUsersResponse findGroupUsers(String groupCode) {
        ViewGroupUsersResponse response = new ViewGroupUsersResponse();
        try {
            boolean groupExists = true;//groupRepository.findGroupByCode(groupCode).isBlank();
            if (groupExists){
                response.setGroupUsersList(userRepository.findUsersByGroupCode(groupCode));
                response.setMessage("Found Members");
                response.setStatus("success");
                return response;
            }
            else {
                response.setGroupUsersList(null);
                response.setMessage("Invalid GroupCode");
                response.setStatus("failure");
                return response;
            }
        }
        catch (NullPointerException e){
            response.setGroupUsersList(null);
            response.setMessage("please verify the groupCode");
            response.setStatus("failure");
            return response;
        }

    }
    public String generateCode(){
        Random rand = new Random();
        int randomNumber = rand.nextInt(900000) + 100000;
        String groupCode = Integer.toString(randomNumber);
        boolean groupExists = groupRepository.findGroupByCode(groupCode).isBlank();
        if (groupExists){
            return groupCode = generateCode();
        }
        else {
            return groupCode;
        }

    }
}
