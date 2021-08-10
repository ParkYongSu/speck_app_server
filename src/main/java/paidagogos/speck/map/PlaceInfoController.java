package paidagogos.speck.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class PlaceInfoController {

    private final PlaceInfoService placeInfoService;

    @Autowired
    public PlaceInfoController(PlaceInfoService placeInfoService) {
        this.placeInfoService = placeInfoService;
    }

    @PostMapping("/map")
    public Optional<List<PlaceInfo>> placeInfo(@RequestBody LinkedHashMap<String, String> body) {
        return placeInfoService.getPlaceInfo(body.get("email"));
    }
}
