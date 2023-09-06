package com.angolar.ao.locadora.api.controllers;

import com.angolar.ao.locadora.domain.model.Unidades;
import com.angolar.ao.locadora.domain.repositories.UnidadesRepository;
import com.angolar.ao.locadora.domain.service.UnidadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/unidades")
public class UnidadesController {

    @Autowired
    private UnidadesService service;
    @Autowired
    private UnidadesRepository repository;

    @PostMapping
    public ResponseEntity<Unidades> save(@RequestBody Unidades unidades) {

        Unidades obj = service.salve(unidades);

        return ResponseEntity.ok(obj);
    }

    @GetMapping
    public List<Unidades> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidades> findById(@PathVariable Long id) {

        Optional<Unidades> unidades = repository.findById(id);

        if ( unidades.isPresent() ) {
            return ResponseEntity.ok(unidades.get());
        }

        return ResponseEntity.notFound()
                .build();
    }
}
