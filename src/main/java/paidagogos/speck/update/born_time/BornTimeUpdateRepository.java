package paidagogos.speck.update.born_time;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import paidagogos.speck.repository.DataRepository;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class BornTimeUpdateRepository implements DataRepository<BornTime> {
    private final JdbcTemplate jdbcTemplate;

    public BornTimeUpdateRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<List<BornTime>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<BornTime> findById(String id) {
        return jdbcTemplate.query("select born_time from user_info where email = ?", bornTimeRowMapper(), id).stream().findAny();
    }

    public int updateBornTime(LinkedHashMap<String, String> body) {
        String email = body.get("email");
        String bornTime = body.get("bornTime");

        int result = jdbcTemplate.update("update user_info set born_time = ? where email = ?", bornTime, email);

        return result;
    }

    public RowMapper<BornTime> bornTimeRowMapper() {
        return (rs, rowNum) -> {
            BornTime bornTime = new BornTime();
            bornTime.setBornTime(rs.getString("born_time"));

            return bornTime;
        };
    }
}
