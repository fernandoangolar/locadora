package com.angolar.ao.locadora.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.angolar.ao.locadora.domain.exception.EntidadeEmUsoException;
import com.angolar.ao.locadora.domain.exception.EntidadeNaoEncontradaException;
import com.angolar.ao.locadora.domain.model.Cidade;
import com.angolar.ao.locadora.domain.model.Municipio;
import com.angolar.ao.locadora.domain.repositories.CidadeRepository;
import com.angolar.ao.locadora.domain.repositories.MunicipioRepository;

import jakarta.transaction.Transactional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository CidadeRepository;

    @Autowired
    private MunicipioRepository municipioRepository;


    @Transactional
    public Cidade salvar(Cidade cidade) {

        Long entityId = cidade.getMunicipio().getId();
        Municipio municipio = municipioRepository.findById(entityId)
                .orElseThrow( () -> new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de municipio com este %d ", entityId)) );

        cidade.setMunicipio(municipio);
        return CidadeRepository.save(cidade);
    }

    @Transactional
    public void delete(Long id) {

        try {

            CidadeRepository.deleteById(id);
        } catch ( EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cidade com o código %d", id));
        } catch ( DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cidade de código %id não ser deletado porque, está em uso", id));
        }
    }
    
}
