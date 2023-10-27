package com.divergent_mindverse.model;

public class Clinica {
    private int idClinica;
    private int idNeurodivergencia;

    public Clinica(int idClinica, int idNeurodivergencia) {
        this.idClinica = idClinica;
        this.idNeurodivergencia = idNeurodivergencia;
    }

    public int getIdClinica() {
        return idClinica;
    }

    public int getIdNeurodivergencia() {
        return idNeurodivergencia;
    }
}
