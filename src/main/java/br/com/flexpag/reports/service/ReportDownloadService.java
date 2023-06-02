package br.com.flexpag.reports.service;

import br.com.flexpag.reports.configurations.JdbcUtils;
import br.com.flexpag.reports.factory.dto.ReportResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class ReportDownloadService {

    public ReportResponseDTO getDownloadLink(String fileName) throws SQLException {

        try(Connection connection = JdbcUtils.getConnection()){

            String query = "SELECT name, link from report where name = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, fileName);

            ResultSet file = statement.executeQuery();

            if (file.next()) {

                String name = file.getString("name");
                String link = file.getString("link");
                var report = new ReportResponseDTO(name, link);

                statement.close();
                file.close();

                return report;

            }else{

                throw new EntityNotFoundException("O arquivo com o nome enviado não foi encontrado!");

            }

        }catch (SQLException e){

            throw new SQLException(e);

        }

    }

}
