package foodBuddy.foodBuddy.inventoryTests;

import foodBuddy.foodBuddy.expense.ExpenseRepository;
import foodBuddy.foodBuddy.expense.userExpenses;
import foodBuddy.foodBuddy.groupManagement.AppGroup;
import foodBuddy.foodBuddy.groupManagement.GroupRepository;
import foodBuddy.foodBuddy.groupManagement.ViewGroupUsersResponse;
import foodBuddy.foodBuddy.inventory.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
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

    @Mock
    private ExpenseRepository expenseRepository;

    @Test
    void testAddItemSuccess() {
        String item="item1";
        int quantity=1;
        String expDate="2023-05-01";
        String groupCode="group1";
        Double amount=10.0;
        String emailId="demo@email.com";
        AddItemRequest request = new AddItemRequest(item,quantity,expDate,groupCode,amount,emailId);
        InventoryEntity inventoryEntity = new InventoryEntity(item, expDate, quantity, amount);
        userExpenses expenses = new userExpenses();
        expenses.setItemId(1);
        expenses.setAmount(10.0);
        expenses.setEmailId("demo@email.com");
        when(inventoryRepository.findInventoryEntitiesByItemName(anyString(), anyString())).thenReturn(Optional.empty());
        when(inventoryRepository.save(any(InventoryEntity.class))).thenReturn(inventoryEntity);
        when(expenseRepository.findUserExpenseExists(emailId)).thenReturn(Optional.empty());
        AddItemResponse response = inventoryService.addItem(request);

        Assertions.assertEquals("success", response.getStatus());
        Assertions.assertEquals("Item successfully", response.getMessage());
    }

    @Test
    void testAddItemAlreadyExists() {
        String item="item1";
        int quantity=1;
        String expDate="2023-05-01";
        String groupCode="group1";
        Double amount=10.0;
        String emailId="demo@email.com";
        AddItemRequest request = new AddItemRequest(item,quantity,expDate,groupCode,amount,emailId);
        InventoryEntity inventoryEntity = new InventoryEntity(item, expDate, quantity, amount);
        userExpenses expenses = new userExpenses();
        expenses.setItemId(1);
        expenses.setAmount(10.0);
        expenses.setEmailId("demo@email.com");
        when(inventoryRepository.findInventoryEntitiesByItemName(anyString(), anyString())).thenReturn(Optional.empty());
        when(inventoryRepository.save(any(InventoryEntity.class))).thenReturn(inventoryEntity);
        when(expenseRepository.findUserExpenseExists(emailId)).thenReturn(Optional.of(expenses));
        when(expenseRepository.getPastUserExpenses(emailId)).thenReturn(30.4);
        AddItemResponse response = inventoryService.addItem(request);
        Assertions.assertEquals("success", response.getStatus());
        Assertions.assertEquals("Item successfully", response.getMessage());
    }

    @Test
    void testAddItemFailure(){
        String item="item1";
        int quantity=1;
        String expDate="2023-05-01";
        String groupCode="group1";
        Double amount=10.0;
        String emailId="demo@email.com";
        AddItemRequest request = new AddItemRequest(item,quantity,expDate,groupCode,amount,emailId);
        InventoryEntity inventoryEntity = new InventoryEntity(item, expDate, quantity, amount);
        userExpenses expenses = new userExpenses();
        expenses.setItemId(1);
        expenses.setAmount(10.0);
        expenses.setEmailId("demo@email.com");
        when(inventoryRepository.findInventoryEntitiesByItemName(anyString(), anyString())).thenReturn(Optional.of(inventoryEntity));
        when(expenseRepository.findUserExpenseExists(emailId)).thenReturn(Optional.empty());
        AddItemResponse response = inventoryService.addItem(request);
        Assertions.assertEquals("failure", response.getStatus());
        Assertions.assertEquals("Item Exists", response.getMessage());
    }

//    @Test
//    void testUpdateItem() {
//        String item="item1";
//        int quantity=1;
//        String expDate="2023-05-01";
//        String groupCode="group1";
//        Double amount=10.0;
//        String emailId="demo@email.com";
//        AddItemRequest request = new AddItemRequest(item,quantity,expDate,groupCode,amount,emailId);
//        InventoryEntity inventoryEntity = new InventoryEntity(item, expDate, quantity, amount);
//
//        when(inventoryRepository.findInventoryEntitiesByItemName(anyString(), anyString())).thenReturn(Optional.of(inventoryEntity));
//
//        UpdateItemResponse response = inventoryService.updateItem(updateItemRequest);
//
//        Assertions.assertEquals("success", response.getStatus());
//        Assertions.assertEquals("Item Updated successfully", response.getMessage());
//    }

//    @Test
//    void testUpdateItemNotExists() {
//        when(inventoryRepository.findInventoryEntitiesByItemName(anyString(), anyString())).thenReturn(Optional.empty());
//
//        UpdateItemResponse response = inventoryService.updateItem(updateItemRequest);
//
//        Assertions.assertEquals("failure", response.getStatus());
//        Assertions.assertEquals("Item Does not Exists", response.getMessage());
//    }
//
//
//
//
    @Test
    void testViewItemsWhenGroupExists() {
        List<ViewItems> inventoryEntities = new ArrayList<ViewItems>();
        String groupCode="group1";
        when(groupRepository.findGroupByCode(groupCode)).thenReturn(new String(groupCode));
        when(inventoryRepository.findItemList(groupCode)).thenReturn(inventoryEntities);
        ViewItemsResponse response = inventoryService.viewItems(groupCode);
        Assertions.assertEquals("success", response.getStatus());
        Assertions.assertEquals(0, response.getItemList().size());
    }

    @Test
    void testViewItemsInvalidGroup() {
        String groupCode="1234";
        when(groupRepository.findGroupByCode(groupCode)).thenReturn(" ");
        ViewItemsResponse response = inventoryService.viewItems(groupCode);
        Assertions.assertEquals("failure", response.getStatus());
        Assertions.assertEquals("Invalid GroupCode", response.getMessage());
    }

    @Test
    void testDeleteItemSuccess(){
        String item="item1";
        int quantity=1;
        String expDate="2023-05-01";
        String groupCode="group1";
        Double amount=10.0;
        String emailId="demo@email.com";
        DeleteItemRequest request = new DeleteItemRequest(item,groupCode,amount,emailId);
        InventoryEntity inventoryEntity = new InventoryEntity(item, expDate, quantity, amount);
        when(inventoryRepository.findInventoryEntitiesByItemName(item,groupCode)).thenReturn(Optional.of(inventoryEntity));
        when(expenseRepository.getPastUserExpenses(emailId)).thenReturn(33.34);
        DeleteItemResponse response = inventoryService.deleteItem(request);
        Assertions.assertEquals("success", response.getStatus());
        Assertions.assertEquals("Item Updated successfully", response.getMessage());

    }

    @Test
    void testDeleteItemFailure(){
        String item="item1";
        int quantity=1;
        String expDate="2023-05-01";
        String groupCode="group1";
        Double amount=10.0;
        String emailId="demo@email.com";
        DeleteItemRequest request = new DeleteItemRequest(item,groupCode,amount,emailId);
        when(inventoryRepository.findInventoryEntitiesByItemName(item,groupCode)).thenReturn(Optional.empty());
        DeleteItemResponse response = inventoryService.deleteItem(request);
        Assertions.assertEquals("failure", response.getStatus());
        Assertions.assertEquals("Item Does not Exists", response.getMessage());

    }


}
