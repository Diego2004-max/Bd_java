package com.ucc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Incluye la base de datos y opciones útiles para local
    private static final String URL ="jdbc:mysql://localhost:3306/sakila?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "JedCRM2024!";

    private static Connection myConn;

    public static Connection getInstanceConnection() throws SQLException {
        // reusa si existe y no está cerrada
        if (myConn == null || myConn.isClosed()) {
            myConn = DriverManager.getConnection(URL, USER, PASS);
        }
        return myConn;
    }
}
