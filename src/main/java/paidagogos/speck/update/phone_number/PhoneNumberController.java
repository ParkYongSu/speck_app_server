package paidagogos.speck.update.phone_number;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
public class PhoneNumberController {
    private final PhoneNumberService phoneNumberService;

    @Autowired
    public PhoneNumberController(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    @PostMapping("/update/phonenumber")
    public int phoneNumber(@RequestBody LinkedHashMap<String, String> body) {
        return phoneNumberService.getPhoneNumber(body);
    }
}
