package com.angolar.ao.locadora.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table( name = "tb_customers")
public class Customer {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private String firstname;

    @Column( nullable = false )
    private String lastname;

    @Column( nullable = false )
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<Rentals> rentals = new ArrayList<>();

    private boolean isAdmin;
}
