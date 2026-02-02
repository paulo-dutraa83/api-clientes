-- Script para criação da tabela de clientes no banco de dados
CREATE TABLE  clientes(
    id              SERIAL          PRIMARY KEY,
    nome            VARCHAR(150)    NOT NULL,
    email           VARCHAR(50)     NOT NULL UNIQUE,
    telefone        VARCHAR         NOT NULL,
    datacadastro    TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    ativo           INT             DEFAULT 1
);