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

    public static String createGroup(GroupCreationRequest request) {
        AppGroup appGroup = new AppGroup(request.getGroupName(),request.getGroupCode());
        String data = appGroupService.CreateGroup(appGroup);
        return data;
    }

    public String joinGroup(GroupJoinRequest request) {
        String data =appGroupService.joinGroup(request);
        return data;
    }
}
