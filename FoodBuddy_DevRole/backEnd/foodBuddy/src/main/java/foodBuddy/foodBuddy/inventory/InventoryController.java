package foodBuddy.foodBuddy.inventory;


import foodBuddy.foodBuddy.notification.NotificationRequest;
import foodBuddy.foodBuddy.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/inventory")
public class InventoryController {

    private  InventoryService inventoryService;
    private  NotificationService notificationService;

    public InventoryController(InventoryService inventoryService, NotificationService notificationService) {
        this.inventoryService = inventoryService;
        this.notificationService = notificationService;
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

    @PostMapping("/notify")
    public String sendNotification(@RequestBody NotificationRequest request){
        notificationService.sendNotification(request);
        return "sucess";
    }
}
