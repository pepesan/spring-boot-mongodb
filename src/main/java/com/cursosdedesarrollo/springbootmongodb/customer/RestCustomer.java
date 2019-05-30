package com.cursosdedesarrollo.springbootmongodb.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class RestCustomer {

    @Autowired
    private CustomerRepository repository;

    @GetMapping
    List<Customer> all() {
        return repository.findAll();
    }

    @PostMapping
    Customer newCustomer(@Valid @RequestBody Customer customer) {
        return repository.save(customer);
    }

    @GetMapping("/{id}")
    Customer one(@PathVariable String id) {

        return repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @PutMapping("/{id}")
    Customer replaceCustomer(@Valid @RequestBody Customer customer, @PathVariable String id) {

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

    @DeleteMapping("/{id}")
    Customer deleteCustomer(@PathVariable String id) {
        Optional<Customer> customer = repository.findById(id);
        repository.deleteById(id);
        return customer.get();
    }
}
