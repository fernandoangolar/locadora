package com.angolar.ao.locadora.domain.repositories;

import com.angolar.ao.locadora.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> { 

}
