package com.angolar.ao.locadora.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table( name = "tb_payment_method")
public class PaymentMethod {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_payment")
    private Long id_payment;

    @Column( nullable = false )
    private String description;
}
