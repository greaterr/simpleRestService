package ru.greaterr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.greaterr.dto.BookDto;

@RestController()
public class ClientsController {
    public final RestTemplate restTemplate;

    public ClientsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/clients")
    public BookDto getClients() {
        return restTemplate.getForObject("http://localhost:8085/books/1", BookDto.class);
    }
}
