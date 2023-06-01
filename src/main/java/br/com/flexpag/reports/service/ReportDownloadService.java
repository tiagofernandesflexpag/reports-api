package br.com.flexpag.reports.service;

import br.com.flexpag.reports.configurations.AWSConfig;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import lombok.RequiredArgsConstructor;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ReportDownloadService {

    private final AWSConfig awsConfig;

    public String getDownloadLink(String filename) {

        String downloadLink = "https://payments-reports-bucket.s3.amazonaws.com/" + filename;

        return downloadLink;

    }

}
