package com.home.hometransito.hometransito_api.domain.service;

import com.home.hometransito.hometransito_api.domain.exception.EntidadeNaoEncontradaException;
import com.home.hometransito.hometransito_api.domain.exception.NegocioException;
import com.home.hometransito.hometransito_api.domain.model.Proprietario;
import com.home.hometransito.hometransito_api.domain.model.StatusVeiculo;
import com.home.hometransito.hometransito_api.domain.model.Veiculo;
import com.home.hometransito.hometransito_api.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final RegistroProprietarioService registroProprietarioService;

    public Veiculo buscar(Long veiculoId){
        return veiculoRepository.findById(veiculoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Veiculo não encontrado!"));
    }

    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo) {
        if (novoVeiculo.getId() != null) {
            throw new NegocioException("Veículo a ser cadastrado não deve possuir um id!");
        }

        boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
                .filter(veiculo -> !veiculo.equals(novoVeiculo))
                .isPresent();

        if (placaEmUso){
            throw new NegocioException("Já existe um veículo cadastrado com esta placa!");
        }

        Proprietario proprietario = registroProprietarioService.buscar(novoVeiculo.getProprietario().getId());

        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(OffsetDateTime.now());

        return veiculoRepository.save(novoVeiculo);
    }
}
