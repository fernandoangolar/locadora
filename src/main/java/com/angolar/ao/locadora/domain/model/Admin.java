package com.angolar.ao.locadora.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table( name = "tb_admins")
public class Admin {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    public Long id;

    @Column( nullable = false )
    public String name;

    @Column( nullable = false )
    private String password;
}
