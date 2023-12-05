package com.angolar.ao.locadora.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table( name = "tb_movies" )
public class Movie {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String release_year;

    @OneToMany(mappedBy = "movie")
    private List<Rentals> rentals = new ArrayList<>();

//    @JsonIgnore
    @ManyToOne
    private Category category;

}
