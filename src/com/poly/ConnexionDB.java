package com.poly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDB {

    // MySQL Configuration - adjust credentials as needed
    private static final String URL = "jdbc:mysql://localhost:3306/testdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root"; // change if different
    private static final String PASSWORD = ""; // change if different

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL introuvable ! Verifiez pom.xml");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur de connexion MySQL:");
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
        }

        return conn;
    }
}