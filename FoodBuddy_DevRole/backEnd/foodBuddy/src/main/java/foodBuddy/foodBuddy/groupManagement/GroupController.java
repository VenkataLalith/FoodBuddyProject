package foodBuddy.foodBuddy.groupManagement;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/groupApi")
@AllArgsConstructor
public class GroupController {
    private GroupServices groupServices;
    @PostMapping("/Create")
    public GroupCreationResponse createGroup(@RequestBody GroupCreationRequest request){
        return groupServices.createGroup(request);
    }
    @PostMapping("/Join")
    public GroupJoinResponse joinGroup(@RequestBody GroupJoinRequest request){
        GroupJoinResponse response = groupServices.joinGroup(request);
        return response;
    }
    @GetMapping("/view")
    public ViewGroupUsersResponse viewGroup(@RequestParam(value = "groupCode") String groupCode){
        ViewGroupUsersResponse response = groupServices.viewGroup(groupCode);
        return response;
    }
}
