package paidagogos.speck.update.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public int getCharacter(LinkedHashMap<String, String> body) {
        if (characterRepository.updateCharacter(body)) {
            return 100;
        }
        return 200;
    }
}
