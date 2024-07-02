package br.gov.sp.fatec.service.impl;

import br.gov.sp.fatec.domain.entity.Carro;
import br.gov.sp.fatec.domain.mapper.CarroMapper;
import br.gov.sp.fatec.domain.request.CarroRequest;
import br.gov.sp.fatec.domain.request.CarroUpdateRequest;
import br.gov.sp.fatec.domain.response.CarroResponse;
import br.gov.sp.fatec.repository.CarroRepository;
import br.gov.sp.fatec.service.CarroService;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CarroServiceImpl implements CarroService {

    private final CarroRepository carroRepository;
    private final CarroMapper carroMapper = CarroMapper.INSTANCE;

    @Transactional
    @Override
    public CarroResponse save(CarroRequest carroRequest) {
        Carro obj = carroMapper.map(carroRequest);
        return carroMapper.map(carroRepository.save(obj));
    }

    @Transactional(readOnly = true)
    @Override
    public CarroResponse findById(Long id) {
        Carro carro = carroRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Carro não encontrado com id: " + id)
        );
        return carroMapper.map(carro);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CarroResponse> findAll() {
        return carroRepository.findAll().stream().map(carroMapper::map).toList();
    }

    @Transactional
    @Override
    public void updateById(Long id, CarroUpdateRequest carroUpdateRequest) {
        Carro carro = carroRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Carro não encontrado com id: " + id)
        );
        carro.setMarca(carroUpdateRequest.marca());
        carro.setModelo(carroUpdateRequest.modelo());
        carro.setAno(carroUpdateRequest.ano());
        carroRepository.save(carro);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        carroRepository.deleteById(id);
    }
}
