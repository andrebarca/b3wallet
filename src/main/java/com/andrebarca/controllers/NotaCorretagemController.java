package com.andrebarca.controllers;

import org.springframework.web.context.annotation.SessionScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

import com.andrebarca.models.NotaCorretagem;
import com.andrebarca.models.Operacao;
import com.andrebarca.models.Papel;
import com.andrebarca.models.TipoOperacao;
import com.andrebarca.repositories.NotaCorretagemRepository;
import com.andrebarca.repositories.PapelRepository;
import com.ibm.icu.text.MessageFormat;

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
        try {
            this.setSelected(this.notaCorretagemRepository.save(this.getSelected()));
            String message = MessageFormat.format("Nota de corretagem {0} salva", new Object[] {this.getSelected().getNumero()});
            Messenger.showInfo(message);

        } catch (EntityNotFoundException error) {
            String message = "Erro ao tentar salvar. Tente mais tarde ou entre em contato com o suporte.";
            Messenger.showError(message);
        }
        return "index?faces-redirect=true";
    }

    @Override
    public String add() {
        System.out.println("add nota method");
        this.setSelected(new NotaCorretagem());
        return "form?faces-redirect=true";
    }

    @Override
    public String edit(long id) {
        this.setSelected(this.notaCorretagemRepository.getById(id));
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
        operacao.setDataOperacao(this.getSelected().getDataPregao());
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