package paidagogos.speck.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import paidagogos.speck.model.UserInfo;

import java.util.HashMap;
import java.util.Optional;

@RestController
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public Optional<UserInfo> userInfo(@RequestBody HashMap<String, String> body) {
//        return loginService.getUserInfo(email);
        return loginService.getUserInfo(body.get("email"));
    }
}
