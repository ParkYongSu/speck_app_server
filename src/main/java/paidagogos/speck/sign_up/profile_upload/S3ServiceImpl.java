package paidagogos.speck.sign_up.profile_upload;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;


@Component
public class S3ServiceImpl implements S3Service {

    private final AmazonS3Client amazonS3Client;
    private final S3Component s3Component;

    @Autowired
    public S3ServiceImpl(AmazonS3Client amazonS3Client, S3Component s3Component) {
        this.amazonS3Client = amazonS3Client;
        this.s3Component = s3Component;
    }

    @Override
    public void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(s3Component.getBucket(), fileName, inputStream, objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public String getFileUrl(String fileName) {
        return String.valueOf(amazonS3Client.getUrl(s3Component.getBucket(), fileName));
    }

}