package paidagogos.speck.sign_up;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashMap;

@RestController
public class SignUpController{
    private final SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }


    @PostMapping(value = "/signup/profile")
    public int signUpProfile(@RequestPart("profile") MultipartFile multipartFile,
                      @RequestPart("signUpData") String signUpData, @RequestPart("terms") String terms) {
        SignUpData setUserInfo =  setSignUpData(signUpData);
        Terms setTerms = setTerms(terms);
        return signUpService.signUpProfile(multipartFile, setUserInfo, setTerms);
    }

    @PostMapping(value = "/signup/none-profile")
    public int signUpNoneProfile(@RequestBody LinkedHashMap<String, Object> body) {
        return signUpService.signUpNoneProfile(body);
    }

    public SignUpData setSignUpData(String signUpData) {
        SignUpData result = new SignUpData();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.readValue(signUpData, SignUpData.class);
        }
        catch (IOException e) {
            System.out.println(e);
        }

        return result;
    }

    public Terms setTerms(String terms) {
        Terms result = new Terms();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.readValue(terms, Terms.class);
        }
        catch (IOException e) {
            System.out.println(e);
        }

        return result;
    }


}
