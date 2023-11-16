package com.angolar.ao.locadora.domain.model;

import ch.qos.logback.core.net.server.Client;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table( name = "tb_rentals")
public class Rentals {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_movie")
    @Column( nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    @Column( nullable = false, name = "id_customer")
    private Customer customer;

    private LocalDateTime rental_date;
    private LocalDateTime return_date;
}
