package paidagogos.speck.update.gender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
public class GenderController {
    private final GenderService genderService;

    @Autowired
    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    @PostMapping("/update/gender")
    public int gender(@RequestBody LinkedHashMap<String, String> body) {
        return genderService.getGender(body);
    }
}
