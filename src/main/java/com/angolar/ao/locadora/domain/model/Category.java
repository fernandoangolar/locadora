package com.angolar.ao.locadora.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable =  false )
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    public List<Movie> movies = new ArrayList<>();
}
