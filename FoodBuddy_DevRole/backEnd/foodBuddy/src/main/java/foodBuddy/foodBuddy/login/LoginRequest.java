package foodBuddy.foodBuddy.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginRequest {
	    private  String password;
	    private  String email;
}
