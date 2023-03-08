package foodBuddy.foodBuddy.groupManagement;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/groupApi")
@AllArgsConstructor
public class GroupController {
    private GroupServices groupServices;
    @PostMapping("/Create")
    public String createGroup(@RequestBody GroupCreationRequest request){
        return groupServices.createGroup(request);
    }
    @PostMapping("/Join")
    public String joinGroup(@RequestBody GroupJoinRequest request){
        String data = groupServices.joinGroup(request);
        return data;
    }
}
