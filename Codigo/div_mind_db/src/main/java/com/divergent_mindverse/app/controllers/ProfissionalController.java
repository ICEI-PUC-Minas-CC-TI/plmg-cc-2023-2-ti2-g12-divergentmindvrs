package com.divergent_mindverse.app.controllers;

import com.divergent_mindverse.dao.ProfissionaisDAO;
import com.divergent_mindverse.model.Profissional;
import com.google.gson.Gson;
import spark.Spark;

public class ProfissionalController {

    public ProfissionalController(final ProfissionaisDAO profissionaisDAO) {
        Spark.port(4567);

        // Define a route to insert a new Profissional
        Spark.post("/profissionais", (request, response) -> {
            int idClinica = Integer.parseInt(request.queryParams("idClinica"));
            int especialidade = Integer.parseInt(request.queryParams("especialidade"));
            
            int idProfissional = profissionaisDAO.insertProfissional(idClinica, especialidade);
            response.status(201); // Created
            return idProfissional;
        });

        // Define a route to update an existing Profissional
        Spark.put("/profissionais/:id", (request, response) -> {
            int idProfissional = Integer.parseInt(request.params(":id"));
            int idClinica = Integer.parseInt(request.queryParams("idClinica"));
            int especialidade = Integer.parseInt(request.queryParams("especialidade"));
            
            profissionaisDAO.updateProfissional(idProfissional, idClinica, especialidade);
            response.status(204); // No Content
            return "";
        });

        // Define a route to delete a Profissional
        Spark.delete("/profissionais/:id", (request, response) -> {
            int idProfissional = Integer.parseInt(request.params(":id"));
            profissionaisDAO.deleteProfissional(idProfissional);
            response.status(204); // No Content
            return "";
        });

        // Define a route to get a specific Profissional
        Spark.get("/profissionais/:id", (request, response) -> {
            int idProfissional = Integer.parseInt(request.params(":id"));
            Profissional profissional = profissionaisDAO.getProfissional(idProfissional);
            if (profissional != null) {
                response.type("application/json"); // Set response type to JSON
                return new Gson().toJson(profissional); // Convert Profissional object to JSON
            } else {
                response.status(404); // Not Found
                return "Profissional not found";
            }
        });

        // Define a route to get all Profissionais
        Spark.get("/profissionais", (request, response) -> {
            response.type("application/json"); // Set response type to JSON
            return new Gson().toJson(profissionaisDAO.getAllProfissionais()); // Convert List<Profissional> to JSON
        });
    }

    public static void main(String[] args) {
        // Set up the database connection and ProfissionaisDAO
        ProfissionaisDAO profissionaisDAO = new ProfissionaisDAO();

        // Create and configure the ProfissionalController
        new ProfissionalController(profissionaisDAO);
    }
}
