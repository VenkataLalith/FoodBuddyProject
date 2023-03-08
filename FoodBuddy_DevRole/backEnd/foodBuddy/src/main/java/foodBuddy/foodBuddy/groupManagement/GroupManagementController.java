package foodBuddy.foodBuddy.groupManagement;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/group")
@AllArgsConstructor
public class GroupManagementController {
	private GroupManagementService groupManagementService;
    @PostMapping
    public String createGroup(@RequestBody GroupCreationRequest request){
        return groupManagementService.createGroup(request);
    }
}
