package foodBuddy.foodBuddy.notification;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/notification")
public class NotificationController {
    private NotificationService notificationService;
    @PostMapping("/notify")
    public String sendNotification(@RequestBody NotificationRequest request){
        System.out.println("inside controller");
        notificationService.sendNotification(request);
        return "success";
    }

}
