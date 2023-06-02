package br.com.flexpag.reports.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AWSFileUploaderService {

    private final AmazonS3 amazonS3;

    public void uploadFileToBucket(File file, String bucketName, String fileName) throws IOException {

        FileInputStream fis = new FileInputStream(file);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.length());

        PutObjectRequest request = new PutObjectRequest(bucketName, fileName, fis, metadata);

        amazonS3.putObject(request);

        fis.close();
    }

}

