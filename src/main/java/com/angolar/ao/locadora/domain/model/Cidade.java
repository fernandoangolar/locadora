package com.angolar.ao.locadora.domain.model;




import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "cidades")
public class Cidade {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Municipio municipio;
}
