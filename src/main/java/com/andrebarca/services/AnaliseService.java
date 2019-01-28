/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrebarca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andrebarca.models.Analise;
import com.andrebarca.repositories.AnaliseRepository;

/**
 *
 * @author andre
 */

@RestController
public class AnaliseService {
    
    @Autowired
    AnaliseRepository analiseRepository;
    
    @RequestMapping(value = "/api/analises/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Analise analise) {
        Analise savedObj = analiseRepository.save(analise);
        return new ResponseEntity<>(savedObj, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/api/analises/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable long id) {
        boolean removed = false;
        try {
            analiseRepository.deleteById(id);
            removed = true;
        } catch (Exception e) {
            System.out.println("could not delete obj id: " + id);
        }
        return new ResponseEntity<>(removed, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/analises", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list() {
        Iterable<Analise> analises = this.analiseRepository.findAll();
        return new ResponseEntity<>(analises, HttpStatus.OK);
    }    
}
