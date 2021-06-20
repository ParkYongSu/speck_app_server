package paidagogos.speck.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import paidagogos.speck.model.PlaceInfo;
import paidagogos.speck.repository.DataRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class PlaceInfoRepository implements DataRepository<PlaceInfo> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlaceInfoRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<List<PlaceInfo>> findAll() {
        return Optional.of(
                jdbcTemplate.query("select * from place_info inner join operation_time on place_info.code = operation_time.code",
                placeInfoRowMapper()));
    }

    // PlaceInfo 모델에 데이터 세팅
    public void setPlaceInfo(PlaceInfo placeInfo, ResultSet rs) throws SQLException {
        placeInfo.setCode(rs.getString("code"));
        placeInfo.setDoroName(rs.getString("doro_name"));
        placeInfo.setPhoneNumber(rs.getString("phone_number"));
        placeInfo.setName(rs.getString("name"));
        placeInfo.setLat(rs.getDouble("lat"));
        placeInfo.setLng(rs.getDouble("lng"));
        placeInfo.setTodayVisitor(rs.getInt("today_visitor"));
        placeInfo.setNum(rs.getInt("num"));
    }

    // OperationTime 모델에 데이터 세팅
    public void setOperationTime(OperationTime operationTime, ResultSet rs) throws SQLException {
        operationTime.setCode(rs.getString("code"));
        operationTime.setMonday(rs.getString("monday"));
        operationTime.setTuesday(rs.getString("tuesday"));
        operationTime.setWednesday(rs.getString("wednesday"));
        operationTime.setThursday(rs.getString("thursday"));
        operationTime.setFriday(rs.getString("friday"));
        operationTime.setSaturday(rs.getString("saturday"));
        operationTime.setSunday(rs.getString("sunday"));
    }

    @Override
    public Optional<PlaceInfo> findById(String id) {
        return Optional.empty();
    }

    private RowMapper<PlaceInfo> placeInfoRowMapper() {
        return (rs, rowNum) -> {
            PlaceInfo placeInfo = new PlaceInfo();
            OperationTime operationTime = new OperationTime();
            setPlaceInfo(placeInfo, rs);
            setOperationTime(operationTime, rs);
            placeInfo.setOperationTime(operationTime);

            return placeInfo;
        };

    }
}
