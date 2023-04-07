package foodBuddy.foodBuddy.groupManagementTests;


import foodBuddy.foodBuddy.appuser.UserRepository;
import foodBuddy.foodBuddy.groupManagement.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class GroupServicesTests {

    private AppGroupService appGroupService;

    private GroupServices groupServices;

    private GroupRepository groupRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        appGroupService = new AppGroupService(groupRepository, userRepository);
        groupServices = new GroupServices(appGroupService);
    }

    @Test
    public void testCreateGroup_success() {
//        String groupName="Demo Group";
//        String groupCode="1234";
//        String userName="user@email.com";
//        GroupCreationRequest request = new GroupCreationRequest(groupName,groupCode,userName);
//        AppGroup appGroup = new AppGroup(groupName,groupCode);
//
//        GroupJoinRequest joinRequest = new GroupJoinRequest(groupCode, userName);
//        GroupJoinResponse joinResponse = new GroupJoinResponse();
//        joinResponse.setStatus("success");
//        GroupCreationResponse creationResponse = new GroupCreationResponse();
//        creationResponse.setStatus("success");

//        when(appGroupService.generateCode()).thenReturn(groupCode);
//        when(groupRepository.findGroupByCode(groupCode)).thenReturn(" ");
//        when(appGroupService.CreateGroup(appGroup)).thenReturn(creationResponse);
//        when(appGroupService.joinGroup(joinRequest)).thenReturn(joinResponse);
//
//        GroupCreationResponse response = groupServices.createGroup(request);
//        System.out.println(" "+response.getStatus()+" "+response.getMessage());
//        assertEquals("success", response.getStatus());
    }

//    @Test
//    public void testCreateGroup_joinFailed() {
//        GroupCreationRequest request = new GroupCreationRequest("Group1", "ABC");
//        AppGroup appGroup = new AppGroup(request.getGroupName(), request.getGroupCode());
//        GroupCreationResponse creationResponse = new GroupCreationResponse("success");
//        GroupJoinRequest joinRequest = new GroupJoinRequest(request.getGroupCode(), "User1");
//        GroupJoinResponse joinResponse = new GroupJoinResponse("failure");
//
//        when(appGroupService.CreateGroup(appGroup)).thenReturn(creationResponse);
//        when(appGroupService.joinGroup(joinRequest)).thenReturn(joinResponse);
//
//        GroupCreationResponse response = groupServices.createGroup(request);
//
//        assertEquals("joinFailed", response.getStatus());
//    }
//
//    @Test
//    public void testJoinGroup() {
//        GroupJoinRequest request = new GroupJoinRequest("ABC", "User1");
//        GroupJoinResponse response = new GroupJoinResponse("success");
//
//        when(appGroupService.joinGroup(request)).thenReturn(response);
//
//        GroupJoinResponse joinResponse = groupServices.joinGroup(request);
//
//        assertEquals("success", joinResponse.getStatus());
//    }
//
//    @Test
//    public void testViewGroup() {
//        String groupCode = "ABC";
//        ViewGroupUsersResponse response = new ViewGroupUsersResponse(groupCode);
//
//        when(appGroupService.findGroupUsers(groupCode)).thenReturn(response);
//
//        ViewGroupUsersResponse viewResponse = groupServices.viewGroup(groupCode);
//
//        assertEquals(groupCode, viewResponse.getGroupCode());
//    }
}
