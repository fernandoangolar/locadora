package com.angolar.ao.locadora.api.controllers;

import com.angolar.ao.locadora.domain.model.Customer;
import com.angolar.ao.locadora.domain.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAll () {
        return  customerRepository.findAll();
    }
}
