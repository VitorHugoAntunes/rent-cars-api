package br.gov.sp.fatec.controller.impl;

import br.gov.sp.fatec.controller.AluguelController;
import br.gov.sp.fatec.domain.request.AluguelRequest;
import br.gov.sp.fatec.domain.request.AluguelUpdateRequest;
import br.gov.sp.fatec.domain.response.AluguelResponse;
import br.gov.sp.fatec.service.AluguelService;
import java.util.List;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.*;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AluguelControllerImpl implements AluguelController {

    private final AluguelService aluguelService;

    @Override
    public ResponseEntity<AluguelResponse> save(AluguelRequest aluguel) {
        return status(CREATED).body(aluguelService.save(aluguel));
    }

    @Override
    public ResponseEntity<AluguelResponse> findById(Long id) {
        return ok(aluguelService.findById(id));
    }

    @Override
    public ResponseEntity<List<AluguelResponse>> findAll() {
        return ok(aluguelService.findAll());
    }

    @Override
    public ResponseEntity<Void> updateById(Long id, AluguelUpdateRequest request) {
        aluguelService.updateById(id, request);
        return noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        aluguelService.deleteById(id);
        return noContent().build();
    }
}
