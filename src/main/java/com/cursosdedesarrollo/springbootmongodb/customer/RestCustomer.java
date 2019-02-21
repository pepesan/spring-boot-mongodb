package com.cursosdedesarrollo.springbootmongodb.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RestCustomer {

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/customer")
    List<Customer> all() {
        return repository.findAll();
    }

    @PostMapping("/customer")
    Customer newCustomer(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @GetMapping("/customer/{id}")
    Customer one(@PathVariable String id) {

        return repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @PutMapping("/customer/{id}")
    Customer replaceCustomer(@RequestBody Customer customer, @PathVariable String id) {

        return repository.findById(id)
                .map(customer1 -> {
                    customer1.firstName=customer.firstName;
                    customer1.lastName=customer.lastName;
                    return repository.save(customer);
                })
                .orElseGet(() -> {
                    customer.id=id;
                    return repository.save(customer);
                });
    }

    @DeleteMapping("/customer/{id}")
    Customer deleteCustomer(@PathVariable String id) {
        Optional<Customer> customer = repository.findById(id);
        repository.deleteById(id);
        return customer.get();
    }
}
