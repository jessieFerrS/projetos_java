package com.home.hometransito.hometransito_api.domain.repository;

import com.home.hometransito.hometransito_api.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Optional<Veiculo> findByPlaca(String placa);

}
