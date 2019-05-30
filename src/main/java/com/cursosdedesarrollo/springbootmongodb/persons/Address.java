package com.cursosdedesarrollo.springbootmongodb.persons;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class Address {

    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String country;
    /*
    @JsonCreator
    public Address(
            final String addressLineOne,
            final String addressLineTwo,
            final String city,
            final String country) {
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.city = city;
        this.country = country;
    }
    */
}
