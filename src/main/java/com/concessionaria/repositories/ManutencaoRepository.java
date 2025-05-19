package com.concessionaria.repositories;

import com.concessionaria.models.Manutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
    Optional<Manutencao> findByTipoManutencao(String tipoManutencao);

}
