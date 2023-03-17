package foodBuddy.foodBuddy.inventory;


import foodBuddy.foodBuddy.groupManagement.ViewGroupUsersResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/add")
    public AddItemResponse addItem(@RequestBody AddItemRequest request){
        return inventoryService.addItem(request);
    }

    @PostMapping("/update")
    public UpdateItemResponse updateItem(@RequestBody UpdateItemRequest request){
        return inventoryService.updateItem(request);
    }

    @GetMapping("/view")
    public ViewItemsResponse viewItem(@RequestParam(value = "groupCode") String groupCode){
        ViewItemsResponse response = inventoryService.viewItems(groupCode);
        return response;
    }
    @PostMapping("/delete")
    public DeleteItemResponse deleteItem(@RequestBody  DeleteItemRequest request) {
    DeleteItemResponse response = inventoryService.deleteItem(itemName,groupCode);
    return response;
}

}
