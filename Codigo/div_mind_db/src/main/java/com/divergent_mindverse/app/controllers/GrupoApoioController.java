package com.divergent_mindverse.app.controllers;

import com.divergent_mindverse.dao.GruposApoioDAO;
import com.divergent_mindverse.model.GrupoApoio;
import com.google.gson.Gson;
import spark.Spark;

public class GrupoApoioController {

    public GrupoApoioController(final GruposApoioDAO gruposApoioDAO) {
        Spark.port(4567);

        // Define a route to insert a new Grupo de Apoio
        Spark.post("/grupos-apoio", (request, response) -> {
            int idNeurodivergencia = Integer.parseInt(request.queryParams("idNeurodivergencia"));

            int idGrupoApoio = gruposApoioDAO.insertGrupoApoio(idNeurodivergencia);
            response.status(201); // Created
            return idGrupoApoio;
        });

        // Define a route to update an existing Grupo de Apoio
        Spark.put("/grupos-apoio/:id", (request, response) -> {
            int idGrupoApoio = Integer.parseInt(request.params(":id"));
            int idNeurodivergencia = Integer.parseInt(request.queryParams("idNeurodivergencia"));

            gruposApoioDAO.updateGrupoApoio(idGrupoApoio, idNeurodivergencia);
            response.status(204); // No Content
            return "";
        });

        // Define a route to delete a Grupo de Apoio
        Spark.delete("/grupos-apoio/:id", (request, response) -> {
            int idGrupoApoio = Integer.parseInt(request.params(":id"));
            gruposApoioDAO.deleteGrupoApoio(idGrupoApoio);
            response.status(204); // No Content
            return "";
        });

        // Define a route to get a specific Grupo de Apoio
        Spark.get("/grupos-apoio/:id", (request, response) -> {
            int idGrupoApoio = Integer.parseInt(request.params(":id"));
            GrupoApoio grupoApoio = gruposApoioDAO.getGrupoApoio(idGrupoApoio);
            if (grupoApoio != null) {
                response.type("application/json"); // Set response type to JSON
                return new Gson().toJson(grupoApoio); // Convert GrupoApoio object to JSON
            } else {
                response.status(404); // Not Found
                return "Grupo de Apoio not found";
            }
        });

        // Define a route to get all Grupos de Apoio
        Spark.get("/grupos-apoio", (request, response) -> {
            response.type("application/json"); // Set response type to JSON
            return new Gson().toJson(gruposApoioDAO.getAllGruposApoio()); // Convert List<GrupoApoio> to JSON
        });
    }

    public static void main(String[] args) {
        // Set up the database connection and GruposApoioDAO
        GruposApoioDAO gruposApoioDAO = new GruposApoioDAO();

        // Create and configure the GrupoApoioController
        new GrupoApoioController(gruposApoioDAO);
    }
}
