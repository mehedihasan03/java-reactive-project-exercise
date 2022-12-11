package com.example.controller;

import com.example.dto.Customer;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/")
    public List<Customer> getAllCustomers(){
        return service.loadAllCustomers();
    }

    @GetMapping("/stream")
    public Flux<Customer> getAllCustomersStream(){
        return service.loadAllCustomersStream();
    }

}
