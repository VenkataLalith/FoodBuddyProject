package foodBuddy.foodBuddy.inventory;

import foodBuddy.foodBuddy.expense.ExpenseRepository;
import foodBuddy.foodBuddy.expense.userExpenses;
import foodBuddy.foodBuddy.groupManagement.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class InventoryService {
    @Autowired
    private final InventoryRepository inventoryRepository;

    @Autowired
    private final GroupRepository groupRepository;

    @Autowired
    private final ExpenseRepository expenseRepository;

    public AddItemResponse addItem(AddItemRequest request) {
        System.out.println("inside Inventory Add");
        System.out.println(request);
        AddItemResponse response = new AddItemResponse();
        InventoryEntity inventory = new InventoryEntity(request.getItemName(), request.getExpDate(),request.getQuantity(), request.getGroupCode(), request.getAmount());
        boolean itemExists = inventoryRepository.findInventoryEntitiesByItemName(inventory.getItemName(),inventory.getGroupCode()).isPresent();
        boolean userExpensesExits = expenseRepository.findUserExpenseExists(request.getEmailId()).isPresent();
        System.out.println("itemExists: "+itemExists);
        userExpenses userExpenses = new userExpenses(request.getEmailId(), request.getAmount());
        if (!itemExists){
            System.out.println("userExpensesExits: "+userExpensesExits);
            if (userExpensesExits){
                Double previousAmount = expenseRepository.getPastUserExpenses(request.getEmailId());
                expenseRepository.updateUserExpense(request.getAmount()+previousAmount, request.getEmailId());
                inventoryRepository.updateItemDetails(request.getItemName(), request.getGroupCode(), request.getExpDate(), request.getQuantity(), request.getAmount());
            }
            else {
                expenseRepository.save(userExpenses);
            }
            inventoryRepository.save(inventory);
            response.setMessage("Item successfully");
            response.setStatus("success");
        }
        else {
            if (userExpensesExits) {
                expenseRepository.updateUserExpense(request.getAmount(), request.getEmailId());
                inventoryRepository.updateItemDetails(request.getItemName(), request.getGroupCode(), request.getExpDate(), request.getQuantity(), request.getAmount());

            } else {
                response.setMessage("Item Exists");
                response.setStatus("failure");
            }
        }

        return response;
    }

//    public UpdateItemResponse updateItem(UpdateItemRequest request) {
//        System.out.println(request);
//        UpdateItemResponse response = new UpdateItemResponse();
//        boolean itemExists = inventoryRepository.findInventoryEntitiesByItemName(request.getItemName(),request.getGroupCode()).isPresent();
//        if (itemExists){
//            inventoryRepository.updateItemDetails(request.getItemName(),request.getGroupCode(), request.getExpDate(), request.getQuantity(),request.g);
//            response.setMessage("Item Updated successfully");
//            response.setStatus("success");
//            return response;
//        }
//        else {
//            response.setMessage("Item Does not Exists");
//            response.setStatus("failure");
//            return response;
//        }
//    }

    public ViewItemsResponse viewItems(String groupCode) {
        ViewItemsResponse response = new ViewItemsResponse();
        try {
            boolean groupExists = groupRepository.findGroupByCode(groupCode).isBlank();
            if (!groupExists){
                response.setItemList(inventoryRepository.findItemList(groupCode));
                response.setMessage("Found Group Items");
                response.setStatus("success");
                return response;
            }
            else {
                response.setItemList(null);
                response.setMessage("Invalid GroupCode");
                response.setStatus("failure");
                return response;
            }
        }
        catch (NullPointerException e){
            response.setItemList(null);
            response.setMessage("please verify the groupCode");
            response.setStatus("failure");
            return response;
        }

    }
    public DeleteItemResponse deleteItem(DeleteItemRequest request) {
        DeleteItemResponse response = new DeleteItemResponse();
        boolean itemExists = inventoryRepository.findInventoryEntitiesByItemName(request.getItemName(),request.getGroupCode()).isPresent();
        if (itemExists){
            Double previousAmount = expenseRepository.getPastUserExpenses(request.getEmailId());
            if(request.getAmount() != null) {
                expenseRepository.updateUserExpense(Math.abs(request.getAmount() - previousAmount), request.getEmailId());
            }
            inventoryRepository.deleteItemfromDB(request.getItemName(),request.getGroupCode());
            response.setMessage("Item Updated successfully");
            response.setStatus("success");
            return response;
        }
        else {
            response.setMessage("Item Does not Exists");
            response.setStatus("failure");
            return response;
        }
    }
}
