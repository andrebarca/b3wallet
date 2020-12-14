package com.andrebarca.controllers;

import org.springframework.web.context.annotation.SessionScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import com.andrebarca.models.NotaCorretagem;
import com.andrebarca.models.Operacao;
import com.andrebarca.models.Papel;
import com.andrebarca.models.TipoOperacao;
import com.andrebarca.repositories.NotaCorretagemRepository;
import com.andrebarca.repositories.PapelRepository;

@Component
@SessionScope
public class NotaCorretagemController implements BaseController {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    NotaCorretagemRepository notaCorretagemRepository;

    @Autowired
    PapelRepository papelRepository;

    private NotaCorretagem selected;

    // item values
    private Papel papel;
    private String tipoOperacao = TipoOperacao.COMPRA.getTipo();
    private Double valor = 0.0;
    private int quantidade = 0;

    public Papel getPapel() {
        return papel;
    }

    public void setPapel(Papel papel) {
        this.papel = papel;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String index() {
        return "index?faces-redirect=true";
    }
    
    @Override
    public String save() {
        System.out.println(this.selected.getNumero());
        System.out.println(this.selected.getDataPregao());
        System.out.println(this.selected.getIrrf());
        System.out.println(this.selected.getcustosOperacionais());
        this.selected.getOperacoes().forEach(op -> System.out.println(op.getPapel().getCodigo()));
        return null;
    }

    @Override
    public String add() {
        System.out.println("add nota method");
        this.selected = new NotaCorretagem();
        return "form?faces-redirect=true";
    }

    @Override
    public String edit(long id) {
        this.selected = this.notaCorretagemRepository.getById(id);
        return "form?faces-redirect=true";
    }

    @Override
    public void delete(long id) {
        // TODO Auto-generated method stub
        
    }

    public void addItem() {
        TipoOperacao tipoOperacao = TipoOperacao.valueOf(this.getTipoOperacao());
        Operacao operacao = new Operacao();
        operacao.setPapel(papel);
        operacao.setTipoOperacao(tipoOperacao);
        operacao.setQuantidade(this.getQuantidade());
        operacao.setValor(this.getValor());
        this.getSelected().addOperacao(operacao);
        this.resetItemForm();
    }

    public void deleteItem(Operacao operacao) {
        System.out.println(operacao);
        this.getSelected().deleteOperacao(operacao);
    }

    public void resetItemForm() {
        this.setPapel(null);
        this.setTipoOperacao("COMPRA");
        this.setQuantidade(0);
        this.setValor(0.0);
    }

    @Override
    public Iterable<NotaCorretagem> list() {
        return this.notaCorretagemRepository.findByOrderByDataPregaoDesc();
    }

    @Override
    public Iterable<NotaCorretagem> find(String query) {
        System.out.println(query);
        return null;
    }

    public NotaCorretagem getSelected() {
        return this.selected;
    }

    public void setSelected(NotaCorretagem selected) {
        this.selected = selected;
    }
}