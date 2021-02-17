package com.andrebarca.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Custodia extends Base {

    public Custodia() {
        this.valorMedio = 0.0;
        this.quantidade = 0;
        this.papel = null;
    }

    public Custodia (double valorMedio, int quantidade, Papel papel) {
        this.valorMedio = valorMedio;
        this.quantidade = quantidade;
        this.papel = papel;
    }

    private Double valorMedio;

    private Integer quantidade;
    
    @ManyToOne
    @JoinColumn(name = "PAPEL_ID")
    private Papel papel;

    public Double getValorMedio() {
        return valorMedio;
    }

    public void setValorMedio(Double valorMedio) {
        this.valorMedio = valorMedio;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Papel getPapel() {
        return papel;
    }

    public void setPapel(Papel papel) {
        this.papel = papel;
    }
}
