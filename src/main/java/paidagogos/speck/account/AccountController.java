package paidagogos.speck.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Optional;

@RestController
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account/set/main")
    public int setMainAccount(@RequestBody LinkedHashMap<String, String> body) {
        return accountService.setMainAccount(body);
    }

    @PostMapping("/account/set/sub")
    public int setSubAccount(@RequestBody LinkedHashMap<String, String> body) {
        return accountService.setSubAccount(body);
    }

    @PostMapping("/account")
    public Optional<Account> getAccountInfo(@RequestBody LinkedHashMap<String, String> body) {
        return accountService.getAccountInfo(body.get("email"));
    }
}
