package com.divergent_mindverse.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.divergent_mindverse.model.Profissional;

public class ProfissionaisDAO {
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
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insertProfissional(int idClinica, int especialidade) {
        String sql = "INSERT INTO profissionais (id_clinica, especialidade) VALUES (?, ?) RETURNING id_profissional";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idClinica);
            preparedStatement.setInt(2, especialidade);
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

    public void updateProfissional(int idProfissional, int idClinica, int especialidade) {
        String sql = "UPDATE profissionais SET id_clinica = ?, especialidade = ? WHERE id_profissional = ?";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idClinica);
            preparedStatement.setInt(2, especialidade);
            preparedStatement.setInt(3, idProfissional);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void deleteProfissional(int idProfissional) {
        String sql = "DELETE FROM profissionais WHERE id_profissional = ?";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idProfissional);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public Profissional getProfissional(int idProfissional) {
        String sql = "SELECT * FROM profissionais WHERE id_profissional = ?";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idProfissional);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idClinica = resultSet.getInt("id_clinica");
                int especialidade = resultSet.getInt("especialidade");
                return new Profissional(idProfissional, idClinica, especialidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public List<Profissional> getAllProfissionais() {
        List<Profissional> profissionais = new ArrayList<>();
        String sql = "SELECT * FROM profissionais";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idProfissional = resultSet.getInt("id_profissional");
                int idClinica = resultSet.getInt("id_clinica");
                int especialidade = resultSet.getInt("especialidade");
                profissionais.add(new Profissional(idProfissional, idClinica, especialidade));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return profissionais;
    }
}
