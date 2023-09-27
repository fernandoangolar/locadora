package com.angolar.ao.locadora.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angolar.ao.locadora.domain.exception.EntidadeEmUsoException;
import com.angolar.ao.locadora.domain.exception.EntidadeNaoEncontradaException;
import com.angolar.ao.locadora.domain.model.Cidade;
import com.angolar.ao.locadora.domain.repositories.CidadeRepository;
import com.angolar.ao.locadora.domain.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
    
    @Autowired
    private CidadeRepository repository;

    @Autowired 
    private CidadeService service;

    @GetMapping
    public List<Cidade> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> findById(@PathVariable Long id) {

        Optional<Cidade> cidade = repository.findById(id);

        return cidade.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound()
                .build());

//        if ( cidade.isPresent() ) {
//            return ResponseEntity.ok(cidade.get());
//        }
//
//        return ResponseEntity.notFound()
//            .build();
    }

    @PostMapping
    public ResponseEntity<?> salve( @RequestBody Cidade cidade ) {

        try {
            Cidade entity = service.salvar(cidade);
    
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(entity);
        } catch( EntidadeNaoEncontradaException e ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage()); 
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update( @PathVariable Long id, @RequestBody Cidade cidade) {

        try {
            Optional<Cidade> entity = repository.findById(id);

            if ( entity.isPresent() ) {
                BeanUtils.copyProperties(cidade, entity.get(), "id");

                Cidade salvar = service.salvar(entity.get());
                return ResponseEntity.ok(salvar);
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
        } catch(EntidadeEmUsoException e ) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());
        }
    }
}
