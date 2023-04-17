package kz.kbtu.renthouse.service;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.model.*;

import kz.kbtu.renthouse.domain.dto.aws.AmazonS3CustomClient;
import kz.kbtu.renthouse.domain.dto.exception.RentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class AwsServiceImpl implements IAwsService {

    @Autowired
    private AmazonS3CustomClient s3client;


    @Override
    public URL putObj(MultipartFile file, String folderName) throws IOException {

        if(file.getSize()>52428800)
            throw new MultipartException("File too big");

        log.info("Saving file {} to AWS", file.getOriginalFilename());
        String fileName = "";

        if(folderName!=null)
            fileName += folderName+"/";

        fileName = String.valueOf(fileName + new Date().getTime()) + "-" + file.getOriginalFilename();

        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file);
        file.transferTo(convFile);


        s3client.getAmazonS3().putObject(
                new PutObjectRequest(
                        s3client.getBucketName(),
                        fileName, //file.getOriginalFilename(),
                        convFile)
        );

        return s3client.getAmazonS3().getUrl(
                s3client.getBucketName(),
                fileName
        );
    }

    @Override
    public void deleteObj(String fileName) {
        log.info("Delete file {} from AWS", fileName);

        if (!getListObj().contains(fileName)) {
            throw new RentException("File is not present", HttpStatus.NOT_FOUND.value());
        }
        try {
            s3client.getAmazonS3().deleteObject(s3client.getBucketName(), fileName);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
    }

    @Override
    public List<String> getListObj() {
        log.info("Getting list of files from AWS");

        ObjectListing objectListing = s3client.getAmazonS3().listObjects(s3client.getBucketName());
        List<String> list = new ArrayList<>();
        for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
            list.add(os.getKey());
        }
        return list;
    }

    @Override
    public List<URL> getObjUrls() {
        log.info("Getting URL  list of files from AWS");

        List<String> list = getListObj();
        return list.stream().map(g -> s3client.getAmazonS3().getUrl(s3client.getBucketName(), g)).collect(Collectors.toList());
    }

    @Override
    public URL getUrlByName(String name) {
        log.info("Getting URL by {}", name);

        if (!getListObj().contains(name)) {
            throw new RentException("File is not present in bucket", HttpStatus.NOT_FOUND.value());
        }
        return s3client.getAmazonS3().getUrl(s3client.getBucketName(), name);
    }


    public List<URL> getObjects(String folderName, String workspaceId) {
        log.info("Getting URL  list of files from AWS");

        String bucketName = s3client.getBucketName();
        String prefix = "";

        if (workspaceId != null) {
            prefix += workspaceId + "/";
            if (folderName != null)
                prefix += folderName + "/";
        }
        String delimiter = "/";

        List<S3ObjectSummary> objectSummaries = new ArrayList<>();

        getObjects(prefix, bucketName, delimiter, objectSummaries);

       return objectSummaries.stream().map(object-> generateLink(object.getKey())).collect(Collectors.toList());
    }

    public void getObjects(String prefix, String bucketName, String delimiter, List<S3ObjectSummary> objectSummaries) {

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                .withBucketName(bucketName).withPrefix(prefix)
                .withDelimiter(delimiter);
        ObjectListing objects = s3client.getAmazonS3().listObjects(listObjectsRequest);
        objectSummaries.addAll(objects.getObjectSummaries());
    }


    public URL generateLink(String key){
        Date expiration = new Date();
        long expTimeMillis = Instant.now().toEpochMilli();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);

        // Generate the presigned URL.
        System.out.println("Generating pre-signed URL.");

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(s3client.getBucketName(), key)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);
        URL url = s3client.getAmazonS3().generatePresignedUrl(generatePresignedUrlRequest);

        return url;
    }

}
