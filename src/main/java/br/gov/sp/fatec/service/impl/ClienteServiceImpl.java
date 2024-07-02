package br.gov.sp.fatec.service.impl;

import br.gov.sp.fatec.domain.entity.Cliente;
import br.gov.sp.fatec.domain.mapper.ClienteMapper;
import br.gov.sp.fatec.domain.request.ClienteRequest;
import br.gov.sp.fatec.domain.request.ClienteUpdateRequest;
import br.gov.sp.fatec.domain.response.ClienteResponse;
import br.gov.sp.fatec.repository.ClienteRepository;
import br.gov.sp.fatec.service.ClienteService;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

    @Transactional
    @Override
    public ClienteResponse save(ClienteRequest clienteRequest) {
        Cliente obj = clienteMapper.map(clienteRequest);
        return clienteMapper.map(clienteRepository.save(obj));
    }

    @Transactional(readOnly = true)
    @Override
    public ClienteResponse findById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cliente não encontrado com id: " + id)
        );
        return clienteMapper.map(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClienteResponse> findAll() {
        return clienteRepository.findAll().stream().map(clienteMapper::map).toList();
    }

    @Transactional
    @Override
    public void updateById(Long id, ClienteUpdateRequest clienteUpdateRequest) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cliente não encontrado com id: " + id)
        );
        cliente.setNome(clienteUpdateRequest.nome());
        cliente.setCpf(clienteUpdateRequest.cpf());
        cliente.setTelefone(clienteUpdateRequest.telefone());
        clienteRepository.save(cliente);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        clienteRepository.deleteById(id);
    }
}
