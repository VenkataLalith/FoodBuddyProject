package foodBuddy.foodBuddy.inventory;

import foodBuddy.foodBuddy.groupManagement.AppGroup;
import foodBuddy.foodBuddy.groupManagement.GroupRepository;
import foodBuddy.foodBuddy.groupManagement.ViewGroupUsersResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private InventoryService inventoryService;

    private AddItemRequest addItemRequest;
    private UpdateItemRequest updateItemRequest;
    private String groupCode;
    private String itemName;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        groupCode = "ABC123";
        itemName = "Test Item";

        addItemRequest = new AddItemRequest();
        addItemRequest.setItemName(itemName);
        addItemRequest.setExpDate("2023-12-31");
        addItemRequest.setQuantity(10);
        addItemRequest.setGroupCode(groupCode);

        updateItemRequest = new UpdateItemRequest();
        updateItemRequest.setItemName(itemName);
        updateItemRequest.setExpDate("2023-12-31");
        updateItemRequest.setQuantity(20);
        updateItemRequest.setGroupCode(groupCode);
    }

    @Test
    void testAddItem() {
        InventoryEntity inventoryEntity = new InventoryEntity(itemName, addItemRequest.getExpDate(), addItemRequest.getQuantity(), addItemRequest.getGroupCode());

        when(inventoryRepository.findInventoryEntitiesByItemName(anyString(), anyString())).thenReturn(Optional.empty());
        when(inventoryRepository.save(any(InventoryEntity.class))).thenReturn(inventoryEntity);

        AddItemResponse response = inventoryService.addItem(addItemRequest);

        Assertions.assertEquals("success", response.getStatus());
        Assertions.assertEquals("Item successfully", response.getMessage());
    }

    @Test
    void testAddItemAlreadyExists() {
        InventoryEntity inventoryEntity = new InventoryEntity(itemName, addItemRequest.getExpDate(), addItemRequest.getQuantity(), addItemRequest.getGroupCode());

        when(inventoryRepository.findInventoryEntitiesByItemName(anyString(), anyString())).thenReturn(Optional.of(inventoryEntity));

        AddItemResponse response = inventoryService.addItem(addItemRequest);

        Assertions.assertEquals("failure", response.getStatus());
        Assertions.assertEquals("Item Exists", response.getMessage());
    }

    @Test
    void testUpdateItem() {
        InventoryEntity inventoryEntity = new InventoryEntity(itemName, addItemRequest.getExpDate(), addItemRequest.getQuantity(), addItemRequest.getGroupCode());

        when(inventoryRepository.findInventoryEntitiesByItemName(anyString(), anyString())).thenReturn(Optional.of(inventoryEntity));

        UpdateItemResponse response = inventoryService.updateItem(updateItemRequest);

        Assertions.assertEquals("success", response.getStatus());
        Assertions.assertEquals("Item Updated successfully", response.getMessage());
    }

    @Test
    void testUpdateItemNotExists() {
        when(inventoryRepository.findInventoryEntitiesByItemName(anyString(), anyString())).thenReturn(Optional.empty());

        UpdateItemResponse response = inventoryService.updateItem(updateItemRequest);

        Assertions.assertEquals("failure", response.getStatus());
        Assertions.assertEquals("Item Does not Exists", response.getMessage());
    }

   


    @Test
    void testViewItemsNoItems() {
        List<InventoryEntity> inventoryEntities = new ArrayList<>();

        when(inventoryRepository.findInventoryEntitiesByGroupCode(anyString())).thenReturn(inventoryEntities);

        ViewInventoryResponse response = inventoryService.viewItems(groupCode);

        Assertions.assertEquals("success", response.getStatus());
        Assertions.assertEquals(0, response.getItems().size());
    }

    @Test
    void testViewItemsInvalidGroup() {
        when(groupRepository.findByGroupCode(anyString())).thenReturn(Optional.empty());

        ViewInventoryResponse response = inventoryService.viewItems(groupCode);

        Assertions.assertEquals("failure", response.getStatus());
        Assertions.assertEquals("Invalid Group Code", response.getMessage());
    }

    @Test
    void testViewGroupUsers() {
        AppGroup appGroup = new AppGroup(groupCode, "Test Group");
        appGroup.addUser("Test User 1");
        appGroup.addUser("Test User 2");

        when(groupRepository.findByGroupCode(anyString())).thenReturn(Optional.of(appGroup));

        ViewGroupUsersResponse response = inventoryService.viewGroupUsers(groupCode);

        Assertions.assertEquals("success", response.getStatus());
        Assertions.assertEquals(2, response.getUsers().size());
    }

    @Test
    void testViewGroupUsersInvalidGroup() {
        when(groupRepository.findByGroupCode(anyString())).thenReturn(Optional.empty());

        ViewGroupUsersResponse response = inventoryService.viewGroupUsers(groupCode);

        Assertions.assertEquals("failure", response.getStatus());
        Assertions.assertEquals("Invalid Group Code", response.getMessage());
    }
}
