package com.angolar.ao.locadora.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table( name = "tb_customers")
public class Customer {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id_customer" )
    private Long id;

    @Column( name = "first_name", nullable = false)
    private String firstname;

    @Column( name = "last_name", nullable = false )
    private String lastname;

    @Column( nullable = false )
    private String email;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Rentals> rentals = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable( name = "tb_customers_roles", joinColumns = @JoinColumn(name = "id_customer", referencedColumnName = "id_customer"),
                    inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id_role") )
    private Collection<Role> roles;
}
