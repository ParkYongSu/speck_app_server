package paidagogos.speck.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paidagogos.speck.model.PlaceInfo;
import paidagogos.speck.repository.DataRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceInfoService {
    private final DataRepository<PlaceInfo> dataRepository;

    @Autowired
    public PlaceInfoService(DataRepository<PlaceInfo> dataRepository) {
        this.dataRepository = dataRepository;
    }

    public Optional<List<PlaceInfo>> getPlaceInfo() {
        return dataRepository.findAll();
    }
}
