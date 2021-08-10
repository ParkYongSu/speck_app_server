package paidagogos.speck.update.gender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import paidagogos.speck.repository.DataRepository;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class GenderRepository implements DataRepository<Gender> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GenderRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<List<Gender>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<Gender> findById(String id) {
        return jdbcTemplate.query("select sex from user_info where email = ?", genderRowMapper(), id).stream().findAny();
    }

    public boolean updateGender(LinkedHashMap<String, String> body) {
        String email = body.get("email");
        String gender = body.get("gender");

        int result = jdbcTemplate.update("update user_info set sex = ? where email = ?", gender, email);

        return (result == 1);
    }

    public RowMapper<Gender> genderRowMapper() {
        return (rs, rowNum) -> {
            Gender gender = new Gender();
            gender.setGender(rs.getString("sex"));
            return gender;
        };
    }
}
