CREATE DATABASE ti2bd;

CREATE TABLE IF NOT EXISTS neurodivergencia (
  id_neurodivergencia SERIAL PRIMARY KEY,
  nome VARCHAR(128) NOT NULL,
  sigla VARCHAR(16),
  descricao VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS grupos_apoio (
  id_grupo SERIAL PRIMARY KEY,
  id_neurodivergencia INT NOT NULL,
  FOREIGN KEY (id_neurodivergencia) REFERENCES neurodivergencia(id_neurodivergencia) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario SERIAL PRIMARY KEY,
    id_neurodivergencia INT NOT NULL,
    login VARCHAR(64) NOT NULL,
    senha VARCHAR(64) NOT NULL,
    nome VARCHAR(128) NOT NULL,
    email VARCHAR(64) NOT NULL,
    FOREIGN KEY (id_neurodivergencia) REFERENCES neurodivergencia(id_neurodivergencia) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS telefone_usuarios (
    id_usuario INT NOT NULL,
    telefone VARCHAR(64) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS integrado (
    id_usuario INT NOT NULL,
    id_grupo INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON UPDATE CASCADE,
    FOREIGN KEY (id_grupo) REFERENCES grupos_apoio(id_grupo) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS clinica (
    id_clinica SERIAL PRIMARY KEY,
    id_neurodivergencia INT NOT NULL,
    FOREIGN KEY (id_neurodivergencia) REFERENCES neurodivergencia(id_neurodivergencia) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS busca_ajuda (
    id_usuario INT NOT NULL,
    id_clinica INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON UPDATE CASCADE,
    FOREIGN KEY (id_clinica) REFERENCES clinica(id_clinica) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS profissionais (
    id_profissional SERIAL PRIMARY KEY,
    id_clinica INT NOT NULL,
    especialidade INT NOT NULL,
    FOREIGN KEY (id_clinica) REFERENCES clinica(id_clinica) ON UPDATE CASCADE,
    FOREIGN KEY (especialidade) REFERENCES neurodivergencia(id_neurodivergencia) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS trabalho (
    id_profissional INT NOT NULL,
    id_clinica INT NOT NULL,
    FOREIGN KEY (id_profissional) REFERENCES profissionais(id_profissional) ON UPDATE CASCADE,
    FOREIGN KEY (id_clinica) REFERENCES clinica(id_clinica) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS gestores (
    id_grupo INT NOT NULL,
    id_profissional INT NOT NULL,
    FOREIGN KEY (id_profissional) REFERENCES profissionais(id_profissional) ON UPDATE CASCADE,
    FOREIGN KEY (id_grupo) REFERENCES grupos_apoio(id_grupo) ON UPDATE CASCADE
);
