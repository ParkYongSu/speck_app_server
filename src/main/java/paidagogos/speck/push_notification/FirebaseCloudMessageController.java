package paidagogos.speck.push_notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class FirebaseCloudMessageController {
    private final FirebaseCloudMessageService firebaseCloudMessageService;

    @Autowired
    public FirebaseCloudMessageController(FirebaseCloudMessageService firebaseCloudMessageService) {
        this.firebaseCloudMessageService = firebaseCloudMessageService;
    }

    @PostMapping("/fcm/token")
    public void sendPushNotification(@RequestBody Map<String, String> targetToken) {
        try {
            firebaseCloudMessageService.sendMessageTo(targetToken.get("targetToken"), "테스트", "테스트죵");
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
