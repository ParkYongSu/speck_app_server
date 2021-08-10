package paidagogos.speck.update.gender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class GenderService {
    private final GenderRepository genderRepository;

    @Autowired
    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public int getGender(LinkedHashMap<String, String> body) {
        if (genderRepository.updateGender(body)) {
            return 100;
        }
        return 200;
    }
}
