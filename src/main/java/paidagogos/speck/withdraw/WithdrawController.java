package paidagogos.speck.withdraw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
public class WithdrawController {
    private final WithdrawService WithdrawService;

    @Autowired
    public WithdrawController(WithdrawService WithdrawService) {
        this.WithdrawService = WithdrawService;
    }

    @PostMapping("/withdraw")
    public int logout(@RequestBody LinkedHashMap<String, String> body) {
        return WithdrawService.withdraw(body.get("email"));
    }
}
