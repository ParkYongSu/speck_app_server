package paidagogos.speck.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import paidagogos.speck.login.UserInfo;
import paidagogos.speck.repository.DataRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
                jdbcTemplate.query(
                        "select * from place_info left join operation_time on place_info.code = operation_time.code;",
                placeInfoRowMapper()));
    }

    @Override
    public Optional<PlaceInfo> findById(String id) {
        return Optional.empty();
    }

    public Optional<List<PlaceImage>> getImages(String code) {
        return Optional.of(
                jdbcTemplate.query(
                "select image_path from place_image where code = ?"
                        , placeImageRowMapper(), code
        ));
    }
    public Optional<List<PlaceInfo>> getPlaceInfo(String email) {
        if (!jdbcTemplate.query("select * from user_info where email = ?", userInfoRowMapper(), email).isEmpty()) {
            return findAll();
        }
        return Optional.empty();
    }
    // PlaceInfo 모델에 데이터 세팅
    public void setPlaceInfo(PlaceInfo placeInfo, ResultSet rs) throws SQLException {
        placeInfo.setDoroName(rs.getString("doro_name"));
        placeInfo.setPhoneNumber(rs.getString("phone_number"));
        placeInfo.setName(rs.getString("name"));
        placeInfo.setLat(rs.getDouble("lat"));
        placeInfo.setLng(rs.getDouble("lng"));
        placeInfo.setTodayVisitor(rs.getInt("today_visitor"));
        placeInfo.setNum(rs.getInt("num"));
        placeInfo.setCode(rs.getString("code"));
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

    private RowMapper<PlaceInfo> placeInfoRowMapper() {
        return (rs, rowNum) -> {
            PlaceInfo placeInfo = new PlaceInfo();
            OperationTime operationTime = new OperationTime();
            setPlaceInfo(placeInfo, rs);
            setOperationTime(operationTime, rs);
            placeInfo.setOperationTime(operationTime);
            placeInfo.setPlaceImage(getImages(placeInfo.getCode()).get());
            return placeInfo;
        };
    }


    public void setUserInfo(UserInfo userInfo, ResultSet rs) throws SQLException {
        userInfo.setEmail(rs.getString("email"));
        userInfo.setSex(rs.getString("sex"));
        userInfo.setBornTime(rs.getString("born_time"));
        userInfo.setNickname(rs.getString("nickname"));
        userInfo.setPhoneNumber(rs.getString("phone_number"));
        userInfo.setCharacterIndex(rs.getInt("character_index"));
    }

    private RowMapper<UserInfo> userInfoRowMapper() {
        return (rs, rowNum) -> {
            UserInfo userInfo = new UserInfo();
            userInfo.setEmail(rs.getString("email"));
            setUserInfo(userInfo, rs);
            return userInfo;
        };
    }

    private RowMapper<PlaceImage>  placeImageRowMapper() {
        return (rs, rowNum) -> {
            PlaceImage placeImage = new PlaceImage();
            placeImage.setImagePath(rs.getString("image_path"));
            return placeImage;
        };
    }
}
