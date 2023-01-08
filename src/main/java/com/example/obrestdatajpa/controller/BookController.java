package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    BookRepository bookRepository;
    // Logger para almacenar errores en los log
    private final Logger log = LoggerFactory.getLogger(BookController.class);

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping("/api/books")
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @GetMapping("/hello")
    public String hello(){
        return "hola";
    }
    @GetMapping("/api/book/{id}")

   /* Opcion 1 no deseable
    public Book findById(@PathVariable Long id){

        Optional<Book>  bookOpt = bookRepository.findById(id);
        //Opcion 1
       // return bookOpt.orElse(null);

            } */

    //Opcion 2

    public ResponseEntity<Book> findById(@PathVariable Long id){

        Optional<Book>  bookOpt = bookRepository.findById(id);
        /*
        if (bookOpt.isPresent())
           return ResponseEntity.ok(bookOpt.get());
       else
           return ResponseEntity.notFound().build();
         */
// Funcional
        return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

/*
    @PostMapping("/api/books")
    public Book create(@RequestBody Book book){

        return bookRepository.save(book);

    } */

    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        if (book.getId() != null) {
            log.warn("Se ha intentado crear una libro ya existente");
           return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bookRepository.save(book));
    }


    @PutMapping("/api/update_books")
    public ResponseEntity<Book> updtaeBook(@RequestBody Book book){

        if (!bookRepository.existsById(book.getId())){
      ResponseEntity.notFound().build();
      log.warn(ResponseEntity.notFound().toString());
      return ResponseEntity.notFound().build();

        }
        return  ResponseEntity.ok(bookRepository.save(book));


    }
    //Delete uno
    @DeleteMapping("api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){

        if(!bookRepository.existsById(id)){
            log.warn("Esta intentando borrar un libro que no existe");
            return ResponseEntity.notFound().build();
        }

        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }
    @DeleteMapping("api/books")
    public ResponseEntity<Book> deleteAll(){

     bookRepository.deleteAll();
     return ResponseEntity.noContent().build();

    }


}
