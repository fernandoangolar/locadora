package com.angolar.ao.locadora.domain.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "actors")
public class Actor {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "actor")
//    private List<Movie> movies = new ArrayList<>();
}
