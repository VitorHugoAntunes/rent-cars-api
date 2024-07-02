package br.gov.sp.fatec.domain.request;

import br.gov.sp.fatec.domain.entity.Carro;
import br.gov.sp.fatec.domain.entity.Cliente;
import lombok.Builder;

@Builder
public record AluguelRequest(
        Carro carro,
        Cliente cliente
) {}
