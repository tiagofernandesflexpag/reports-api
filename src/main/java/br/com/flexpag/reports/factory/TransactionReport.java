package br.com.flexpag.reports.factory;

import br.com.flexpag.reports.configurations.JdbcUtils;
import br.com.flexpag.reports.factory.dto.ParamRequest;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class TransactionReport implements Report{

    @Override
    public void getReport(ParamRequest paramRequest) {

        try (Connection connection = JdbcUtils.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder("SELECT t.*, p.client_id FROM transaction t");
            queryBuilder.append(" JOIN purchase p ON t.purchase_id = p.id WHERE 1 = 1");

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

            System.out.println(resultSet.getArray(2));

            while (resultSet.next()) {
                System.out.println("Transaction ID: " + resultSet.getInt("id"));
                System.out.println("Status: " + resultSet.getString("status"));
                System.out.println("Date: " + resultSet.getDate("created_at"));
                System.out.println("Payment Type: " + resultSet.getString("payment_type"));
                System.out.println("Purchase ID: " + resultSet.getLong("purchase_id"));
                System.out.println("Client ID: " + resultSet.getLong("client_id"));
                System.out.println("-----------------------------");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
