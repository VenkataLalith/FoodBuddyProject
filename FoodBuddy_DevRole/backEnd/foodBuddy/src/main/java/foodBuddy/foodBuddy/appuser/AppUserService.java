package foodBuddy.foodBuddy.appuser;

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
    private final UserRepository  userRepository;
    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
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
            throw new IllegalStateException("email already taken");
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
    
    public String loginUser(AppUser appUser) {
    	if(userRepository.findByEmail(appUser.getEmail()).isPresent()) {
    		String password = userRepository.findPasswordByEmail(appUser.getEmail());
    		if(password.equals(appUser.getPassword())) {
    			return "success";
    		} else {
    			return "failure";
    		}
    	} else {
    		//throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    		return "failure";
    	}
    	
    	
    }
}
