package com.angolar.ao.locadora.domain.repositories;

import com.angolar.ao.locadora.domain.model.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadesRepository extends JpaRepository<Unidade, Long> {
}
