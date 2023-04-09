package foodBuddy.foodBuddy.inventory;


import foodBuddy.foodBuddy.notification.NotificationRequest;
import foodBuddy.foodBuddy.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1/inventory")
public class InventoryController {

    private  InventoryService inventoryService;
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/add")
    public AddItemResponse addItem(@RequestBody AddItemRequest request){
        return inventoryService.addItem(request);
    }

//    @PostMapping("/update")
//    public UpdateItemResponse updateItem(@RequestBody UpdateItemRequest request){
//        return inventoryService.updateItem(request);
//    }

    @GetMapping("/view")
    public ViewItemsResponse viewItem(@RequestParam(value = "groupCode") String groupCode){
        ViewItemsResponse response = inventoryService.viewItems(groupCode);
        return response;
    }
    @PostMapping("/delete")
    public DeleteItemResponse deleteItem(@RequestBody  DeleteItemRequest request) {
    DeleteItemResponse response = inventoryService.deleteItem(request);
    return response;
    }
}
