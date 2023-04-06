package foodBuddy.foodBuddy.notification;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping(path = "/api/v1/notification")
public class NotificationController {

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @PostMapping("/notify")
    public String sendNotification(@RequestBody NotificationRequest request) throws MessagingException, jakarta.mail.MessagingException {
        System.out.println("inside controller");
//        notificationService.sendSimpleEmail();
        notificationService.send("sujahidms@gmail.com","foodbuddy.asdc5308@gmail.com");

        return "success";
    }

}
