/*package com.divergent_mindverse.app.controllers;

import com.divergent_mindverse.dao.UsuariosDAO;
import com.divergent_mindverse.model.Usuario;
import com.google.gson.Gson;

import static spark.Spark.*;

public class UsuarioController {
    private UsuariosDAO usuariosDAO;
    private Gson gson;

    public UsuarioController() {
        this.usuariosDAO = new UsuariosDAO();
        this.gson = new Gson();
        setupEndpoints();
    }

    private void setupEndpoints() {
        // Create a new Usuario
        post("/usuarios", (request, response) -> {
            Usuario usuario = gson.fromJson(request.body(), Usuario.class);
            int newUserId = usuariosDAO.insertUsuario(usuario);
            response.status(201);
            return newUserId;
        });

        // Retrieve a Usuario by ID
        get("/usuarios/:id", (request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            Usuario usuario = usuariosDAO.getUsuario(userId);
            if (usuario != null) {
                return gson.toJson(usuario);
            } else {
                response.status(404);
                return "User not found.";
            }
        });

        // Update a Usuario by ID
        put("/usuarios/:id", (request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            Usuario updatedUsuario = gson.fromJson(request.body(), Usuario.class);
            if (usuariosDAO.updateUsuario(userId, updatedUsuario)) {
                return "User updated.";
            } else {
                response.status(404);
                return "User not found.";
            }
        });

        // Delete a Usuario by ID
        delete("/usuarios/:id", (request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            if (usuariosDAO.deleteUsuario(userId)) {
                return "User deleted.";
            } else {
                response.status(404);
                return "User not found.";
            }
        });
    }
}*/
