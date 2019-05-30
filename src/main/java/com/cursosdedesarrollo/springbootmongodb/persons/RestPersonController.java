package com.cursosdedesarrollo.springbootmongodb.persons;

import com.cursosdedesarrollo.springbootmongodb.customer.Customer;
import com.cursosdedesarrollo.springbootmongodb.customer.CustomerNotFoundException;
import com.cursosdedesarrollo.springbootmongodb.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class RestPersonController {
    @Autowired
    private PersonRepository repository;

    @GetMapping
    List<Person> all() {
        return repository.findAll();
    }

    @PostMapping
    Person newPerson(@RequestBody Person person) {
        return repository.save(person);
    }

    @GetMapping("/{id}")
    Person one(@PathVariable String id) {

        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("/{id}")
    Person replacePerson(@RequestBody Person person, @PathVariable String id) {

        return repository.findById(id)
                .map(person1 -> {
                    person1.setFirstName(person.getFirstName());
                    person1.setSecondName(person.getSecondName());
                    person1.setAddress(person.getAddress());
                    person1.setDateOfBirth(person.getDateOfBirth());
                    person1.setHobbies(person.getHobbies());
                    return repository.save(person1);
                })
                .orElseGet(() -> {
                    person.setId(id);
                    return repository.save(person);
                });
    }

    @DeleteMapping("/{id}")
    void deletePerson(@PathVariable String id) {
        repository.deleteById(id);
    }
}
