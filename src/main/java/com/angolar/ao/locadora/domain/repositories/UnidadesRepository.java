package com.angolar.ao.locadora.domain.repositories;

import com.angolar.ao.locadora.domain.model.Unidades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadesRepository extends JpaRepository<Unidades, Long> {
}
