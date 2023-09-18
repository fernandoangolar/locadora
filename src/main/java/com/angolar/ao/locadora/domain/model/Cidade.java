package com.angolar.ao.locadora.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;



@Data
@Entity
@Table(name = "cidades")
public class Cidade {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany
    private Municipio municipio;
}
