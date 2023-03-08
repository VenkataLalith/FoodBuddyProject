package foodBuddy.foodBuddy.login;

import foodBuddy.foodBuddy.appuser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import foodBuddy.foodBuddy.appuser.AppUser;
import foodBuddy.foodBuddy.appuser.AppUserRole;
import foodBuddy.foodBuddy.appuser.AppUserService;
import foodBuddy.foodBuddy.registration.EmailValidator;
import foodBuddy.foodBuddy.registration.RegistrationRequest;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


@Service
public class LoginService {
	private EmailValidator emailValidator;

    @Autowired
    private AppUserService appUserService;

    public String login(LoginRequest request) {
        try {
            InternetAddress internetAddress = new InternetAddress(request.getUsername());
            internetAddress.validate();
            AppUser appUser =new AppUser(request.getUsername(), request.getPassword());
            String data = appUserService.loginUser(appUser);
            return data;
        } catch (AddressException e) {
            // invalid email address
            return e.toString();
//            throw new IllegalStateException("Email Not Valid");

        }
        //        return appUserService.loginUser(new AppUser(
//        		request.getEmail(),
//                request.getPassword()
//                ));
    }

}
