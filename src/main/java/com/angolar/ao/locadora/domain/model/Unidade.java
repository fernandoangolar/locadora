package com.angolar.ao.locadora.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "unidades")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private Movie movie;

    //    @JsonIgnore
    @CreationTimestamp
    @Column( nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    //    @JsonIgnore
    @UpdateTimestamp
    @Column( nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;
}