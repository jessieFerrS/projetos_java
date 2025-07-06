package com.home.hometransito.hometransito_api.domain.service;

import com.home.hometransito.hometransito_api.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ApreensaoVeiculoService {

    private final RegistroVeiculoService registroVeiculoService;

    @Transactional
    public void apreender(Long veiculoId){
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
        veiculo.apreender();
    }

    @Transactional
    public void liberar(Long veiculoId){
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
        veiculo.liberar();
    }
}
