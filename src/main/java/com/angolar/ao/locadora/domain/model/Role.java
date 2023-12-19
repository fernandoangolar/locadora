package com.angolar.ao.locadora.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_roles")
public class Role {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id_role")
    private Long id;

    @Column( name = "name_role", nullable = false)
    private String name;
}
