package br.gov.sp.fatec.domain.request;

import lombok.Builder;

@Builder
public record CarroUpdateRequest(
        String modelo,
        String marca,
        Integer ano
) {}
