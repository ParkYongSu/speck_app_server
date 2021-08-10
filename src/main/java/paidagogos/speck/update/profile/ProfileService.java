package paidagogos.speck.update.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileUpdateResult update(MultipartFile file, Profile profile) {
        ProfileUpdateResult profileUpdateResult = new ProfileUpdateResult();
        if (profileRepository.setProfile(file, profile) != null) {
            profileUpdateResult.setProfile(profileRepository.setProfile(file, profile));
            profileUpdateResult.setCode(100);
            return profileUpdateResult;
        }
        return null;
    }

    public int update(LinkedHashMap<String, String> body) {
        if (profileRepository.setProfile(body)) {
            return 100;
        }
        return 200;
    }
}
