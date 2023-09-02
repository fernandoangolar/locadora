package com.angolar.ao.locadora.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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
    private Category category;

//    @JsonIgnore
    @CreationTimestamp
    @Column( nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

//    @JsonIgnore
    @UpdateTimestamp
    @Column( nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;
}
