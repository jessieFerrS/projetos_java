package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

// records são imutaveis uma vez criados, não podemos mais alterar seu valor
public record ProductRecordDto(@NotBlank String name, @NotNull BigDecimal value) {
}
