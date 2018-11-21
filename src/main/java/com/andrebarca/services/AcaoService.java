/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrebarca.services;

import com.andrebarca.models.Acao;
import com.andrebarca.repositories.AcaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author andre
 */

@RestController
public class AcaoService {
    
    @Autowired
    AcaoRepository acaoRepository;
    
    @RequestMapping(value = "/api/acoes/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Acao acao) {
        Acao savedObj = this.acaoRepository.save(acao);
        return new ResponseEntity<>(savedObj, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/acoes", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Acao> list() {
        Iterable<Acao> acoes = this.acaoRepository.findAll(new Sort(Direction.ASC, "codigo"));
        return (List) acoes ;
    }
}