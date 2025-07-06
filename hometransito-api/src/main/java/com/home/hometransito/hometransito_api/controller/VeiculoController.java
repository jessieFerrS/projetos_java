package com.home.hometransito.hometransito_api.controller;

import com.home.hometransito.hometransito_api.domain.model.Veiculo;
import com.home.hometransito.hometransito_api.domain.repository.VeiculoRepository;
import com.home.hometransito.hometransito_api.domain.service.ApreensaoVeiculoService;
import com.home.hometransito.hometransito_api.domain.service.RegistroVeiculoService;
import com.home.hometransito.hometransito_api.mapper.VeiculoMapper;
import com.home.hometransito.hometransito_api.model.VeiculoRepresentationModel;
import com.home.hometransito.hometransito_api.model.input.VeiculoModelInput;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;
    private final RegistroVeiculoService registroVeiculoService;
    private final ApreensaoVeiculoService apreensaoVeiculoService;
    private final VeiculoMapper veiculoMapper;

    @GetMapping
    public List<VeiculoRepresentationModel> listar() {
        return veiculoMapper.toCollectionModel(veiculoRepository.findAll());
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoRepresentationModel> buscar(@PathVariable Long veiculoId){
        return veiculoRepository.findById(veiculoId)
            .map(veiculoMapper::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoRepresentationModel cadastrar(@Valid @RequestBody VeiculoModelInput veiculoModelInput) {
        Veiculo novoVeiculo = veiculoMapper.toEntity(veiculoModelInput);
        Veiculo veiculoCadastrado = registroVeiculoService.cadastrar(novoVeiculo);

        return veiculoMapper.toModel(veiculoCadastrado);
    }

    @PutMapping("/{veiculoId}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void aprender(@PathVariable Long veiculoId){
        apreensaoVeiculoService.apreender(veiculoId);
    }


    @DeleteMapping("/{veiculoId}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void liberar(@PathVariable Long veiculoId){
        apreensaoVeiculoService.liberar(veiculoId);
    }

}
