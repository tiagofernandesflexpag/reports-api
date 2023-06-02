package br.com.flexpag.reports.service;

import br.com.flexpag.reports.configurations.JdbcUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class GenerateDownloadLinkService {

    public void generateDownloadLinkService(String fileName) throws SQLException {

        String baseLink = "https://payments-reports-bucket.s3.amazonaws.com/";

        String fullLink = baseLink + fileName;

        try(Connection connection = JdbcUtils.getConnection()){

            String query = "INSERT INTO report (name, link) VALUES (?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, fileName);
            statement.setString(2, fullLink);

            statement.executeUpdate();
            statement.close();

        }catch (SQLException e){

            throw new SQLException(e);

        }

    }
}
