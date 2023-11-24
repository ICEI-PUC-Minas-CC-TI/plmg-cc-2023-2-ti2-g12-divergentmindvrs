package com.divergent_mindverse.app.controllers;

import com.divergent_mindverse.dao.ClinicaDAO;
import com.divergent_mindverse.model.Clinica;
import com.google.gson.Gson;
import spark.Spark;

public class ClinicaController {

    public ClinicaController(final ClinicaDAO clinicaDAO) {
        Spark.port(4567);

        // Define a route to insert a new Clinica
        Spark.post("/clinicas", (request, response) -> {
            int idNeurodivergencia = Integer.parseInt(request.queryParams("idNeurodivergencia"));

            int idClinica = clinicaDAO.insertClinica(idNeurodivergencia);
            response.status(201); // Created
            return idClinica;
        });

        // Define a route to update an existing Clinica
        Spark.put("/clinicas/:id", (request, response) -> {
            int idClinica = Integer.parseInt(request.params(":id"));
            int idNeurodivergencia = Integer.parseInt(request.queryParams("idNeurodivergencia"));

            clinicaDAO.updateClinica(idClinica, idNeurodivergencia);
            response.status(204); // No Content
            return "";
        });

        // Define a route to delete a Clinica
        Spark.delete("/clinicas/:id", (request, response) -> {
            int idClinica = Integer.parseInt(request.params(":id"));
            clinicaDAO.deleteClinica(idClinica);
            response.status(204); // No Content
            return "";
        });

        // Define a route to get a specific Clinica
        Spark.get("/clinicas/:id", (request, response) -> {
            int idClinica = Integer.parseInt(request.params(":id"));
            Clinica clinica = clinicaDAO.getClinica(idClinica);
            if (clinica != null) {
                response.type("application/json"); // Set response type to JSON
                return new Gson().toJson(clinica); // Convert Clinica object to JSON
            } else {
                response.status(404); // Not Found
                return "Clinica not found";
            }
        });

        // Define a route to get all Clinicas
        Spark.get("/clinicas", (request, response) -> {
            response.type("application/json"); // Set response type to JSON
            return new Gson().toJson(clinicaDAO.getAllClinicas()); // Convert List<Clinica> to JSON
        });
    }

    public static void main(String[] args) {
        // Set up the database connection and ClinicaDAO
        ClinicaDAO clinicaDAO = new ClinicaDAO();

        // Create and configure the ClinicaController
        new ClinicaController(clinicaDAO);
    }
}
