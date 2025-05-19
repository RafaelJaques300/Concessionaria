package com.concessionaria.models;

import com.concessionaria.dtos.ManutencaoDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "manutencao")
public class Manutencao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoManutencao;

    @Column(unique = true)
    private String dataManutencao;
    private String status;
    private Double custo;

    public Manutencao() {}

    public Manutencao(String tipoManutencao, String dataManutencao, String status, Double custo) {
        this.tipoManutencao = tipoManutencao;
        this.dataManutencao = dataManutencao;
        this.status = status;
        this.custo = custo;
    }

    public Manutencao(ManutencaoDTO manutencaoDTO){
        this.id = manutencaoDTO.getId();
        this.tipoManutencao = manutencaoDTO.getTipoManutencao();
        this.dataManutencao = manutencaoDTO.getDataManutencao();
        this.status = manutencaoDTO.getStatus();
        this.custo = manutencaoDTO.getCusto();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Manutencao manutencao = (Manutencao) o;
        return Objects.equals(id, manutencao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
