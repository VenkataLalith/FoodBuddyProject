package foodBuddy.foodBuddy.appuser;

import foodBuddy.foodBuddy.login.LoginResponse;
import foodBuddy.foodBuddy.registration.token.ConfirmationToken;
import foodBuddy.foodBuddy.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";

    private final static String PASSWORD_WRONG ="Wrong Password for email: %s";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).
                orElseThrow(() ->
                new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }
    public String signUpUser (AppUser appUser){
        boolean userExists = userRepository.findByEmail(appUser.getEmail())
                .isPresent();
        if (userExists){
//            throw new IllegalStateException("email already taken");
            return "User Exists";
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        userRepository.save(appUser);
        // for tokenization
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
                );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        // send email
        return token;
    }
    
    public LoginResponse loginUser(AppUser appUser) {
        LoginResponse response = new LoginResponse();
    	if(userRepository.findByEmail(appUser.getEmail()).isPresent()) {
    		String password = userRepository.findPasswordByEmail(appUser.getEmail());
            Boolean userPassword = (bCryptPasswordEncoder.matches(appUser.getPassword(),password));
            if(userPassword){
                response.setGroupCode(userRepository.findGroupByEmail(appUser.getEmail()));
                response.setUsername(appUser.getEmail());
                response.setStatus("success");
                response.setMessage("Login Successful");
    			return response;
    		} else {
                response.setGroupCode(null);
                response.setUsername(null);
                response.setStatus("failure");
                response.setMessage("Login failed");
                return response;
    		}
    	} else {
    		//throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
            response.setGroupCode(null);
            response.setUsername(null);
            response.setStatus("failure");
            response.setMessage("Login failed");
            return response;
    	}
    	
    	
    }
}
