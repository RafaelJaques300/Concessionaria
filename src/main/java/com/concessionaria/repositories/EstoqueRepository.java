package com.concessionaria.repositories;

import com.concessionaria.models.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Optional<Estoque> findByQuantidadeDisponivel(Double quantidadeDisponivel);
}
