package kz.kbtu.renthouse.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import kz.kbtu.renthouse.domain.dto.aws.AmazonS3CustomClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsS3Configuration {

    @Value("${bucket.name}")
    private String bucketName;
    @Value("${access.key}")
    private String accessKey;
    @Value("${access.secret}")
    private String accessSecret;

    @Value("${access.service-endpoint}")
    private String serviceEndpoint;

    @Value("${aws.region}")
    private String awsRegion;


    @Bean
    public AWSCredentials basicAWSCredentials() {
        return new BasicAWSCredentials(accessKey, accessSecret);
    }

    @Bean
    public AmazonS3CustomClient s3client() {
        AmazonS3 psClientS3 = AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                //.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(serviceEndpoint, awsRegion))
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials()))
                .build();

        return new AmazonS3CustomClient(
                psClientS3,
                bucketName
        );
    }

}
