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
import foodBuddy.foodBuddy.appuser.AppUserService;
import foodBuddy.foodBuddy.appuser.UserRepository;
import foodBuddy.foodBuddy.registration.EmailValidator;
import foodBuddy.foodBuddy.registration.token.ConfirmationTokenRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


class FoodBuddyApplicationTests {

	@InjectMocks
	private AppUserService appUserService;
	
	@InjectMocks
	private EmailValidator emailValidator;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Test
	void loadUserByUserNameTestWhenUserExist() {
		String email="timH@email.com";
		UserDetails userDetails = new AppUser("Tim","Hortons","myDemoPassword","timH@email.com");
		when(userRepository.findByEmail(email)).thenReturn(Optional.of(new AppUser("Tim","Hortons","myDemoPassword","timH@email.com")));
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
		AppUser user = new AppUser("Tim","Hortons","myDemoPassword","timH@email.com");
		user.setEmail(email);
		when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
		when(userRepository.findPasswordByEmail(email)).thenReturn("myDemoPassword");
		when(bCryptPasswordEncoder.matches("myDemoPassword","myDemoPassword")).thenReturn(true);
		assertEquals("Login Successful",appUserService.loginUser(user).getMessage());
	}

	@Test
	void userNotExistingLoginFailureTest() {
		String email="timH@email.com";
		Optional<AppUser> empty = Optional.empty();
		when(userRepository.findByEmail(email)).thenReturn(empty);
		when(userRepository.findPasswordByEmail(email)).thenReturn("myDemoPassword");
		AppUser user = new AppUser("Tim","Hortons","myDemoPassword","timH@email.com");
		assertEquals("Login failed",appUserService.loginUser(user).getMessage());
	}

	@Test
	void userPasswordMismatchLoginFailureTest() {
		String email="timH@email.com";
		when(userRepository.findByEmail(email)).thenReturn(Optional.of(new AppUser("Tim","Hortons","myDemoPassword","timH@email.com")));
		when(userRepository.findPasswordByEmail(email)).thenReturn("myDemoPassword");
		AppUser user = new AppUser("Tim","Hortons","IncorrectPassword","timH@email.com");
		assertEquals("Login failed",appUserService.loginUser(user).getMessage());
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
    AppUser invalidUser = new AppUser("Invalid", "User", "password", email);
}

	@Test
	void userRegistrationWithExistingEmailTest() {
    String email = "existingUser@example.com";
    AppUser existingUser = new AppUser("Existing", "User", "password", email);
    when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));
    assertEquals("User Exists", appUserService.signUpUser(existingUser));
}
}
