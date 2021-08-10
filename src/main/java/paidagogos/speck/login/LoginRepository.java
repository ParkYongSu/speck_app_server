package paidagogos.speck.login;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import paidagogos.speck.repository.DataRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class LoginRepository implements DataRepository<UserInfo> {
    private final JdbcTemplate jdbcTemplate;

    public LoginRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<List<UserInfo>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<UserInfo> findById(String id) {
        return jdbcTemplate.query("select * from user_info where email = ?", userInfoRowMapper(), id).stream().findAny();
    }

    public RowMapper<UserInfo> userInfoRowMapper() {
        return (rs, rowNum) -> {
            UserInfo userInfo = new UserInfo();
            userInfo.setEmail(rs.getString("email"));
            userInfo.setNickname(rs.getString("nickname"));
            userInfo.setBornTime(rs.getString("born_time"));
            userInfo.setSex(rs.getString("sex"));
            userInfo.setPhoneNumber(rs.getString("phone_number"));
            userInfo.setCharacterIndex(rs.getInt("character_index"));
            userInfo.setProfile(rs.getString("profile"));

            return userInfo;
        };
    }
}
