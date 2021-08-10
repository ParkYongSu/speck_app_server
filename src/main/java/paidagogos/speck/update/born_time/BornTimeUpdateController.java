package paidagogos.speck.update.born_time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
public class BornTimeUpdateController {
    private final BornTimeUpdateService bornTimeUpdateService;

    @Autowired
    public BornTimeUpdateController(BornTimeUpdateService bornTimeUpdateService) {
        this.bornTimeUpdateService = bornTimeUpdateService;
    }

    @PostMapping("/update/borntime")
    public int bornTime(@RequestBody LinkedHashMap<String, String> body) {
        return bornTimeUpdateService.getBornTime(body);
    }
}
