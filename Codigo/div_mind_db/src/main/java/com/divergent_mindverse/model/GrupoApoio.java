package com.divergent_mindverse.model;

public class GrupoApoio {
    private int idGrupo;
    private int idNeurodivergencia;

    public GrupoApoio(int idGrupo, int idNeurodivergencia) {
        this.idGrupo = idGrupo;
        this.idNeurodivergencia = idNeurodivergencia;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public int getIdNeurodivergencia() {
        return idNeurodivergencia;
    }
}
