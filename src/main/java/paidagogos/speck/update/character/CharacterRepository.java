package paidagogos.speck.update.character;

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
public class CharacterRepository implements DataRepository<Character> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CharacterRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<List<Character>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<Character> findById(String id) {
        return jdbcTemplate.query("select character_index from user_info where email = ?", characterRowMapper(), id).stream().findAny();
    }

    public boolean updateCharacter(LinkedHashMap<String, String> body) {
        String email = body.get("email");
        int character = Integer.parseInt(body.get("character"));

        int result = jdbcTemplate.update("update user_info set character_index = ? where email = ?", character, email);

        return (result == 1);
    }

    public RowMapper<Character> characterRowMapper() {
        return (rs, rowNum) -> {
            Character character = new Character();
            character.setCharacter(rs.getInt("character_index"));
            return character;
        };
    }
}
