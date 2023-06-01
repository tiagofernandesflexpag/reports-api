package br.com.flexpag.reports.service;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

@Service
public class FileService {

    public ByteArrayOutputStream writeArchive(ResultSet resultSet) throws SQLException, IOException {

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

        FileWriter fw = new FileWriter("relatorio.txt");
        fw.write(outputStream.toString());
        fw.close();

        return outputStream;
    }
}

