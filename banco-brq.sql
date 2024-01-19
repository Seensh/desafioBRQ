CREATE DATABASE banco_brq;
\c banco_brq;

-- Criação da tabela endereco
CREATE TABLE endereco (
    id_endereco SERIAL PRIMARY KEY,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    pais VARCHAR(50) NOT NULL
);

-- Tabela Cliente
CREATE TABLE cliente (
    id_cliente SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    documento VARCHAR(14) UNIQUE NOT NULL,
    tipo_pessoa VARCHAR(2) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    data_inclusao DATE NOT NULL,
    id_endereco INT REFERENCES endereco(id_endereco)
);

-- Tabela Conta
CREATE TABLE conta (
    id_conta SERIAL PRIMARY KEY,
    agencia VARCHAR(255) UNIQUE NOT NULL,
    numero_conta VARCHAR(255) UNIQUE NOT NULL,
    saldo DECIMAL(12,2),
    status VARCHAR(10),
    id_cliente INT REFERENCES cliente(id_cliente),
    data_inclusao DATE NOT NULL
);
