package com.concessionaria.dtos;

import com.concessionaria.models.Estoque;

public class EstoqueDTO {
    public Long id;
    public Double quantidadeDisponivel;

    public EstoqueDTO() {}

    public EstoqueDTO(Estoque estoque) {
        this.id = estoque.getId();
        this.quantidadeDisponivel = estoque.getQuantidadeDisponivel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Double quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }
}
