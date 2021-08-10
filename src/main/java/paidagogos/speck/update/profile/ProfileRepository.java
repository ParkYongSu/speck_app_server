package paidagogos.speck.update.profile;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import paidagogos.speck.sign_up.profile_upload.S3Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.UUID;

@Repository
public class ProfileRepository {
    private final JdbcTemplate jdbcTemplate;
    private final S3Service s3Service;

    @Autowired
    public ProfileRepository(DataSource dataSource, S3Service s3Service) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.s3Service = s3Service;
    }

    public String setProfile(MultipartFile file, Profile data) {
        String profile = uploadImage(file);
        String nickname = data.getNickname();
        String email = data.getEmail();
        int result = jdbcTemplate.update("update user_info set profile = ?, nickname = ? where email = ?", profile, nickname, email);
        if (result == 1) {
            return profile;
        }
        return null;
    }

    public boolean setProfile(LinkedHashMap<String, String> body) {
        String nickname = body.get("nickname");
        String email = body.get("email");
        String profile = body.get("profile");
        int result = jdbcTemplate.update("update user_info set nickname = ?, profile = ? where email = ?", nickname, profile,email);
        return (result == 1);
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
