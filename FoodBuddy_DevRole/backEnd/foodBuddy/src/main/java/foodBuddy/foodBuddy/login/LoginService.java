package foodBuddy.foodBuddy.login;

import foodBuddy.foodBuddy.appuser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import foodBuddy.foodBuddy.appuser.AppUser;
import foodBuddy.foodBuddy.appuser.AppUserService;
import foodBuddy.foodBuddy.registration.EmailValidator;
import foodBuddy.foodBuddy.registration.RegistrationRequest;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


@Service
public class LoginService {
	private EmailValidator emailValidator;
    private AppUserService appUserService;


    public LoginService(AppUserService appUserService, EmailValidator emailValidator) {
        this.appUserService = appUserService;
        this.emailValidator = emailValidator;
    }
    public LoginResponse login(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        try {
            InternetAddress internetAddress = new InternetAddress(request.getUsername());
            internetAddress.validate();
            AppUser appUser =new AppUser(request.getUsername(), request.getPassword());
            response = appUserService.loginUser(appUser);
            return response;
        } catch (Exception e) {
            // invalid email address
            response.setStatus("failure");
            response.setMessage(e.toString());
            response.setUsername(null);
            response.setGroupCode(null);
//            throw new IllegalStateException("Email Not Valid");
            return  response;
        }
    }

}
