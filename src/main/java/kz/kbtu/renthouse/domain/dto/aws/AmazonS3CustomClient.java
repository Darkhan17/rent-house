package kz.kbtu.renthouse.domain.dto.aws;

import com.amazonaws.services.s3.AmazonS3;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmazonS3CustomClient {
    private AmazonS3 amazonS3;
    private String bucketName;

}
