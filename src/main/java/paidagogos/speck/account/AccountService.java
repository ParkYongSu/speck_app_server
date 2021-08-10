package paidagogos.speck.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public int setMainAccount(LinkedHashMap<String, String> body) {
        if (accountRepository.savaMainAccount(body) == 1) {
            return 100;
        }
        return 200;
    }

    public int setSubAccount(LinkedHashMap<String, String> body) {
        if (accountRepository.saveSubAccount(body) == 1) {
            return 100;
        }
        return 200;
    }

    public Optional<Account> getAccountInfo(String email) {
        return accountRepository.findById(email);
    }
}
