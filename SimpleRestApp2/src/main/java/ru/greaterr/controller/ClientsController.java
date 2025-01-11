package ru.greaterr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.greaterr.dto.Book;

@RestController()
public class ClientsController {
    public final RestTemplate restTemplate;

    public ClientsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/clients")
    public Book getClients() {
        return restTemplate.getForObject("http://localhost:8080/books", Book.class);
    }
}
