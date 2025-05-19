package com.concessionaria.dtos;

import com.concessionaria.models.Veiculo;

public class VeiculoDTO {
    private Long id;
    private String marca;
    private String ano;
    private String preco ;
    private String cor;

    public VeiculoDTO() {}

    public VeiculoDTO(Veiculo veiculo) {
        this.id = veiculo.getId();
        this.marca = veiculo.getMarca();
        this.ano = veiculo.getAno();
        this.preco = veiculo.getPreco();
        this.cor = veiculo.getCor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
