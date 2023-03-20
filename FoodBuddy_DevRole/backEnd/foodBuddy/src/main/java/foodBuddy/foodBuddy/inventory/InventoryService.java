package foodBuddy.foodBuddy.inventory;

import foodBuddy.foodBuddy.groupManagement.AppGroup;
import foodBuddy.foodBuddy.groupManagement.GroupRepository;
import foodBuddy.foodBuddy.groupManagement.ViewGroupUsersResponse;
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

    public AddItemResponse addItem(AddItemRequest request) {
        System.out.println("inside Inventory Add");
        System.out.println(request);
        AddItemResponse response = new AddItemResponse();
        InventoryEntity inventory = new InventoryEntity(request.getItemName(), request.getExpDate(),request.getQuantity(), request.getGroupCode());
        boolean itemExists = inventoryRepository.findInventoryEntitiesByItemName(inventory.getItemName(),inventory.getGroupCode()).isPresent();
        if (!itemExists){
            inventoryRepository.save(inventory);
            response.setMessage("Item successfully");
            response.setStatus("success");
            return response;
        }
        else {
            response.setMessage("Item Exists");
            response.setStatus("failure");
            return response;
        }
    }

    public UpdateItemResponse updateItem(UpdateItemRequest request) {
        UpdateItemResponse response = new UpdateItemResponse();
        boolean itemExists = inventoryRepository.findInventoryEntitiesByItemName(request.getItemName(),request.getGroupCode()).isPresent();
        if (itemExists){
            inventoryRepository.updateItemDetails(request.getItemName(),request.getGroupCode(), request.getExpDate(), request.getQuantity());
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

    public ViewItemsResponse viewItems(String groupCode) {
        ViewItemsResponse response = new ViewItemsResponse();
        try {
            boolean groupExists = true;//groupRepository.findGroupByCode(groupCode).isBlank();
            if (groupExists){
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
            inventoryRepository.updateItemDetails(request.getItemName(),request.getGroupCode(), request.getExpDate(), request.getQuantity());
            inventoryRepository.
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
