package com.angolar.ao.locadora.domain.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_categories")
public class Category {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable =  false )
    private String name;

}
