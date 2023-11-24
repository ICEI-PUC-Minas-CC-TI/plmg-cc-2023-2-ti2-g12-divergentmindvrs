package com.divergent_mindverse.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.divergent_mindverse.model.Clinica;

public class ClinicaDAO {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ti2bd";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "PGmo1234";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void executeSQL(String sql) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public int insertClinica(int idNeurodivergencia) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO clinica (id_neurodivergencia) VALUES (?) RETURNING id_clinica";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idNeurodivergencia);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return -1;
    }

    public void updateClinica(int idClinica, int idNeurodivergencia) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "UPDATE clinica SET id_neurodivergencia = ? WHERE id_clinica = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idNeurodivergencia);
            preparedStatement.setInt(2, idClinica);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void deleteClinica(int idClinica) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "DELETE FROM clinica WHERE id_clinica = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idClinica);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public Clinica getClinica(int idClinica) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM clinica WHERE id_clinica = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idClinica);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idNeurodivergencia = resultSet.getInt("id_neurodivergencia");
                return new Clinica(idClinica, idNeurodivergencia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public List<Clinica> getAllClinicas() {
        List<Clinica> clinicas = new ArrayList<>();
        String sql = "SELECT * FROM clinica";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idClinica = resultSet.getInt("id_clinica");
                int idNeurodivergencia = resultSet.getInt("id_neurodivergencia");
                clinicas.add(new Clinica(idClinica, idNeurodivergencia));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return clinicas;
    }
}