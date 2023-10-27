package com.divergent_mindverse.model;

public class Profissional {
    private int idProfissional;
    private int idClinica;
    private int especialidade;

    public Profissional(int idProfissional, int idClinica, int especialidade) {
        this.idProfissional = idProfissional;
        this.idClinica = idClinica;
        this.especialidade = especialidade;
    }

    public int getIdProfissional() {
        return idProfissional;
    }

    public int getIdClinica() {
        return idClinica;
    }

    public int getEspecialidade() {
        return especialidade;
    }
}
