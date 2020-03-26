package com.andrebarca.controllers;

import java.io.Serializable;

import com.andrebarca.models.Empresa;
import com.andrebarca.repositories.EmpresaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class EmpresaController implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private int counter = 0;
    
    private final String text = "teste";

    @Autowired
    private EmpresaRepository empresaRepository;

    public Iterable<Empresa> getEmpresas() {
        return empresaRepository.findAll();
    }

    public String getText() {
        return text + ++this.counter;
    }
}
