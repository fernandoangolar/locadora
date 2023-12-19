package com.angolar.ao.locadora.domain.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

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
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @CreatedDate
    private LocalDateTime rental_date;

    private LocalDateTime return_date;
}
