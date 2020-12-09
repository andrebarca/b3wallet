package com.andrebarca.controllers;

import com.andrebarca.models.Papel;
import com.andrebarca.repositories.PapelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;


@Controller
@SessionScope
public class PapelController implements BaseController {

    private static final long serialVersionUID = 1L;

    private Papel selected;

    @Autowired
    private PapelRepository repository;

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
        this.selected = new Papel();
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
    public Iterable<Papel> list() {
        return this.repository.findAll();
    }

    @Override
    public Iterable<Papel> find(String query) {
        return this.repository.find(query);
    }

    /**
     * @return the selected
     */
    public Papel getSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(Papel selected) {
        this.selected = selected;
    }
}