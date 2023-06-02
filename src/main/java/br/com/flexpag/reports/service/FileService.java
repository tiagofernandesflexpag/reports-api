package br.com.flexpag.reports.service;

import br.com.flexpag.reports.configurations.AWSConfig;
import br.com.flexpag.reports.factory.enums.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class FileService {

    private final AWSFileUploader awsFileUploader;
    private final GenerateDownloadLinkService generateDownloadLinkService;

    public ByteArrayOutputStream writeArchive(ResultSet resultSet, ReportType reportType) throws SQLException, IOException {

        ResultSetMetaData metaData = resultSet.getMetaData();
        Integer columnCount = metaData.getColumnCount();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);

        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            writer.write(columnName);
            if (i < columnCount) {
                writer.write(",");
            }
        }
        writer.write("\n");

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                Object value = resultSet.getObject(i);
                writer.write(value != null ? value.toString() : "");
                if (i < columnCount) {
                    writer.write(",");
                }
            }
            writer.write("\n");
        }

        writer.flush();
        writer.close();

        FileWriter tempFw = new FileWriter("relatorio.xls");
        tempFw.write(outputStream.toString());
        tempFw.close();

        File file = new File("relatorio.xls");
        String bucketName = "payments-reports-bucket";
        String fileName = "relatorio" + "_" + reportType + "_" + UUID.randomUUID() + ".xls";

        try {

            awsFileUploader.uploadFileToBucket(file, bucketName, fileName);

            file.delete();

            generateDownloadLinkService.generateDownloadLinkService(fileName);

        }catch (Exception e){

            throw new RuntimeException(e);

        }

        return outputStream;

    }
}

