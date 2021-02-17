package com.andrebarca.controllers;

import com.andrebarca.models.Empresa;
import com.andrebarca.repositories.EmpresaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class EmpresaController implements BaseController {

    private static final long serialVersionUID = 1L;

    private Empresa selected;

    @Autowired
    private EmpresaRepository repository;

    @Override
    public String index() {
        return "index?faces-redirect=true";
    }

    @Override
    public String save() {
        this.repository.save(this.selected);
        return "index?faces-redirect=true";
    }

    @Override
    public String add() {
        this.selected = new Empresa();
        return "form?faces-redirect=true";
    }

    @Override
    public String edit(long id) {
        this.selected = this.repository.findById(id).isPresent() ? this.repository.findById(id).get() : null;
        return "form?faces-redirect=true";
    }    

    @Override
    public void delete(long id) {
        System.out.println("implementar <Entity id>: " + id);
    }

    @Override
    public Iterable<Empresa> list() {
        return this.repository.findAll();
    }

    public Iterable<Empresa> getEmpresas() {
        return this.repository.findAll(Sort.by(Direction.ASC, "nome"));
    }

    @Override
    public Iterable<Empresa> find(String query) {
        return this.repository.findAll();
    }

    /**
     * @return the selected
     */
    public Empresa getSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(Empresa selected) {
        this.selected = selected;
    }
}