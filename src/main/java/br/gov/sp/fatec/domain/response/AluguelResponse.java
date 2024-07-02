package br.gov.sp.fatec.domain.response;

import br.gov.sp.fatec.domain.entity.Carro;
import br.gov.sp.fatec.domain.entity.Cliente;
import br.gov.sp.fatec.domain.enums.AluguelStatus;

import java.time.LocalDate;

public record AluguelResponse(
        Long id,
        AluguelStatus status,
        LocalDate dataInicio,
        LocalDate dataFim,
        Double valor,
        Carro carro,
        Cliente cliente
) {}
