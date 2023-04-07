package foodBuddy.foodBuddy.groupManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor
public class GroupServices {
    @Autowired
    private static AppGroupService appGroupService;

    public GroupServices(AppGroupService appGroupService) {
        this.appGroupService = appGroupService;
    }

    public static GroupCreationResponse createGroup(GroupCreationRequest request) {
        AppGroup appGroup = new AppGroup(request.getGroupName(),request.getGroupCode());
        System.out.println("appGroup "+ appGroup);
        /*
        Generate a random groupCode, instead of getting from user.
         */
        String groupCode = appGroupService.generateCode();// validating for unique groupCode

        appGroup.setGroupCode(groupCode);
        GroupCreationResponse creationReq = appGroupService.CreateGroup(appGroup);
        if(creationReq.getStatus().equalsIgnoreCase("success")) {
            GroupJoinRequest joinReq = new GroupJoinRequest(groupCode, request.getUserName());
            GroupJoinResponse response = appGroupService.joinGroup(joinReq);
            creationReq.setGroupCode(groupCode);
            if (response.getStatus().equalsIgnoreCase("failure")){
                creationReq.setStatus("joinFailed");
            }
        }
        return creationReq;
    }

    public GroupJoinResponse joinGroup(GroupJoinRequest request) {
        GroupJoinResponse response = appGroupService.joinGroup(request);
        return response;
    }


    public ViewGroupUsersResponse viewGroup(String groupCode) {
        ViewGroupUsersResponse response = appGroupService.findGroupUsers(groupCode);
        return response;
    }


    public LeaveGroupResponse leaveGroup(LeaveGroupRequest request) {
        LeaveGroupResponse response = appGroupService.leaveGroup(request);
        return response;
    }
}
