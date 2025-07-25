package com.farafan;

import oracle.pg.rdbms.pgql.PgqlStatement;
import oracle.pg.rdbms.pgql.PgqlConnection;
import oracle.pg.rdbms.pgql.jdbc.PgqlJdbcRdbmsDriver;
import oracle.pgql.lang.PgqlException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgqlCreate {

    public static void main(String[] args) throws SQLException, PgqlException {

        String jdbcUrl = "jdbc:oracle:thin:@//10.6.150.248:1521/farafan";
        String username = "graph";
        String password = "graph1404";

        Connection conn = null;
        PgqlStatement pgqlStmt = null;

        try {
            //Get a jdbc connection
            DriverManager.registerDriver(new PgqlJdbcRdbmsDriver());
            conn = DriverManager.getConnection(jdbcUrl, username, password);
            conn.setAutoCommit(false);

            // Get a PGQL connection
            PgqlConnection pgqlConn = PgqlConnection.getConnection(conn);

            // Create a PGQL Statement
            pgqlStmt = pgqlConn.createStatement();

            // Execute PGQL Query
            String pgql = "SELECT e, v, n FROM MATCH (v)-[e]-(n) ON PROBATE_GRAPH LIMIT 100";

            // Print the results
            pgqlStmt.execute(pgql);

            while (pgqlStmt.getResultSet().next()) {
                pgqlStmt.getResultSet().print();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close the statement
            if (pgqlStmt != null) {
                pgqlStmt.close();
            }
            // close the connection
            if (conn != null) {
                conn.close();
            }
        }
    }
}


