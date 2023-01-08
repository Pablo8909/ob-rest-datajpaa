package com.example.obrestdatajpa;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {

		ApplicationContext context =SpringApplication.run(ObRestDatajpaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);

		System.out.println("Numero de libros " + repository.count());
		repository.save(new Book(null,"Homo Deus","Yuval Noha",450,29.99, LocalDate.of(2018,12,1),true));
		System.out.println("Numero de libros " + repository.count());
		System.out.println(repository.findAll().toString());
	}

}
