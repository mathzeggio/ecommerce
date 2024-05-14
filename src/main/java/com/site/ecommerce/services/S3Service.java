package com.site.ecommerce.services;

import com.site.ecommerce.config.S3Config;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;

public class S3Service {
    private final S3Config s3Client = new S3Config();
    private final S3Client connection = s3Client.getConfig();


    public S3Service() {}

    public void createBucket(String bucketName) {
        CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                .bucket(bucketName)
                .build();

        try {
            connection.createBucket(createBucketRequest);
            System.out.println("Bucket \"" + bucketName + "\" criado com sucesso na região us-east1");
        } catch (SdkClientException e) {
            System.err.println("Erro ao criar bucket: " + e.getMessage());
        }
    }

    public void uploadFile(String bucketName, MultipartFile file){
        try {
            String objectKey = file.getOriginalFilename(); // Use original filename for object key
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectKey)
                    .contentType(file.getContentType()) // Set content type based on uploaded file
                    .build();

            RequestBody requestBody = RequestBody.fromInputStream(file.getInputStream(), file.getSize());

            PutObjectResponse response = connection.putObject(putObjectRequest, requestBody);

            System.out.println(response.toString());
        } catch (SdkClientException e) {
            System.err.println("Erro ao fazer upload: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
