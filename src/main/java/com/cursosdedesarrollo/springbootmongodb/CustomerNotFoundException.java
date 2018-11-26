package com.cursosdedesarrollo.springbootmongodb;

public class CustomerNotFoundException extends RuntimeException {

    CustomerNotFoundException(String id) {
        super("Could not find customer " + id);
    }
}
