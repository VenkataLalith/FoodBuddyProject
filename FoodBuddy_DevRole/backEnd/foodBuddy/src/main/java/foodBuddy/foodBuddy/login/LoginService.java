package foodBuddy.foodBuddy.login;

import org.springframework.stereotype.Service;

import foodBuddy.foodBuddy.appuser.AppUser;
import foodBuddy.foodBuddy.appuser.AppUserRole;
import foodBuddy.foodBuddy.appuser.AppUserService;
import foodBuddy.foodBuddy.registration.EmailValidator;
import foodBuddy.foodBuddy.registration.RegistrationRequest;

@Service
public class LoginService {
	private EmailValidator emailValidator;
    private AppUserService appUserService;

    public String login(LoginRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("Email Not Valid");
        }
        return appUserService.loginUser(new AppUser(
        		request.getEmail(),
                request.getPassword()
                ));
    }

}
