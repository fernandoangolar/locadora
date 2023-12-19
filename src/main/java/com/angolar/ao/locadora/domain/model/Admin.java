package com.angolar.ao.locadora.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table( name = "tb_admins")
public class Admin {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    @Column( name = "id_admin")
    private Long id;

    @Column( name = "first_name", nullable = false )
    private String firstname;

    @Column( name = "last_name",nullable = false )
    private String lastname;

    @Column( name = "password", nullable = false )
    private String password;

    @Column( name = "email", nullable = false )
    private String email;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable( name = "tb_admins_roles", joinColumns = @JoinColumn(name = "id_admin", referencedColumnName = "id_admin"),
                    inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id_role"))
    private Collection<Role> roles;
}
