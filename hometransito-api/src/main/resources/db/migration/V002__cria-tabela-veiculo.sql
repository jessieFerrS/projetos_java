-- Criação da tabela 'veiculo'
CREATE TABLE veiculo (
    id BIGSERIAL PRIMARY KEY,
    proprietario_id BIGINT NOT NULL,
    marca VARCHAR(20) NOT NULL,
    modelo VARCHAR(20) NOT NULL,
    placa VARCHAR(7) NOT NULL,
    status VARCHAR(20) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
    data_apreensao TIMESTAMP
);

-- Adição da chave estrangeira para a tabela 'proprietario'
ALTER TABLE veiculo ADD CONSTRAINT fk_veiculo_proprietario
FOREIGN KEY (proprietario_id) REFERENCES proprietario (id);

-- Garantia de unicidade da placa
ALTER TABLE veiculo ADD CONSTRAINT uk_veiculo UNIQUE (placa);


