package com.home.hometransito.hometransito_api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.home.hometransito.hometransito_api.domain.exception.NegocioException;
import com.home.hometransito.hometransito_api.domain.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ProprietarioId.class)
    //@JoinColumn(name = "proprietario_id") -> permite especificar o nome da propriedade que faz o relacionamento no modelo relacional
    private Proprietario proprietario;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotBlank
    //XXX0000
    //XXX0X00
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    private String placa;

    @JsonProperty(access = Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    @JsonProperty(access = Access.READ_ONLY)
    private OffsetDateTime dataCadastro;

    @JsonProperty(access = Access.READ_ONLY)
    private OffsetDateTime dataApreensao;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    private List<Autuacao> autuacoes = new ArrayList<>();

    public Autuacao adicionarAutuacao(Autuacao autuacao){
        autuacao.setDataOcorrencia(OffsetDateTime.now());
        autuacao.setVeiculo(this);
        getAutuacoes().add(autuacao);
        return autuacao;
    }

    public void apreender(){
        if (estaApreendido()){
            throw new NegocioException("Veículo já se encontra apreendido");
        }

        setStatus(StatusVeiculo.APREENDIDO);
        setDataApreensao(OffsetDateTime.now());
    }

    public void liberar(){
        if (naoEstaApreendido()){
            throw new NegocioException("Veículo não está apreendido!!");
        }
        setStatus(StatusVeiculo.REGULAR);
        setDataApreensao(null);
    }

    public boolean estaApreendido() {
        return StatusVeiculo.APREENDIDO.equals(getStatus());
    }

    public boolean naoEstaApreendido() {
        return !estaApreendido();
    }
}
