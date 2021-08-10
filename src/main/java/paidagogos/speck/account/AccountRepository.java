package paidagogos.speck.account;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import paidagogos.speck.repository.DataRepository;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository implements DataRepository<Account> {
    private final JdbcTemplate jdbcTemplate;

    public AccountRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<List<Account>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<Account> findById(String id) {
        return jdbcTemplate.query("select * from account where email = ?" , accountRowMapper(), id).stream().findAny();
    }

    public int savaMainAccount(LinkedHashMap<String, String> body) {
        String email = body.get("email");
        String mainAccountOwner = body.get("mainAccountOwner");
        String mainAccountNumber = body.get("mainAccountNumber");
        String mainAccountBank = body.get("mainAccountBank");

        int result = jdbcTemplate.update(
                "update account set main_account_owner = ?, main_account_number = ?, main_account_bank = ? where email = ?",
                mainAccountOwner, mainAccountNumber, mainAccountBank, email
        );
        return result;
    }

    public int saveSubAccount(LinkedHashMap<String, String> body) {
        String email = body.get("email");
        String subAccountOwner = body.get("subAccountOwner");
        String subAccountNumber = body.get("subAccountNumber");
        String subAccountBank = body.get("subAccountBank");
        int result = jdbcTemplate.update(
                "update account set sub_account_owner = ?, sub_account_number = ?, sub_account_bank = ? where email = ?",
                subAccountOwner, subAccountNumber, subAccountBank, email
        );
        return result;
    }

    public RowMapper<Account> accountRowMapper() {
        return (rs, rowNum) -> {
            Account account = new Account();
            account.setMainAccountOwner(rs.getString("main_account_owner"));
            System.out.println(rs.getString("main_account_owner"));
            account.setMainAccountNumber(rs.getString("main_account_number"));
            account.setMainAccountBank(rs.getString("main_account_bank"));
            account.setSubAccountOwner(rs.getString("sub_account_owner"));
            account.setSubAccountNumber(rs.getString("sub_account_number"));
            account.setSubAccountBank(rs.getString("sub_account_bank"));
            return account;
        };
    }
}
