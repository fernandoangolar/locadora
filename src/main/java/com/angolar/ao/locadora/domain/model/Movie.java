package com.angolar.ao.locadora.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import lombok.Data;

@Data
@Entity
@Table( name = "movies" )
public class Movie {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String tempo;

//    @JsonIgnore
    @ManyToOne
    private Category category;

    @JsonIgnore
    @OneToMany( mappedBy = "movie" )
    private List<Unidade> unidades = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private Actor actor;

}
