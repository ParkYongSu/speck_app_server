package paidagogos.speck.update.born_time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class BornTimeUpdateService {
    private final BornTimeUpdateRepository bornTimeUpdateRepository;

    @Autowired
    public BornTimeUpdateService(BornTimeUpdateRepository bornTimeUpdateRepository) {
        this.bornTimeUpdateRepository = bornTimeUpdateRepository;
    }

    public int getBornTime(LinkedHashMap<String, String> body) {
        if (bornTimeUpdateRepository.updateBornTime(body) == 1) {
            return 100;
        }
        return 200;
    }
}
