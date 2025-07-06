package com.home.hometransito.hometransito_api.controller;

import com.home.hometransito.hometransito_api.domain.model.Autuacao;
import com.home.hometransito.hometransito_api.domain.model.Veiculo;
import com.home.hometransito.hometransito_api.domain.service.RegistroAutuacaoService;
import com.home.hometransito.hometransito_api.domain.service.RegistroVeiculoService;
import com.home.hometransito.hometransito_api.mapper.AutuacaoMapper;
import com.home.hometransito.hometransito_api.model.AutuacaoModel;
import com.home.hometransito.hometransito_api.model.input.AutuacaoInput;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
public class AutuacaoController {

    private final AutuacaoMapper autuacaoMapper;
    private final RegistroAutuacaoService registroAutuacaoService;
    private final RegistroVeiculoService registroVeiculoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoModel registrar(@PathVariable Long veiculoId,
                                   @Valid @RequestBody AutuacaoInput autuacaoInput) {


        Autuacao novaAutuacao = autuacaoMapper.toEntity(autuacaoInput);
        Autuacao autuacaoRegistrada = registroAutuacaoService
                .registrar(veiculoId, novaAutuacao);
        return autuacaoMapper.toModel(autuacaoRegistrada);
    }

    @GetMapping
    public List<AutuacaoModel> listar(@PathVariable Long veiculoId){
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
        return autuacaoMapper.toCollectionModel(veiculo.getAutuacoes());

    }

}
