package foodBuddy.foodBuddy.groupManagement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GroupServicesTest {
    
    @Mock
    private AppGroupService appGroupService;
    
    @InjectMocks
    private GroupServices groupServices;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testCreateGroup_success() {
        GroupCreationRequest request = new GroupCreationRequest("Group1", "ABC");
        AppGroup appGroup = new AppGroup(request.getGroupName(), request.getGroupCode());
        GroupCreationResponse creationResponse = new GroupCreationResponse("success");
        GroupJoinRequest joinRequest = new GroupJoinRequest(request.getGroupCode(), "User1");
        GroupJoinResponse joinResponse = new GroupJoinResponse("success");
        
        when(appGroupService.CreateGroup(appGroup)).thenReturn(creationResponse);
        when(appGroupService.joinGroup(joinRequest)).thenReturn(joinResponse);
        
        GroupCreationResponse response = groupServices.createGroup(request);
        
        assertEquals("success", response.getStatus());
    }
    
    @Test
    public void testCreateGroup_joinFailed() {
        GroupCreationRequest request = new GroupCreationRequest("Group1", "ABC");
        AppGroup appGroup = new AppGroup(request.getGroupName(), request.getGroupCode());
        GroupCreationResponse creationResponse = new GroupCreationResponse("success");
        GroupJoinRequest joinRequest = new GroupJoinRequest(request.getGroupCode(), "User1");
        GroupJoinResponse joinResponse = new GroupJoinResponse("failure");
        
        when(appGroupService.CreateGroup(appGroup)).thenReturn(creationResponse);
        when(appGroupService.joinGroup(joinRequest)).thenReturn(joinResponse);
        
        GroupCreationResponse response = groupServices.createGroup(request);
        
        assertEquals("joinFailed", response.getStatus());
    }
    
    @Test
    public void testJoinGroup() {
        GroupJoinRequest request = new GroupJoinRequest("ABC", "User1");
        GroupJoinResponse response = new GroupJoinResponse("success");
        
        when(appGroupService.joinGroup(request)).thenReturn(response);
        
        GroupJoinResponse joinResponse = groupServices.joinGroup(request);
        
        assertEquals("success", joinResponse.getStatus());
    }
    
    @Test
    public void testViewGroup() {
        String groupCode = "ABC";
        ViewGroupUsersResponse response = new ViewGroupUsersResponse(groupCode);
        
        when(appGroupService.findGroupUsers(groupCode)).thenReturn(response);
        
        ViewGroupUsersResponse viewResponse = groupServices.viewGroup(groupCode);
        
        assertEquals(groupCode, viewResponse.getGroupCode());
    }
}
