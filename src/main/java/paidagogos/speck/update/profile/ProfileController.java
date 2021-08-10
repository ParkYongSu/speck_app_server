package paidagogos.speck.update.profile;

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
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/update/profile")
    public ProfileUpdateResult updateProfile(@RequestPart("profile") MultipartFile file, @RequestPart("data")String data) {
        Profile profile = new Profile();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            profile = objectMapper.readValue(data, Profile.class);
        }
        catch (IOException  e) {
            System.out.println(e);
        }
        return profileService.update(file, profile);
    }

    @PostMapping("/update/profile/none")
    public int updateProfile(@RequestBody LinkedHashMap<String, String> body) {
        return profileService.update(body);
    }


}
