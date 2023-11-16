package com.angolar.ao.locadora.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table( name = "tb_customer")
public class Customer {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Rentals> rentals = new ArrayList<>();

    private boolean isAdmin;
}
