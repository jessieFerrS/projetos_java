package com.home.hometransito.hometransito_api.model.input;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoModelInput {

    @NotBlank
    @Size(max = 20)
    private String marca;


    @NotBlank
    @Size(max = 20)
    private String modelo;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    private String placa;

//    private Long proprietarioId;
    private ProprietarioIdInput proprietario;

}
