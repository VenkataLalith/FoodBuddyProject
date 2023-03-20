package foodBuddy.foodBuddy.notification;

import foodBuddy.foodBuddy.appuser.UserRepository;
import foodBuddy.foodBuddy.groupManagement.GroupRepository;
import foodBuddy.foodBuddy.groupManagement.ViewGroupUsers;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService {
    @Autowired
    private final UserRepository userRepository;
//    @Autowired
//    private EmailService emailService;
    public String sendNotification(NotificationRequest request){
        System.out.println(request);
        List<ViewGroupUsers> groupName = userRepository.findUsersByGroupCode(request.getGroupCode());
        List<String> recipients = new ArrayList<>();
        for (ViewGroupUsers eachUser: groupName) {
            System.out.println(eachUser.getUsername());
            recipients.add(eachUser.getUsername());
        }
        recipients.add("sujahidms@gmail.com");
        System.out.println(recipients);
//        String subject = "Hello from FoodBuddy!";
//        String body = "This is a test email sent from MyApplication.";
//        for (String emailId: recipients) {
//            emailService.sendSimpleMessage(emailId,subject,body);
//        }
        return "success";
    }
}
