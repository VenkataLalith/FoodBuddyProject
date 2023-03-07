package foodBuddy.foodBuddy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import foodBuddy.foodBuddy.appuser.AppUser;
import foodBuddy.foodBuddy.appuser.AppUserRole;
import foodBuddy.foodBuddy.appuser.AppUserService;
import foodBuddy.foodBuddy.appuser.UserRepository;
import foodBuddy.foodBuddy.registration.EmailValidator;
import foodBuddy.foodBuddy.registration.token.ConfirmationTokenRepository;

@SpringBootTest
class FoodBuddyApplicationTests {

	@InjectMocks
	private AppUserService appUserService;
	
	@InjectMocks
	private EmailValidator emailValidator;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	
	@Test
	void loadUserByUserNameTestWhenUserExist() {
		String email="timH@email.com";
		UserDetails userDetails = new AppUser("Tim","Hortons","myDemoPassword","timH@email.com",AppUserRole.USER);
		when(userRepository.findByEmail(email)).thenReturn(Optional.of(new AppUser("Tim","Hortons","myDemoPassword","timH@email.com",AppUserRole.USER)));
		assertEquals(userDetails,appUserService.loadUserByUsername(email));
	}
	
	@Test
	void loadUserByNameTestWhenUserDoesNotExist() {
		String email="timH@email.com";
		Optional<AppUser> empty = Optional.empty();
		when(userRepository.findByEmail(email)).thenReturn(empty);
		assertThrows(UsernameNotFoundException.class,() -> appUserService.loadUserByUsername(email));
	}
	
	@Test
	void userLoginSuccessTest() {
		String email="timH@email.com";
		when(userRepository.findByEmail(email)).thenReturn(Optional.of(new AppUser("Tim","Hortons","myDemoPassword","timH@email.com",AppUserRole.USER)));
		when(userRepository.findPasswordByEmail(email)).thenReturn("myDemoPassword");
		AppUser user = new AppUser("Tim","Hortons","myDemoPassword","timH@email.com",AppUserRole.USER);
		assertEquals("success",appUserService.loginUser(user));
	}
	
	@Test
	void userNotExistingLoginFailureTest() {
		String email="timH@email.com";
		Optional<AppUser> empty = Optional.empty();
		when(userRepository.findByEmail(email)).thenReturn(empty);
		when(userRepository.findPasswordByEmail(email)).thenReturn("myDemoPassword");
		AppUser user = new AppUser("Tim","Hortons","myDemoPassword","timH@email.com",AppUserRole.USER);
		assertEquals("failure",appUserService.loginUser(user));
	}
	
	@Test
	void userPasswordMismatchLoginFailureTest() {
		String email="timH@email.com";
		when(userRepository.findByEmail(email)).thenReturn(Optional.of(new AppUser("Tim","Hortons","myDemoPassword","timH@email.com",AppUserRole.USER)));
		when(userRepository.findPasswordByEmail(email)).thenReturn("myDemoPassword");
		AppUser user = new AppUser("Tim","Hortons","IncorrectPassword","timH@email.com",AppUserRole.USER);
		assertEquals("failure",appUserService.loginUser(user));
	}
	
	@Test
	void emailValidatorTest() {
		String email = "timH@email.com";
		assertTrue(emailValidator.test(email));
	}

	@Test
    void loadUserByUserNameTestWhenUserIsNull() {
    String email = "nullUser@example.com";
	Optional<AppUser> empty = Optional.empty();
    when(userRepository.findByEmail(email)).thenReturn(empty);
    assertThrows(UsernameNotFoundException.class, () -> appUserService.loadUserByUsername(email));
}

	@Test
	void userRegistrationWithInvalidEmailTest() {
    String email = "invalidEmail";
	Optional<AppUser> empty = Optional.empty();
    AppUser invalidUser = new AppUser("Invalid", "User", "password", email, AppUserRole.USER);
}

	@Test
	void userRegistrationWithExistingEmailTest() {
    String email = "existingUser@example.com";
    AppUser existingUser = new AppUser("Existing", "User", "password", email, AppUserRole.USER);
    when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));
    assertThrows(IllegalStateException.class, () -> appUserService.signUpUser(existingUser));
}

	
	

}
