package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
// Las pruebas se jecutan en un puerto aleatorio definido.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {
    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @LocalServerPort
    private int port;
    @BeforeEach
    void setUp(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);

    }


    @Test
    void findAll() {
        ResponseEntity<Book[]> response=testRestTemplate.getForEntity("/api/books", Book[].class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        List<Book> books= Arrays.asList(response.getBody());
        System.out.println(books.size());
    }

    @Test
    void findById() {
    }

    @Test
    void create() {

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json = """
                {
                    "title": "La Edad de Oro",
                    "author": "José Marti",
                    "pages": 150,
                    "price": 30,
                    "releaseDate": "2000-12-01",
                    "online": true
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Book> response = testRestTemplate.exchange("/api/books", HttpMethod.POST,request,Book.class);
        Book result = response.getBody();
        assertEquals("La Edad de Oro", result.getTitle());

    }

    @Test
    void updtaeBook() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }


    @Test
    void Hello(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/hello",String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCode().value());
        assertEquals("hola", response.getBody());

    }
}