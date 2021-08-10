package paidagogos.speck.withdraw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class WithdrawRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WithdrawRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean deleteUser(String id) {
        int result = jdbcTemplate.update("delete from user_info where email = ?", id);

        return (result == 1);
    }
}
