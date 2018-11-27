package com.cursosdedesarrollo.springbootmongodb;

import com.cursosdedesarrollo.springbootmongodb.books.Book;
import com.cursosdedesarrollo.springbootmongodb.books.BookRepository;
import com.cursosdedesarrollo.springbootmongodb.customer.Customer;
import com.cursosdedesarrollo.springbootmongodb.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootMongodbApplication {

    @Autowired
    private BookRepository bookRepository;



    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongodbApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            repository.deleteAll();

            // save a couple of customers
            repository.save(new Customer("Alice", "Smith"));
            repository.save(new Customer("Bob", "Smith"));

            // fetch all customers
            System.out.println("Customers found with findAll():");
            System.out.println("-------------------------------");
            for (Customer customer : repository.findAll()) {
                System.out.println(customer);
            }
            System.out.println();
            // fetch an individual customer
            System.out.println("Customer found with findByFirstName('Alice'):");
            System.out.println("--------------------------------");
            System.out.println(repository.findByFirstName("Alice"));

            System.out.println("Customers found with findByLastName('Smith'):");
            System.out.println("--------------------------------");
            for (Customer customer : repository.findByLastName("Smith")) {
                System.out.println(customer);
            }

            Book b= new Book();
            String autor1="Terry Prattchet";
            String autor2="Neil Gayman";
            List<String> autores=new ArrayList<>();
            autores.add(autor1);
            autores.add(autor2);
            b.setAuthorNames(autores);
            b.setTitle("Buenos Presagios");
            bookRepository.save(b);

            // fetch all customers
            System.out.println("Books found with findAll():");
            System.out.println("-------------------------------");
            for (Book book : bookRepository.findAll()) {
                System.out.println(book);
            }
            System.out.println();
            // fetch an individual book
            System.out.println("Book found with findByTitleContainingOrderByTitle('buenos'):");
            System.out.println("--------------------------------");
            System.out.println(bookRepository.findByTitleContainingOrderByTitle("Buenos"));

            System.out.println("Book found with findByTitle('Buenos'):");
            System.out.println("--------------------------------");
            for (Book book : bookRepository.findByTitleContaining("Buenos")) {
                System.out.println(book);
            }

        };
    }

}
