package com.farafan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.pg.rdbms.pgql.jdbc.PgqlJdbcRdbmsDriver;

public class Main {
    public static void main(String[] args) throws SQLException {

        String jdbcUrl = "jdbc:oracle:thin:@//hostname:1521/XE";
        String username = "username";
        String password = "password";

        DriverManager.registerDriver(new PgqlJdbcRdbmsDriver());

        System.out.println("connecting to " + jdbcUrl);

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Connected to Oracle DB successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}