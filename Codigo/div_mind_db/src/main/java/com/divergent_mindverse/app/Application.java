package com.divergent_mindverse.app;

import com.divergent_mindverse.app.controllers.ClinicaController;
import com.divergent_mindverse.app.controllers.GrupoApoioController;
import com.divergent_mindverse.app.controllers.ProfissionalController;
import com.divergent_mindverse.app.controllers.UsuarioController;
import com.divergent_mindverse.dao.ClinicaDAO;
import com.divergent_mindverse.dao.UsuariosDAO;
import com.divergent_mindverse.dao.ProfissionaisDAO;
import com.divergent_mindverse.dao.GruposApoioDAO;
import spark.Spark;

public class Application {

    public static void main(String[] args) {
        // Set up controllers for different entities

        // Set the port and other configurations
        Spark.port(4567); // Set your desired port number

        ClinicaController clinicaController = new ClinicaController(new ClinicaDAO());
        UsuarioController usuarioController = new UsuarioController(new UsuariosDAO());
        ProfissionalController profissionalController = new ProfissionalController(new ProfissionaisDAO());
        GrupoApoioController grupoApoioController = new GrupoApoioController(new GruposApoioDAO());

        // This will start the server with defined routes from all controllers
    }
}
