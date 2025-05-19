package com.concessionaria.models;

import com.concessionaria.dtos.VeiculoDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String marca;

    @Column(unique = true)
    private String ano;

    @Column(unique = true)
    private String preco;

    @Column(unique = true)
    private String cor;

    public Veiculo() {}

    public Veiculo(String marca, String ano, String preco, String cor) {
        this.marca = marca;
        this.ano = ano;
        this.preco = preco;
        this.cor = cor;
    }

    public Veiculo(VeiculoDTO veiculoDTO){
        this.id = veiculoDTO.getId();
        this.marca = veiculoDTO.getMarca();
        this.ano = veiculoDTO.getAno();
        this.preco = veiculoDTO.getPreco();
        this.cor = veiculoDTO.getCor();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(id, veiculo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
