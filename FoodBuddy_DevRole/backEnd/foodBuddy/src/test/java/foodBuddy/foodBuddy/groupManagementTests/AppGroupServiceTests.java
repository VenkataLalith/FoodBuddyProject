package foodBuddy.foodBuddy.groupManagement;

import foodBuddy.foodBuddy.appuser.User;
import foodBuddy.foodBuddy.appuser.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppGroupServiceTest {

    private AppGroupService appGroupService;
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        appGroupService = new AppGroupService(groupRepository, userRepository);
    }

    @Test
    void testCreateGroupSuccess() {
        // Arrange
        AppGroup appGroup = new AppGroup("Test Group");
        GroupCreationResponse expectedResponse = new GroupCreationResponse();
        expectedResponse.setMessage("Group created successfully");
        expectedResponse.setStatus("success");
        when(groupRepository.findByGroupName(appGroup.getGroupName())).thenReturn(Optional.empty());

        // Act
        GroupCreationResponse actualResponse = appGroupService.CreateGroup(appGroup);

        // Assert
        verify(groupRepository, times(1)).save(appGroup);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testCreateGroupFailure() {
        // Arrange
        AppGroup appGroup = new AppGroup("Existing Group");
        GroupCreationResponse expectedResponse = new GroupCreationResponse();
        expectedResponse.setMessage("GroupName Exists");
        expectedResponse.setStatus("failure");
        when(groupRepository.findByGroupName(appGroup.getGroupName())).thenReturn(Optional.of(appGroup));

        // Act
        GroupCreationResponse actualResponse = appGroupService.CreateGroup(appGroup);

        // Assert
        verify(groupRepository, never()).save(appGroup);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testJoinGroupSuccess() {
        // Arrange
        GroupJoinRequest request = new GroupJoinRequest("Test User", "Test Code");
        GroupJoinResponse expectedResponse = new GroupJoinResponse();
        expectedResponse.setMessage("Joined successfully");
        expectedResponse.setStatus("success");
        when(groupRepository.findGroupByCode(request.getGroupCode())).thenReturn("Test Code");

        // Act
        GroupJoinResponse actualResponse = appGroupService.joinGroup(request);

        // Assert
        verify(userRepository, times(1)).UpdateGroupName(request.getGroupCode(), request.getUserName());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testJoinGroupFailure() {
        // Arrange
        GroupJoinRequest request = new GroupJoinRequest("Test User", "Invalid Code");
        GroupJoinResponse expectedResponse = new GroupJoinResponse();
        expectedResponse.setMessage("please verify the groupCode: Unable to join");
        expectedResponse.setStatus("failure");
        when(groupRepository.findGroupByCode(request.getGroupCode())).thenReturn("");

        // Act
        GroupJoinResponse actualResponse = appGroupService.joinGroup(request);

        // Assert
        verify(userRepository, never()).UpdateGroupName(request.getGroupCode(), request.getUserName());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testFindGroupUsersSuccess() {

        // Arrange
        String groupCode = "Test Code";
        ViewGroupUsersResponse expectedResponse = new ViewGroupUsersResponse();
        List<User> users = new ArrayList<>();
        users.add(new User("Test User", groupCode));
        expectedResponse.setGroupUsersList(users);
        expectedResponse.setMessage("Found Members");
        expectedResponse.setStatus("success");
        when(groupRepository.findGroupByCode(groupCode)).thenReturn(groupCode);
        when(userRepository.findByGroupName(groupCode)).thenReturn(users);
    
        // Act
        ViewGroupUsersResponse actualResponse = appGroupService.findGroupUsers(groupCode);
    
        // Assert
        assertEquals(expectedResponse, actualResponse);
    }
    
    @Test
    void testFindGroupUsersFailure() {
        // Arrange
        String groupCode = "Invalid Code";
        ViewGroupUsersResponse expectedResponse = new ViewGroupUsersResponse();
        expectedResponse.setMessage("Invalid Group Code");
        expectedResponse.setStatus("failure");
        when(groupRepository.findGroupByCode(groupCode)).thenReturn("");
    
        // Act
        ViewGroupUsersResponse actualResponse = appGroupService.findGroupUsers(groupCode);
    
        // Assert
        verify(userRepository, never()).findByGroupName(groupCode);
        assertEquals(expectedResponse, actualResponse);
    }
    
