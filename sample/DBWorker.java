package sample;

import java.sql.*;

class DBWorker {
    static final String DB_CONNECTION = "jdbc:postgresql://127.0.0.1:5432/testMaker";
    static final String DB_USER = "postgres";
    static final String DB_PASSWORD = "Admin1234";
    static final String DB_DRIVER = "org.postgresql.Driver";

    static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
