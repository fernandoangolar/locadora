package com.angolar.ao.locadora.domain.service;

import com.angolar.ao.locadora.domain.exception.EntidadeEmUsoException;
import com.angolar.ao.locadora.domain.exception.EntidadeNaoEncontradaException;
import com.angolar.ao.locadora.domain.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActorService {

    @Autowired
    private ActorRepository repository;

    @Transactional
    public void delete( Long id ) {

        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e ) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe nenhum Acto cadastrado com o código %d", id));
        } catch (DataIntegrityViolationException e ) {
            throw new EntidadeEmUsoException(String.format("Actor com o código %d não pode ser removido, pois está em uso", id));
        }

    }
}
