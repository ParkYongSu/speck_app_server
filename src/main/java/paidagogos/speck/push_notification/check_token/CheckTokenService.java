package paidagogos.speck.push_notification.check_token;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CheckTokenService {
    private final CheckTokenRepository checkTokenRepository;

    public CheckTokenService(CheckTokenRepository checkTokenRepository) {
        this.checkTokenRepository = checkTokenRepository;
    }

    public boolean updateToken(Map<String, String> data) {
        return checkTokenRepository.updateToken(data);
    }
}
