package paidagogos.speck.push_notification.check_token;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CheckTokenController {
    private final CheckTokenService checkTokenService;

    public CheckTokenController(CheckTokenService checkTokenService) {
        this.checkTokenService = checkTokenService;
    }

    @PostMapping("/fcm/check/token")
    public int updateToken(@RequestBody Map<String, String> body) {
        if (checkTokenService.updateToken(body)) {
            return 202;
        }
        return 400;
    }
}
