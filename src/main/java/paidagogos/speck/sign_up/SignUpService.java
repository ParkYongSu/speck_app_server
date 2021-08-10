package paidagogos.speck.sign_up;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;

@Service
public class SignUpService {
    private final SignUpRepository signUpRepository;

    @Autowired
    public SignUpService(SignUpRepository signUpRepository) {
        this.signUpRepository = signUpRepository;
    }


    public int signUpProfile(MultipartFile multipartFile, SignUpData signUpData, Terms terms) {
        if (signUpRepository.saveUserInfoProfile(multipartFile, signUpData, terms)) {
            return 100;
        }
        return 200;
    }

    public int signUpNoneProfile(LinkedHashMap<String, Object> body) {
        if (signUpRepository.saveUserInfoNoneProfile(body)) {
            return 100;
        }
        return 200;
    }
}
