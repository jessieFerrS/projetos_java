package com.home.hometransito.hometransito_api.domain.model;

import com.home.hometransito.hometransito_api.domain.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
//@Table(name = "tb_proprietario")
public class Proprietario {

    @NotNull(groups = ValidationGroups.ProprietarioId.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column
    @NotBlank
    @Size(min = 3, max = 60, message = "{size}")
    private String nome;

    @Column
    @NotBlank
    @Size(max = 255)
    @Email
    private String email;

    @Column
    @NotBlank
    @Size(max = 20)
    private String telefone;

}
