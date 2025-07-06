package com.home.hometransito.hometransito_api.controller;

import com.home.hometransito.hometransito_api.domain.model.Proprietario;
import com.home.hometransito.hometransito_api.domain.exception.NegocioException;
import com.home.hometransito.hometransito_api.domain.repository.ProprietarioRepository;
import com.home.hometransito.hometransito_api.domain.service.RegistroProprietarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor  // cria construtores para todos os argumentos
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {


    private final RegistroProprietarioService registroProprietarioService;
    private final ProprietarioRepository proprietarioRepository;

    // torna a classe mais flexivel para testar e para acrescentar outras coisas
    //public ProprietarioController(ProprietarioRepository proprietarioRepository) {
    //    this.proprietarioRepository = proprietarioRepository;
    //}

    //@PersistenceContext
    //private EntityManager manager;          //entityManager -> gerenciador de entidades

    @GetMapping
    public List<Proprietario> listar(){
        // retorno encadeado da query
        //return manager.createQuery("from Proprietario", Proprietario.class).getResultList();

        // retorno completo da query
        //    TypedQuery<Proprietario>query = manager
        //            .createQuery("from Proprietario", Proprietario.class);
        //     return query.getResultList();

        return proprietarioRepository.findAll();
    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long proprietarioId){      //PathVariable vincula a variavel ao metodo
        return proprietarioRepository.findById(proprietarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

        // Optional<Proprietario> proprietario = proprietarioRepository.findById(proprietarioId);

//        if (proprietario.isPresent()){               //se fizer o replace => return proprietario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//            return ResponseEntity.ok(proprietario.get());
//        }
//        return ResponseEntity.notFound().build();   //build => constroi uma response entity com o status desejado.
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario adicionar(@Valid @RequestBody Proprietario proprietario){ //vincula o argumento ao corpo da requisição
        //return proprietarioRepository.save(proprietario);
        return registroProprietarioService.salvar(proprietario);
    }

    @PutMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long proprietarioId,
                                                  @Valid @RequestBody Proprietario proprietario) {

        if (!proprietarioRepository.existsById(proprietarioId)){
            return ResponseEntity.notFound().build();
        }

        proprietario.setId(proprietarioId);
        Proprietario proprietarioAtualizado = registroProprietarioService.salvar(proprietario);

        return ResponseEntity.ok(proprietarioAtualizado);
    }

    @DeleteMapping("/{proprietarioId}")
    public ResponseEntity<Void> remover(@PathVariable Long proprietarioId){

        if (!proprietarioRepository.existsById(proprietarioId)){
            return ResponseEntity.notFound().build();
        }

        registroProprietarioService.excluir(proprietarioId);
       //proprietarioRepository.deleteById(proprietarioId);
        return ResponseEntity.noContent().build();
    }

}
