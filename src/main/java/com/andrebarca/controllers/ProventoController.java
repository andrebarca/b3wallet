package com.andrebarca.controllers;

import com.andrebarca.models.Provento;
import com.andrebarca.models.TipoProvento;
import com.andrebarca.repositories.ProventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

@Controller
@SessionScope
public class ProventoController implements BaseController {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    ProventoRepository repository;

    private Provento selected;

    @Override
    public String index() {
        return "index?faces-redirect=true";
    }

    @Override
    public String save() {
        System.out.println(this.getSelected().getPapel().getCodigo());
            System.out.println(this.getSelected().getDataEx());
            System.out.println(this.getSelected().getValor());
            
        this.setSelected(this.repository.save(this.getSelected()));
        return "index";
    }

    @Override
    public String add() {
        this.selected = new Provento();
        return "form?faces-redirect=true";
    }

    @Override
    public String edit(long id) {
        this.selected = this.repository.findById(id).isPresent() ? this.repository.findById(id).get() : null;
        return "form?faces-redirect=true";
    }

    @Override
    public void delete(long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Iterable<Provento> list() {
        return this.repository.findAll(Sort.by(Direction.DESC, "dataEx"));
    }

    @Override
    public Iterable<Provento> find(String query) {
        // TODO Auto-generated method stub
        return null;
    }

    public Provento getSelected() {
        return selected;
    }

    public void setSelected(Provento selected) {
        this.selected = selected;
    }

    public TipoProvento[] getTipos() {
        return TipoProvento.values();
    }
    
}
