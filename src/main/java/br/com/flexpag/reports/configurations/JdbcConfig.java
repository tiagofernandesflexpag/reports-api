package br.com.flexpag.reports.configurations;

import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class JdbcConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "minha_senha";

    public static Connection getConnection() throws SQLException {
        try {

            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            throw new SQLException("Falha ao se conectar com o Driver da DB!");

        }
    }
}
