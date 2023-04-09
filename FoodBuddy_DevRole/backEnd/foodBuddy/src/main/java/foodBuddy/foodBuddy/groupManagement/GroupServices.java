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
}
