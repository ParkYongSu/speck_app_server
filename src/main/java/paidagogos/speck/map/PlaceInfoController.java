package paidagogos.speck.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import paidagogos.speck.model.PlaceInfo;

import java.util.List;
import java.util.Optional;

@RestController
public class PlaceInfoController {

    private final PlaceInfoService placeInfoService;

    @Autowired
    public PlaceInfoController(PlaceInfoService placeInfoService) {
        this.placeInfoService = placeInfoService;
    }

    @GetMapping("/map")
    public Optional<List<PlaceInfo>> placeInfo() {
        return placeInfoService.getPlaceInfo();
    }

}
