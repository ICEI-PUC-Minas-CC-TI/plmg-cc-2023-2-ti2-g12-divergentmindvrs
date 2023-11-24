package com.divergent_mindverse.model;

public class Usuario {
    private int idUsuario;
    private int idNeurodivergencia;
    private String login;
    private String senha;
    private String nome;
    private String email;

    public Usuario(int idUsuario, int idNeurodivergencia, String login, String senha, String nome, String email) {
        this.idUsuario = idUsuario;
        this.idNeurodivergencia = idNeurodivergencia;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.email = email;
    }

    public int getIdUsuario() {return idUsuario;}

    public int getIdNeurodivergencia() {return idNeurodivergencia;}

    public String getLogin() {return login;}

    public String getSenha() {return senha;}

    public String getNome() {return nome;}

    public String getEmail() {return email;}
}
