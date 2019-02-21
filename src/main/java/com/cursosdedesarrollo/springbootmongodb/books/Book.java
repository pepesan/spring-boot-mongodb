package com.cursosdedesarrollo.springbootmongodb.books;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "books")
public class Book {

    @Id
    private String id;
    private String title;
    private String isbn;
    private List<String> authorNames;
    private Date publishDate;
    private List<String> subjects;

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authorNames=" + authorNames +
                ", publishDate=" + publishDate +
                ", subjects=" + subjects +
                '}';
    }
}
