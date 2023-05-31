package br.com.flexpag.reports.factory;

import br.com.flexpag.reports.configurations.JdbcUtils;
import br.com.flexpag.reports.factory.dto.ParamRequest;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class ClientReport implements Report {

    @Override
    public void getReport(ParamRequest paramRequest) {

        try (Connection connection = JdbcUtils.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM client");

            if (paramRequest.status() != null) {
                queryBuilder.append(" AND status = ?");
            }

            if (paramRequest.date() != null) {
                queryBuilder.append(" AND created_at <= ?");
            }

            if (paramRequest.paymentType() != null) {
                queryBuilder.append(" AND payment_type = ?");
            }

            if (paramRequest.clientId() != null) {
                queryBuilder.append(" AND p.client_id = ?");
            }

            PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
            int parameterIndex = 1;

            if (paramRequest.status() != null) {
                statement.setString(parameterIndex++, paramRequest.status().name());
            }

            if (paramRequest.date() != null) {
                statement.setDate(parameterIndex++, java.sql.Date.valueOf(paramRequest.date()));
            }

            if (paramRequest.paymentType() != null) {
                statement.setString(parameterIndex++, paramRequest.paymentType().name());
            }

            if (paramRequest.clientId() != null) {
                statement.setLong(parameterIndex++, paramRequest.clientId());
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Client ID: " + resultSet.getLong("id"));
                System.out.println("Contract Type: " + resultSet.getString("contract"));
                System.out.println("Contract Number: " + resultSet.getLong("contract_number"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("User ID: " + resultSet.getLong("user_id"));
                System.out.println("-----------------------------");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
