package br.gov.sp.fatec.domain.request;

import br.gov.sp.fatec.domain.enums.AluguelStatus;
import lombok.Builder;

@Builder
public record AluguelUpdateRequest(
        AluguelStatus status
) {}
