package com.angolar.ao.locadora.domain.service;

import com.angolar.ao.locadora.domain.exception.EntidadeEmUsoException;
import com.angolar.ao.locadora.domain.exception.EntidadeNaoEncontradaException;
import com.angolar.ao.locadora.domain.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public void delete( Long id ) {

       try  {
           categoryRepository.deleteById(id);
       } catch ( EmptyResultDataAccessException e ) {
           throw new EntidadeNaoEncontradaException( String.format("Não existe nenhuma categoria cadastrado com o código %d", id));
       } catch (DataIntegrityViolationException e ) {
           throw new EntidadeEmUsoException(String.format("Categoria de código %d não pode ser removido, pois esta em uso", id));
       }
    }
}
