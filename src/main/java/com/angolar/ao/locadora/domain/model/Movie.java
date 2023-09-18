package com.angolar.ao.locadora.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table( name = "movies" )
public class Movie {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String titulo;
    private String tempo;

    @ManyToOne
    @JoinColumn( nullable = false )
    private Category category;

    @OneToMany(mappedBy = "movie")
    private List<Unidade> unidades;

    @ManyToMany
    @JoinTable(name = "movie_actors",
        joinColumns = @JoinColumn(name = "cod_movie"),
        inverseJoinColumns = @JoinColumn(name = "cod_actors"))
    private List<Actor> actors;

//    @JsonIgnore
    @CreationTimestamp
    @Column( nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

//    @JsonIgnore
    @UpdateTimestamp
    @Column( nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;
}
