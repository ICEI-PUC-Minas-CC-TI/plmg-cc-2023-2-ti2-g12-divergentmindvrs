package com.divergent_mindverse.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static spark.Spark.*;

import com.divergent_mindverse.CreateDatabase;
import com.divergent_mindverse.dao.ClinicaDAO;
import com.divergent_mindverse.dao.GruposApoioDAO;
import com.divergent_mindverse.dao.ProfissionaisDAO;
import com.divergent_mindverse.dao.UsuariosDAO;
import com.divergent_mindverse.model.Clinica;
import com.divergent_mindverse.model.GrupoApoio;
import com.divergent_mindverse.model.Profissional;
import com.divergent_mindverse.model.Usuario;
import com.google.gson.Gson;

public class Application {
    public static void main(String[] args) {

        CreateDatabase.createDatabase();

        port(8080);
        staticFileLocation("/public");

        Gson gson = new Gson();

        Connection connection = null;
//------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------
        try {
            UsuariosDAO usuariosDAO = new UsuariosDAO();
            
            // Creating a user and retrieving the user ID
            int newUserId = usuariosDAO.insertUsuario(1, "testuser", "testpass", "Test User", "testuser@example.com");
            System.out.println("Created user with ID: " + newUserId);

            // Retrieving a user
            Usuario retrievedUser = usuariosDAO.getUsuario(newUserId);
            if (retrievedUser != null) {
                System.out.println("Retrieved user:");
                System.out.println("ID: " + retrievedUser.getIdUsuario());
                System.out.println("Name: " + retrievedUser.getNome());
                System.out.println("Email: " + retrievedUser.getEmail());
            } else {
                System.out.println("User not found.");
            }

            // Updating a user
            usuariosDAO.updateUsuario(newUserId, 1, "newtestuser", "newtestpass", "New Test User", "newtestuser@example.com");
            System.out.println("User updated.");

            // Deleting a user
            usuariosDAO.deleteUsuario(newUserId);
            System.out.println("User deleted.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------
        try {
        // Create an instance of the ProfissionaisDAO
        ProfissionaisDAO profissionaisDAO = new ProfissionaisDAO();

        // Insert a new profissional and retrieve the profissional ID
        int newProfissionalId = profissionaisDAO.insertProfissional(1, 1);
        System.out.println("Created profissional with ID: " + newProfissionalId);

        // Retrieve a profissional
        Profissional retrievedProfissional = profissionaisDAO.getProfissional(newProfissionalId);
        if (retrievedProfissional != null) {
            System.out.println("Retrieved profissional:");
            System.out.println("ID: " + retrievedProfissional.getIdProfissional());
            System.out.println("Clinica ID: " + retrievedProfissional.getIdClinica());
            System.out.println("Especialidade ID: " + retrievedProfissional.getEspecialidade());
        } else {
            System.out.println("Profissional not found.");
        }

        // Update a profissional
        profissionaisDAO.updateProfissional(newProfissionalId, 2, 2);
        System.out.println("Profissional updated.");

        // Delete a profissional
        profissionaisDAO.deleteProfissional(newProfissionalId);
        System.out.println("Profissional deleted.");

        // Retrieve all profissionais
        List<Profissional> allProfissionais = profissionaisDAO.getAllProfissionais();
        System.out.println("All Profissionais:");
        for (Profissional profissional : allProfissionais) {
            System.out.println("ID: " + profissional.getIdProfissional());
            System.out.println("Clinica ID: " + profissional.getIdClinica());
            System.out.println("Especialidade ID: " + profissional.getEspecialidade());
            System.out.println("------------");
        }
        } catch (Exception e) {
        e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------        
try {
    // Create an instance of the GruposApoioDAO
    GruposApoioDAO gruposApoioDAO = new GruposApoioDAO();

    // Insert a new Grupo de Apoio and retrieve the Grupo de Apoio ID
    int newGrupoApoioId = gruposApoioDAO.insertGrupoApoio(1);
    System.out.println("Created Grupo de Apoio with ID: " + newGrupoApoioId);

    // Retrieve a Grupo de Apoio
    GrupoApoio retrievedGrupoApoio = gruposApoioDAO.getGrupoApoio(newGrupoApoioId);
    if (retrievedGrupoApoio != null) {
        System.out.println("Retrieved Grupo de Apoio:");
        System.out.println("ID: " + retrievedGrupoApoio.getIdGrupo());
        System.out.println("Neurodivergencia ID: " + retrievedGrupoApoio.getIdNeurodivergencia());
    } else {
        System.out.println("Grupo de Apoio not found.");
    }

    // Update a Grupo de Apoio
    gruposApoioDAO.updateGrupoApoio(newGrupoApoioId, 2);
    System.out.println("Grupo de Apoio updated.");

    // Delete a Grupo de Apoio
    gruposApoioDAO.deleteGrupoApoio(newGrupoApoioId);
    System.out.println("Grupo de Apoio deleted.");

    // Retrieve all Grupo de Apoio
    List<GrupoApoio> allGruposApoio = gruposApoioDAO.getAllGruposApoio();
    System.out.println("All Grupos de Apoio:");
    for (GrupoApoio grupoApoio : allGruposApoio) {
        System.out.println("ID: " + grupoApoio.getIdGrupo());
        System.out.println("Neurodivergencia ID: " + grupoApoio.getIdNeurodivergencia());
        System.out.println("------------");
    }
} catch (Exception e) {
    e.printStackTrace();
}
//------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------

        try {
            // Create an instance of the ClinicaDAO
            ClinicaDAO clinicaDAO = new ClinicaDAO();

            // Insert a new clinica and retrieve the clinica ID
            int newClinicaId = clinicaDAO.insertClinica(1);
            System.out.println("Created clinica with ID: " + newClinicaId);

            // Retrieve a clinica
            Clinica retrievedClinica = clinicaDAO.getClinica(newClinicaId);
            if (retrievedClinica != null) {
                System.out.println("Retrieved clinica:");
                System.out.println("ID: " + retrievedClinica.getIdClinica());
                System.out.println("Neurodivergencia ID: " + retrievedClinica.getIdNeurodivergencia());
            } else {
                System.out.println("Clinica not found.");
            }

            // Update a clinica
            clinicaDAO.updateClinica(newClinicaId, 2);
            System.out.println("Clinica updated.");

            // Delete a clinica
            clinicaDAO.deleteClinica(newClinicaId);
            System.out.println("Clinica deleted.");

            // Retrieve all clinicas
            List<Clinica> allClinicas = clinicaDAO.getAllClinicas();
            System.out.println("All Clinicas:");
            for (Clinica clinica : allClinicas) {
                System.out.println("ID: " + clinica.getIdClinica());
                System.out.println("Neurodivergencia ID: " + clinica.getIdNeurodivergencia());
                System.out.println("------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}