package com.angolar.ao.locadora.domain.model;

import com.angolar.ao.locadora.domain.enums.Status_reserves;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_reserves")
public class Reservation {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id_reservation;

    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn( name = "id_customer")
    private Customer customer;

    @OneToMany( mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Movie> movies = new ArrayList<>();

    @CreatedDate
    private LocalDateTime booking_date;

    private Status_reserves status_reserves;
}
