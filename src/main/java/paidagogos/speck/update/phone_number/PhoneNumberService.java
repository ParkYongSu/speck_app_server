package paidagogos.speck.update.phone_number;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class PhoneNumberService {
    private final PhoneNumberRepository phoneNumberRepository;

    @Autowired
    public PhoneNumberService(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    public int getPhoneNumber(LinkedHashMap<String, String> body) {
        if (phoneNumberRepository.updatePhoneNumber(body)) {
            return 100;
        }
        return 200;
    }
}
