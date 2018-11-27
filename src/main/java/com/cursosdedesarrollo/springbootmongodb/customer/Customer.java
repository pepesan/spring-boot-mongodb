package com.cursosdedesarrollo.springbootmongodb.customer;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Customer {

    @Id
    public String id;

    @NotNull
    @NotBlank
    public String firstName;
    @NotNull
    @NotBlank
    public String lastName;


    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


}
