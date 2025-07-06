CREATE TABLE proprietario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);

ALTER TABLE proprietario
ADD CONSTRAINT uk_proprietario UNIQUE (email);