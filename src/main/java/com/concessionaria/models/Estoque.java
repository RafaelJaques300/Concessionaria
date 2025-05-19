package com.concessionaria.models;

import com.concessionaria.dtos.EstoqueDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "estoque")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantidadeDisponivel;

    public Estoque() {}

    public Estoque(Double quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public Estoque(EstoqueDTO estoqueDTO){
        this.id = estoqueDTO.getId();
        this.quantidadeDisponivel = estoqueDTO.getQuantidadeDisponivel();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Estoque estoque = (Estoque) o;
        return Objects.equals(id, estoque.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
