package sample;

import java.sql.*;

class DBWorker {
    static final String DB_CONNECTION = "jdbc:mysql://sql7.freesqldatabase.com/sql7278177";
    static final String DB_USER = "sql7278177";
    static final String DB_PASSWORD = "ljhpayjNpm";
    static final String DB_DRIVER = "com.mysql.jdbc.Driver";

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
