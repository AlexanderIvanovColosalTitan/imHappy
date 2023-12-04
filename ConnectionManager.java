package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionManager {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/sad";
                String username = "root";
                String password = "root";
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Соединение с базой данных установлено.");
            } catch (SQLException e) {
                System.out.println("Ошибка при установлении соединения с базой данных: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Соединение с базой данных закрыто.");
            } catch (SQLException e) {
                System.out.println("Ошибка при закрытии соединения с базой данных: " + e.getMessage());
            }
        }
    }
}
