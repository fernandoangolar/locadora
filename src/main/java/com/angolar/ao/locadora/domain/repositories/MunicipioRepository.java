package com.angolar.ao.locadora.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angolar.ao.locadora.domain.model.Municipio;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long>{
    
}
