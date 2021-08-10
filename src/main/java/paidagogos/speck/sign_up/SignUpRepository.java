package paidagogos.speck.sign_up;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import paidagogos.speck.sign_up.profile_upload.S3Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.UUID;

@Repository
public class SignUpRepository {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;
    private final S3Service s3Service;
    public SignUpRepository(DataSource dataSource, PasswordEncoder passwordEncoder, S3Service s3Service) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.passwordEncoder = passwordEncoder;
        this.s3Service = s3Service;
    }

    public boolean saveUserInfoProfile(MultipartFile multipartFile, SignUpData signUpData, Terms terms) {
        String email = signUpData.getEmail();
        String pw = signUpData.getPw();
        String phoneNumber = signUpData.getPhoneNumber();
        String sex = signUpData.getSex();
        String bornTime = signUpData.getBornTime().toString();
        String nickname = signUpData.getNickname();
        int character = signUpData.getCharacterIndex();
        String profile = (multipartFile == null)?null:uploadImage(multipartFile);

        String service = terms.getService();
        String personalInformationCollection = terms.getPersonalInformationCollection();
        String receiveEventInformation = terms.getReceiveEventInformation();


        int resultUserInfo = jdbcTemplate.update("insert into user_info values(?, ?, ?, ?, ?, ?, ?, ?)",
                email, passwordEncoder.encode(pw), sex, bornTime, nickname, phoneNumber, character, profile);

        int resultTerms = jdbcTemplate.update("insert into terms values(?, ?, ?, ?)",
                email, service, personalInformationCollection, receiveEventInformation);

        return (resultUserInfo == 1) && (resultTerms == 1);
    }

    public boolean saveUserInfoNoneProfile(LinkedHashMap<String, Object> body) {
        String email = body.get("email").toString();
        String pw = body.get("pw").toString();
        String phoneNumber = body.get("phoneNumber").toString();
        String sex = body.get("sex").toString();
        String bornTime = body.get("bornTime").toString();
        String nickname = body.get("nickname").toString();
        int character = Integer.parseInt(body.get("characterIndex").toString());
        String service = body.get("service").toString();
        String personalInformationCollection = body.get("personalInformationCollection").toString();
        String receiveEventInformation = body.get("receiveEventInformation").toString();

        int resultInfo = jdbcTemplate.update("insert into user_info values(?, ?, ?, ?, ? ,?, ?,?)",
                email, passwordEncoder.encode(pw), sex, bornTime, nickname, phoneNumber, character, null);
        int resultTerms = jdbcTemplate.update("insert into terms values(?,? , ?, ?)",
                email, service, personalInformationCollection, receiveEventInformation);

        return ((resultInfo == 1) && (resultTerms == 1));
    }

    public String uploadImage(MultipartFile file) {
        String fileName = createFileName(file.getOriginalFilename()); // 파일이름 생성

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            s3Service.uploadFile(inputStream, objectMetadata, fileName);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발생하였습니다 (%s)", file.getOriginalFilename()));
        }
        return s3Service.getFileUrl(fileName);
    }

    private String createFileName(String originalFileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("잘못된 형식의 파일 (%s) 입니다", fileName));
        }
    }
}
