package br.gov.sp.fatec.repository;

import br.gov.sp.fatec.domain.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
    List<Carro> findByMarca(String marca);
}
