package paidagogos.speck.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceInfoService {
    private final PlaceInfoRepository placeInfoRepository;

    @Autowired
    public PlaceInfoService(PlaceInfoRepository placeInfoRepository) {
        this.placeInfoRepository = placeInfoRepository;
    }

    public Optional<List<PlaceInfo>> getPlaceInfo(String email) {
        return placeInfoRepository.getPlaceInfo(email);
    }
}
