package com.andrebarca.controllers;

import java.io.Serializable;
import java.util.Optional;

import com.andrebarca.models.Empresa;
import com.andrebarca.repositories.EmpresaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class EmpresaController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Empresa selected;

    @Autowired
    private EmpresaRepository empresaRepository;

    public Iterable<Empresa> getEmpresas() {
        return this.empresaRepository.findAll();
    }

    public Empresa getSelected() {
        return this.selected;
    }

    public String index() {
        return "index?faces-redirect=true";
    }

    public String save() {
        System.out.println("Empresa id: " + this.selected.getId());
        System.out.println("Empresa nome: " + this.selected.getNome());
        System.out.println("Empresa CNPJ: " + this.selected.getCnpj());
        this.empresaRepository.save(this.selected);
        return "index?faces-redirect=true";
    }

    public String add() {
        this.selected = new Empresa();
        return "form?faces-redirect=true";
    }

    public String edit(long id) {
        System.out.println("Empresa id: " + id);
        this.selected = this.empresaRepository.findById(id).isPresent() ? this.empresaRepository.findById(id).get() : null;
        return "form?faces-redirect=true";
    }    

    public void delete(long id) {
        System.out.println("Delete Empresa id: " + id);
    }
}