package com.jpdictionary.demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestSQLConnections {
	public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=JPDict;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "20022005";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("Connected Successfully!!!");
            } else {
                System.out.println("Connected Failed!!!");
            }
        } catch (SQLException e) {
            System.err.println("Connection Error: " + e.getMessage());
        }
    }
}
