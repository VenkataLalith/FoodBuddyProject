package foodBuddy.foodBuddy.SecurityTests;

import foodBuddy.foodBuddy.security.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PasswordEncoderTest {

    @Test
    public void testBCryptPasswordEncoder() {
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        BCryptPasswordEncoder bCryptPasswordEncoder = passwordEncoder.bCryptPasswordEncoder();
        assertNotNull(bCryptPasswordEncoder);
    }
}
