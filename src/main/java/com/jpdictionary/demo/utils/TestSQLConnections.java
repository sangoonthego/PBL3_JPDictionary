package com.jpdictionary.demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestSQLConnections {
	private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=JPDictionary;encrypt=true;trustServerCertificate=true";
	private static final String user = "sa";
	private static final String password = "20022005";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	public static void main(String[] args) {
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
