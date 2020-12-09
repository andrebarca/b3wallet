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

import com.andrebarca.models.Nota;
import com.andrebarca.repositories.NotaRepository;

/**
 *
 * @author andre
 */

@RestController
public class NotaService {
    
    @Autowired
    NotaRepository repository;
    
    @RequestMapping(value = "/api/notas/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Nota nota) {
        Nota savedObj = repository.save(nota);
        return new ResponseEntity<>(savedObj, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/api/notas/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable long id) {
        boolean removed = false;
        try {
            repository.deleteById(id);
            removed = true;
        } catch (Exception e) {
            System.out.println("could not delete obj id: " + id);
        }
        return new ResponseEntity<>(removed, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/notas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list() {
        Iterable<Nota> notas = this.repository.findAll();
        return new ResponseEntity<>(notas, HttpStatus.OK);
    }    
}
