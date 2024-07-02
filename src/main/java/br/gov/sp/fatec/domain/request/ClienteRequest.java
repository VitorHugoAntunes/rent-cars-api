package br.gov.sp.fatec.domain.request;

import lombok.Builder;

@Builder
public record ClienteRequest(String nome, String cpf, String telefone) {}
