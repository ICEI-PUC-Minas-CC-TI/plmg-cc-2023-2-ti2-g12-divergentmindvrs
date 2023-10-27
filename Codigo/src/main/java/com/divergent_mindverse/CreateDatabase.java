package com.divergent_mindverse;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {
    public static void createDatabase() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "PGmo1234";

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                if (!tableExists(connection, "ti2bd")) {
                    String scriptContent = readSQLScript("/create_database.sql");

                    try (Statement statement = connection.createStatement()) {
                        statement.execute(scriptContent);
                        System.out.println("Database created successfully.");
                    }
                } else {
                    System.out.println("Table already exists. Skipping creation.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    private static boolean tableExists(Connection connection, String tableName) {
        String query = "SELECT to_regclass('public." + tableName + "')";
        try (Statement statement = connection.createStatement()) {
            return statement.executeQuery(query).next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String readSQLScript(String scriptPath) {
        try (InputStream inputStream = CreateDatabase.class.getResourceAsStream(scriptPath);
             InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            StringBuilder scriptContent = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                scriptContent.append(line).append("\n");
            }
            return scriptContent.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
