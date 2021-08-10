package paidagogos.speck.update.phone_number;

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
public class PhoneNumberRepository implements DataRepository<PhoneNumber> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PhoneNumberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<List<PhoneNumber>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<PhoneNumber> findById(String id) {
        return jdbcTemplate.query("select phone_number from user_info where email = ?",
                phoneNumberRowMapper(), id).stream().findAny();
    }

    public boolean updatePhoneNumber(LinkedHashMap<String, String> body) {
        String email = body.get("email");
        String phoneNumber = body.get("phoneNumber");

        int result = jdbcTemplate.update("update user_info set phone_number = ? where email = ?", phoneNumber, email);

        return (result == 1);
    }

    public RowMapper<PhoneNumber> phoneNumberRowMapper() {
        return (rs, rowNum) -> {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setPhoneNumber(rs.getString("phone_number"));
            return phoneNumber;
        };
    }
}
