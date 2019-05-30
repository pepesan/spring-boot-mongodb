package com.cursosdedesarrollo.springbootmongodb;

import com.cursosdedesarrollo.springbootmongodb.books.Book;
import com.cursosdedesarrollo.springbootmongodb.books.BookRepository;
import com.cursosdedesarrollo.springbootmongodb.customer.Customer;
import com.cursosdedesarrollo.springbootmongodb.customer.CustomerRepository;
import com.cursosdedesarrollo.springbootmongodb.persons.Address;
import com.cursosdedesarrollo.springbootmongodb.persons.Hobby;
import com.cursosdedesarrollo.springbootmongodb.persons.Person;
import com.cursosdedesarrollo.springbootmongodb.persons.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class SpringBootMongodbApplication extends SpringBootServletInitializer {

    @Autowired
    private BookRepository bookRepository;
    @Autowired private PersonRepository personRepository;



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


            personRepository.deleteAll();
            /*
            final Address address =
                    new Address(
                            "19 Imaginary Road",
                            "Imaginary Place",
                            "Imaginary City",
                            "UK");
             */
            Address address1=new Address();

            address1.setCountry("UK");
            address1.setAddressLineOne("19 Imaginary Road");
            address1.setAddressLineTwo("Imaginary Place");
            address1.setCity("Imaginary City");

            final Hobby badminton = new Hobby("Badminton");
            final Hobby tv = new Hobby("TV");
            final List<Hobby> hobbies = Arrays.asList(badminton, tv);

            final Person john = new Person(
                    "John",
                    "Doe",
                    LocalDateTime.now(),
                    address1,
                    "Winner",
                    100,
                    hobbies);
            personRepository.save(john);

            System.out.println("Find by first name");
            personRepository.findByFirstName("John").forEach(System.out::println);

            System.out.println("Find by country (UK)");
            personRepository.findByCountry("UK").forEach(System.out::println);

            address1.setCountry("US");
            personRepository.save(john);
            System.out.println("Find by country (US)");
            personRepository.findByCountry("US").forEach(System.out::println);

            System.out.println("Find by hobby (TV)");
            personRepository.findByHobby("TV").forEach(System.out::println);
        };
    }

}
