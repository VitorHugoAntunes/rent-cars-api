package br.gov.sp.fatec.domain.request;

import br.gov.sp.fatec.domain.entity.Carro;
import br.gov.sp.fatec.domain.entity.Cliente;

public record AluguelRequest(
        Carro carro,
        Cliente cliente
) {}
