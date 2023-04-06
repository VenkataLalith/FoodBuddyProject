package foodBuddy.foodBuddy.notification;
//import jakarta.mail.MessagingException;
import javax.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
@Service
public class NotificationService implements EmailSender{
    private JavaMailSender javaMailSender;

    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void sendSimpleEmail() throws MessagingException {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setTo("sujahidms@gmail.com");
//        simpleMailMessage.setFrom("foodbuddy.asdc5308@gmail.com");
//        simpleMailMessage.setSubject("Subject");
//        simpleMailMessage.setText("text");
//        javaMailSender.send(simpleMailMessage);
//    }

    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,"utf-8");
            helper.setFrom(email);
            helper.setTo(to);
            helper.setText("BODY");
            helper.setSubject("subject");
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
