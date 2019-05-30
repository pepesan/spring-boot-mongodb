package com.cursosdedesarrollo.springbootmongodb.persons;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Data
public class Hobby {

    private String name;

    @JsonCreator
    public Hobby(final String name) {
        this.name = name;
    }
}
