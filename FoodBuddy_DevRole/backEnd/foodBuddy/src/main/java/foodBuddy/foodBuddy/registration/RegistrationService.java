package foodBuddy.foodBuddy.registration;

import foodBuddy.foodBuddy.appuser.AppUser;
import foodBuddy.foodBuddy.appuser.AppUserService;
import foodBuddy.foodBuddy.registration.token.ConfirmationToken;
import foodBuddy.foodBuddy.registration.token.ConfirmationTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private EmailValidator emailValidator;
    private final AppUserService appUserService;

    public RegistrationService(EmailValidator emailValidator, AppUserService appUserService) {
        this.emailValidator = emailValidator;
        this.appUserService = appUserService;
    }

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("Email Not Valid");
        }
        return appUserService.signUpUser(new AppUser(request.getFirstName(),
                request.getLastName(),
                request.getPassword(),
                request.getEmail()));
    }


}
