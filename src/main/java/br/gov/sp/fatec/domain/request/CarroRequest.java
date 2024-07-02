package br.gov.sp.fatec.domain.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CarroRequest(
        @NotBlank(message = "Modelo é obrigatorio") String modelo,
        @NotBlank(message = "Marca é obrigatorio") @Size(min = 3,message = "minimo 3 chars") String marca,
        @NotNull(message = "Ano é obrigatorio") @Min(value = 1980, message = "Ano deve ser superior a 1900") Integer ano
) {}
