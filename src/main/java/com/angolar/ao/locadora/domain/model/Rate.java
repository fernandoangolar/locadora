package com.angolar.ao.locadora.domain.model;

import com.angolar.ao.locadora.domain.enums.StatusRete;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table( name = "tb_rate")
public class Rate {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id_rate;

    @Column( nullable = false)
    private String description;

    private Double value;

    @CreatedDate
    private LocalDateTime application_date;

    @OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    private StatusRete statusRete;
}
