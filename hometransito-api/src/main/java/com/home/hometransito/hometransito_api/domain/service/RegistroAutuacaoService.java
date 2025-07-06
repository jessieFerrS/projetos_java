package com.home.hometransito.hometransito_api.domain.service;

import com.home.hometransito.hometransito_api.domain.model.Autuacao;
import com.home.hometransito.hometransito_api.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroAutuacaoService {

    private RegistroVeiculoService registroVeiculoService;

    @Transactional
    public Autuacao registrar(Long veiculoId,Autuacao novaAtuacao) {
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);

        return veiculo.adicionarAutuacao(novaAtuacao);

    }
}
