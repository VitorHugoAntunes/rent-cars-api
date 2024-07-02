package br.gov.sp.fatec.init;

import br.gov.sp.fatec.domain.request.AluguelRequest;
import br.gov.sp.fatec.domain.request.CarroRequest;
import br.gov.sp.fatec.domain.request.ClienteRequest;
import br.gov.sp.fatec.domain.response.CarroResponse;
import br.gov.sp.fatec.domain.response.ClienteResponse;
import br.gov.sp.fatec.service.AluguelService;
import br.gov.sp.fatec.service.CarroService;
import br.gov.sp.fatec.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitDB implements CommandLineRunner {

    private final ClienteService clienteService;
    private final CarroService carroService;
    private final AluguelService aluguelService;

    @Override
    public void run(String... args) throws Exception {
        ClienteRequest cliente1 = ClienteRequest.builder()
                .nome("Cliente 1")
                .cpf("123.456.789-00")
                .telefone("(11) 99999-9999")
                .build();
        ClienteResponse clienteResponse = clienteService.save(cliente1);

        CarroRequest carro1 = CarroRequest.builder()
                .modelo("Modelo 1")
                .marca("Marca 1")
                .ano(2021)
                .build();
        CarroResponse carroResponse = carroService.save(carro1);

        AluguelRequest aluguelRequest = AluguelRequest.builder()
                .clienteId(clienteResponse.id())
                .carroId(carroResponse.id())
                .build();

        aluguelService.save(aluguelRequest);
    }
}
