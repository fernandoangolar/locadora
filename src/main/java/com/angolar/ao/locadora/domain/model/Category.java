package com.angolar.ao.locadora.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.lang.annotation.Target;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
