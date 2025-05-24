package com.farafan;

import oracle.pg.rdbms.pgql.PgqlConnection;
import oracle.pg.rdbms.pgql.PgqlStatement;

import java.sql.*;



public class Main {
    public static void main(String[] args) throws SQLException {

        String jdbcUrl = "jdbc:oracle:thin:@//10.6.150.248:1521/farafan";
        String username = "graph";
        String password = "graph1404";

//        DriverManager.registerDriver(new PgqlJdbcRdbmsDriver());

        System.out.println("connecting to " + jdbcUrl);

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Connected to Oracle DB successfully!");

            PgqlConnection pgqlConn = PgqlConnection.getConnection(conn);
            PgqlStatement pgqlStmt = pgqlConn.createStatement();
            pgqlStmt.execute("SELECT e, v, n FROM MATCH (v)-[e]-(n) ON PROBATE_GRAPH LIMIT 100");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}