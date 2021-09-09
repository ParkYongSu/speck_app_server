package paidagogos.speck.push_notification.check_token;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.Map;

@Repository
@Transactional
public class CheckTokenRepository {
    private final JdbcTemplate jdbcTemplate;

    public CheckTokenRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean checkExistToken(String email) {
        String checkQuery = "select count(*) from fcm_token where email = ?";
        int count = jdbcTemplate.queryForObject(checkQuery, new Object[]{email}, Integer.class);
        return count != 0;
    }

    public boolean updateToken(Map<String, String> data) {
        String email = data.get("email");
        String token = data.get("token");
        boolean isExist = checkExistToken(email);
        String deleteQuery = "delete from fcm_token where email = ?";
        String insertQuery = "insert into fcm_token(email, token) values(?, ?)";
        // 존재하면 기존 토큰 삭제하고 갱신
        if (isExist) {
            jdbcTemplate.update(deleteQuery, email);
        }
        jdbcTemplate.update(insertQuery, email, token);

        return jdbcTemplate.queryForObject("select count(*) from fcm_token where email = ?", new Object[] {email}, Integer.class) != 0;
    }
}
