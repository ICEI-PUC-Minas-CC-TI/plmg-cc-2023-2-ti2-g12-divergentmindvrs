package com.divergent_mindverse.app.controllers;

import com.divergent_mindverse.dao.UsuariosDAO;
import com.divergent_mindverse.model.Usuario;
import com.google.gson.Gson;
import spark.Spark;

public class UsuarioController {

    public UsuarioController(final UsuariosDAO usuariosDAO) {
        Spark.port(4567);

        // Define a route to insert a new Usuario
        Spark.post("/usuarios", (request, response) -> {
            int idNeurodivergencia = Integer.parseInt(request.queryParams("idNeurodivergencia"));
            String login = request.queryParams("login");
            String senha = request.queryParams("senha");
            String nome = request.queryParams("nome");
            String email = request.queryParams("email");

            int idUsuario = usuariosDAO.insertUsuario(idNeurodivergencia, login, senha, nome, email);
            response.status(201); // Created
            return idUsuario;
        });

        // Define a route to update an existing Usuario
        Spark.put("/usuarios/:id", (request, response) -> {
            int idUsuario = Integer.parseInt(request.params(":id"));
            int idNeurodivergencia = Integer.parseInt(request.queryParams("idNeurodivergencia"));
            String login = request.queryParams("login");
            String senha = request.queryParams("senha");
            String nome = request.queryParams("nome");
            String email = request.queryParams("email");

            usuariosDAO.updateUsuario(idUsuario, idNeurodivergencia, login, senha, nome, email);
            response.status(204); // No Content
            return "";
        });

        // Define a route to delete a Usuario
        Spark.delete("/usuarios/:id", (request, response) -> {
            int idUsuario = Integer.parseInt(request.params(":id"));
            usuariosDAO.deleteUsuario(idUsuario);
            response.status(204); // No Content
            return "";
        });

        // Define a route to get a specific Usuario
        Spark.get("/usuarios/:id", (request, response) -> {
            int idUsuario = Integer.parseInt(request.params(":id"));
            Usuario usuario = usuariosDAO.getUsuario(idUsuario);
            if (usuario != null) {
                response.type("application/json"); // Set response type to JSON
                return new Gson().toJson(usuario); // Convert Usuario object to JSON
            } else {
                response.status(404); // Not Found
                return "Usuario not found";
            }
        });

        // Define a route to get all Usuarios
        Spark.get("/usuarios", (request, response) -> {
            response.type("application/json"); // Set response type to JSON
            return new Gson().toJson(usuariosDAO.getAllUsuarios()); // Convert List<Usuario> to JSON
        });
    }

    public static void main(String[] args) {
        // Set up the database connection and UsuariosDAO
        UsuariosDAO usuariosDAO = new UsuariosDAO();

        // Create and configure the UsuarioController
        new UsuarioController(usuariosDAO);
    }
}
