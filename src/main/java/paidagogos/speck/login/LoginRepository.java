package paidagogos.speck.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import paidagogos.speck.model.UserInfo;
import paidagogos.speck.repository.DataRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class LoginRepository implements DataRepository<UserInfo> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
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
            userInfo.setBirthday(rs.getString("birthday"));
            userInfo.setSex(rs.getString("sex"));
            userInfo.setCharacter(rs.getInt("character_index"));

            return userInfo;
        };
    }
}
