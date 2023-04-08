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
            System.out.println("inside createGroup "+appGroup);
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
        System.out.println("GroupJoinRequest "+request);
        GroupJoinResponse response = new GroupJoinResponse();
        try {
            boolean groupExists = groupRepository.findGroupByCode(request.getGroupCode()).isBlank();
            System.out.println("groupExists "+ groupExists);
            String groupName = groupRepository.findGroupByCode(request.getGroupCode());
            String userName = request.getUserName();
            String groupCode = request.getGroupCode();
            if (!groupExists){
                userRepository.UpdateGroupName(groupCode,groupName,userName);
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
            boolean groupExists = groupRepository.findGroupByCode(groupCode).isBlank();
            if (!groupExists){
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
        String groupExists = groupRepository.findGroupByCode(groupCode);
        if (groupExists!=null){
            return groupCode = generateCode();
        }
        else {
            return groupCode;
        }

    }

    public LeaveGroupResponse leaveGroup(LeaveGroupRequest request) {
        String groupCode = request.getGroupCode();
        String userEmail = request.getUserName();
        Boolean groupExists = groupRepository.findGroupByCode(groupCode).isBlank();
        String groupName = groupRepository.findGroupByCode(groupCode);
        System.out.println(groupExists);
        System.out.println(groupName);
        LeaveGroupResponse leaveGroupResponse = new LeaveGroupResponse();
        if (!groupExists){
            groupCode="";
            groupName="";
            userRepository.UpdateGroupName(groupCode,groupName,userEmail);
            leaveGroupResponse.setMessage("User Left the group");
            leaveGroupResponse.setStatus("Success");
        }
        else {
            leaveGroupResponse.setMessage("Does not belong to any group");
            leaveGroupResponse.setStatus("failure");
        }
        return leaveGroupResponse;
    }
}
