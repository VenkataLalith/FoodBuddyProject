package foodBuddy.foodBuddy.groupManagementTests;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringJUnitConfig
//@WebMvcTest(GroupController.class)
class GroupControllerTests {
//
//    @MockBean
//    private GroupServices groupServices;
//
//    @Mock
//    private GroupCreationRequest groupCreationRequest;
//
//    @Mock
//    private GroupJoinRequest groupJoinRequest;
//
//    @Mock
//    private ViewGroupUsersResponse viewGroupResponse;
//
//    @Mock
//    private GroupCreationResponse groupCreationResponse;
//
//    @Mock
//    private GroupJoinResponse groupJoinResponse;
//
//    @Mock
//    private GroupRepository groupRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testCreateGroup() throws Exception {
//        when(groupServices.createGroup(groupCreationRequest)).thenReturn(groupCreationResponse);
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/groupApi/Create")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{}"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        assertEquals(result.getResponse().getContentAsString(), "{}");
//    }
//
//    @Test
//    void testJoinGroup() throws Exception {
//        when(groupServices.joinGroup(groupJoinRequest)).thenReturn(groupJoinResponse);
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/groupApi/Join")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{}"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        assertEquals(result.getResponse().getContentAsString(), "{}");
//    }
//
//    @Test
//    void testViewGroup() throws Exception {
//        String groupCode = "Test Code";
//        List<User> users = new ArrayList<>();
//        users.add(new User("Test User", groupCode));
//        viewGroupResponse.setGroupUsersList(users);
//        viewGroupResponse.setMessage("Found Members");
//        viewGroupResponse.setStatus("success");
//        when(groupServices.viewGroup(groupCode)).thenReturn(viewGroupResponse);
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/groupApi/view")
//                .param("groupCode", groupCode))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        assertEquals(result.getResponse().getContentAsString(), "{\"groupUsersList\":[{\"name\":\"Test User\",\"groupName\":\"Test Code\"}],\"message\":\"Found Members\",\"status\":\"success\"}");
//    }
}
