package com.concessionaria.dtos;


import com.concessionaria.models.Manutencao;

public class ManutencaoDTO {
    private Long id;
    private String tipoManutencao;
    private String dataManutencao;
    private String status;
    private Double custo;

    public ManutencaoDTO() {}

    public ManutencaoDTO(Manutencao manutencao) {
        this.id = manutencao.getId();
        this.tipoManutencao = manutencao.getTipoManutencao();
        this.dataManutencao = manutencao.getDataManutencao();
        this.status = manutencao.getStatus();
        this.custo = manutencao.getCusto();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoManutencao() {
        return tipoManutencao;
    }

    public void setTipoManutencao(String tipoManutencao) {
        this.tipoManutencao = tipoManutencao;
    }

    public String getDataManutencao() {
        return dataManutencao;
    }

    public void setDataManutencao(String dataManutencao) {
        this.dataManutencao = dataManutencao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }
}
