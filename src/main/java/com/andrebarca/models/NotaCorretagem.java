package com.andrebarca.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class NotaCorretagem extends Base {

    public NotaCorretagem() {

    }

    public NotaCorretagem(
        Long numero,
        Date dataPregao,
        Set<Operacao> operacoes,
        Double custosOperacionais,
        Double irrf
    ) {
        this.numero = numero;
        this.dataPregao = dataPregao;
        this.operacoes = operacoes;
        this.custosOperacionais = custosOperacionais;
        this.irrf = irrf;
    }

    private static final long serialVersionUID = 1L;
    private Long numero;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")    
    private Date dataPregao;

    @OneToMany(mappedBy = "notaCorretagem", orphanRemoval = true, cascade = CascadeType.ALL )
    @JsonIgnoreProperties(value = { "notaCorretagem" }, allowSetters = true)
    @OrderBy("id asc")
    private Set<Operacao> operacoes;

    private Double custosOperacionais;
    
    private Double irrf;

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Date getDataPregao() {
        return dataPregao;
    }

    public void setDataPregao(Date dataPregao) {
        this.dataPregao = dataPregao;
    }

    public void addOperacao(Operacao operacao) {
        operacao.setNotaCorretagem(this);
        this.operacoes.add(operacao);
    }

    public Set<Operacao> getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(Set<Operacao> operacoes) {
        operacoes.forEach(op -> {
            op.setNotaCorretagem(this);
        });
        this.operacoes = operacoes;
    }

    /**
     * @return the custos
     */
    public Double getcustosOperacionais() {
        return custosOperacionais;
    }

    /**
     * @param custos the custos to set
     */
    public void setcustosOperacionais(Double custosOperacionais) {
        this.custosOperacionais = custosOperacionais;
    }

    /**
     * @return the irrf
     */
    public Double getIrrf() {
        return irrf;
    }

    /**
     * @param irrf the irrf to set
     */
    public void setIrrf(Double irrf) {
        this.irrf = irrf;
    }

    public Double getTotalTaxas() {
        return this.custosOperacionais + this.irrf;
    }
}