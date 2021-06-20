package paidagogos.speck.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paidagogos.speck.model.UserInfo;
import paidagogos.speck.repository.DataRepository;

import java.util.Optional;

@Service
public class LoginService {
    private final DataRepository<UserInfo> dataRepository;

    @Autowired
    public LoginService(DataRepository<UserInfo> dataRepository) {
        this.dataRepository = dataRepository;
    }

    public Optional<UserInfo> getUserInfo(String id) {
        return dataRepository.findById(id);
    }
}
