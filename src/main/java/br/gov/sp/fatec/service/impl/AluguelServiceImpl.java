package br.gov.sp.fatec.service.impl;

import br.gov.sp.fatec.domain.entity.Aluguel;
import br.gov.sp.fatec.domain.entity.Carro;
import br.gov.sp.fatec.domain.entity.Cliente;
import br.gov.sp.fatec.domain.enums.AluguelStatus;
import br.gov.sp.fatec.domain.enums.CarroStatus;
import br.gov.sp.fatec.domain.mapper.AluguelMapper;
import br.gov.sp.fatec.domain.request.AluguelRequest;
import br.gov.sp.fatec.domain.request.AluguelUpdateRequest;
import br.gov.sp.fatec.domain.response.AluguelResponse;
import br.gov.sp.fatec.repository.AluguelRepository;
import br.gov.sp.fatec.repository.CarroRepository;
import br.gov.sp.fatec.repository.ClienteRepository;
import br.gov.sp.fatec.service.AluguelService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AluguelServiceImpl implements AluguelService {

    private final AluguelRepository aluguelRepository;
    private final CarroRepository carroRepository;
    private final ClienteRepository clienteRepository;
    private final AluguelMapper aluguelMapper = AluguelMapper.INSTANCE;

    @Transactional(readOnly = true)
    @Override
    public List<AluguelResponse> findAll(AluguelStatus status) {
        if (status == null) {
            return aluguelRepository.findAll().stream().map(aluguelMapper::map).toList();
        }
        return aluguelRepository.findByAluguelStatus(status).stream()
                .map(aluguelMapper::map)
                .toList();
    }

    @Transactional
    @Override
    public AluguelResponse save(AluguelRequest aluguelRequest) {
        Carro carro = carroRepository.findById(
                aluguelRequest.carro().getId()
        ).orElseThrow(
                () -> new EntityNotFoundException("Carro não encontrado com id: " + aluguelRequest.carro().getId())
        );

        if (Objects.equals(carro.getStatus(), CarroStatus.ALUGADO)) {
            throw new IllegalArgumentException(String.format("O carro '%s' está alugado", carro.getModelo()));
        }

        Cliente cliente = clienteRepository.findById(
                aluguelRequest.cliente().getId()
        ).orElseThrow(
                () -> new EntityNotFoundException("Cliente não encontrado com id: " + aluguelRequest.cliente().getId())
        );

        carro.setStatus(CarroStatus.ALUGADO);
        carroRepository.save(carro);

        Aluguel aluguel = new Aluguel();
        aluguel.setCarro(carro);
        aluguel.setCliente(cliente);
        aluguel.setDataFim(Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        return aluguelMapper.map(aluguelRepository.save(aluguel));
    }

    @Transactional (readOnly = true)
    @Override
    public AluguelResponse findById(Long id) {
        Aluguel aluguel = aluguelRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Aluguel não encontrado com id: " + id)
        );
        return aluguelMapper.map(aluguel);
    }


    @Transactional
    @Override
    public void updateById(Long id, AluguelUpdateRequest aluguelUpdateRequest) {
        Aluguel aluguel = aluguelRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Aluguel não encontrado com id: " + id)
        );
        aluguel.setStatus(aluguelUpdateRequest.status());
        aluguelRepository.save(aluguel);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        findById(id);
        aluguelRepository.deleteById(id);
    }
}
