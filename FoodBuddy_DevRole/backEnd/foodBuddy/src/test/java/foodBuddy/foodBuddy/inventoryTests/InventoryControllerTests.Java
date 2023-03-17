package foodBuddy.foodBuddy.inventory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class InventoryControllerTest {
    @MockBean
    private InventoryService inventoryService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addItemTest() throws Exception {
        // given
        AddItemRequest request = new AddItemRequest("groupCode", "item", 10);
        AddItemResponse expectedResponse = new AddItemResponse("item added successfully");

        given(inventoryService.addItem(any(AddItemRequest.class))).willReturn(expectedResponse);

        // when-then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/inventory/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"groupCode\":\"groupCode\",\"itemName\":\"item\",\"quantity\":10}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"message\":\"item added successfully\"}"));
    }

    @Test
    public void updateItemTest() throws Exception {
        // given
        UpdateItemRequest request = new UpdateItemRequest("groupCode", "item", 10);
        UpdateItemResponse expectedResponse = new UpdateItemResponse("item updated successfully");

        given(inventoryService.updateItem(any(UpdateItemRequest.class))).willReturn(expectedResponse);

        // when-then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/inventory/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"groupCode\":\"groupCode\",\"itemName\":\"item\",\"quantity\":10}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"message\":\"item updated successfully\"}"));
    }

    @Test
    public void viewItemTest() throws Exception {
        // given
        ViewItemsResponse expectedResponse = new ViewItemsResponse("groupCode", "item", 10);

        given(inventoryService.viewItems(any(String.class))).willReturn(expectedResponse);

        // when-then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/inventory/view")
                .param("groupCode", "groupCode"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"groupCode\":\"groupCode\",\"itemName\":\"item\",\"quantity\":10}"));
    }
}
