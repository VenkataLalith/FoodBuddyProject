package foodBuddy.foodBuddy.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "/api/v1/login")
@AllArgsConstructor
public class LoginController {
	private LoginService loginService;
    @PostMapping
    public String login(@RequestBody LoginRequest request){
        return loginService.login(request);
    }

}
