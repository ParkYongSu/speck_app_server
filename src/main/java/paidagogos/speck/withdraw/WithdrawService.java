package paidagogos.speck.withdraw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WithdrawService {
    private final WithdrawRepository logoutRepository;

    @Autowired
    public WithdrawService(WithdrawRepository logoutRepository) {
        this.logoutRepository = logoutRepository;
    }

    public int withdraw(String email) {
        if (logoutRepository.deleteUser(email)) {
            return 100;
        }
        return 200;
    }
}
