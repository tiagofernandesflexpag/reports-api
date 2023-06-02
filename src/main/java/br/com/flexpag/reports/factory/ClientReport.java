package br.com.flexpag.reports.factory;

import br.com.flexpag.reports.configurations.JdbcUtils;
import br.com.flexpag.reports.factory.dto.ParamRequest;
import br.com.flexpag.reports.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ClientReport implements Report {

    private final FileService fileService;

    @Override
    public ByteArrayOutputStream getReport(ParamRequest paramRequest) {

        try (Connection connection = JdbcUtils.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder("SELECT id FROM client WHERE 1 = 1");

            if (paramRequest.date() != null) {
                queryBuilder.append(" AND created_at <= ?");
            }

            if (paramRequest.clientId() != null) {
                queryBuilder.append(" AND id = ?");
            }

            PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
            int parameterIndex = 1;

            if (paramRequest.date() != null) {
                statement.setDate(parameterIndex++, java.sql.Date.valueOf(paramRequest.date()));
            }

            if (paramRequest.clientId() != null) {
                statement.setLong(parameterIndex++, paramRequest.clientId());
            }

            ResultSet resultSet = statement.executeQuery();
            ByteArrayOutputStream outputStream = fileService.writeArchive(resultSet, paramRequest.reportType());

            resultSet.close();
            statement.close();

            return outputStream;

        } catch (SQLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

        return null;

    }

}
