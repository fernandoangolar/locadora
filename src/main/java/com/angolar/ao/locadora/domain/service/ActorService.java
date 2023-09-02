package com.angolar.ao.locadora.domain.service;

import com.angolar.ao.locadora.domain.model.Actor;
import com.angolar.ao.locadora.domain.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    @Autowired
    private ActorRepository repository;

    public void delete( Long id ) {

        Actor actor = repository.findById(id).get();

        if ( actor == null ) {
            throw new EmptyResultDataAccessException(1);
        }

        repository.delete(actor);
    }
}
