package br.gov.sp.fatec.domain.request;

public record CarroUpdateRequest(
        String modelo,
        String marca,
        String cor,
        Integer ano
) {}
