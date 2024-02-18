package br.com.maurigvs.customer.controller;

import br.com.maurigvs.customer.dto.PersonRequest;
import br.com.maurigvs.customer.mapper.PersonMapper;
import br.com.maurigvs.customer.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody @Valid PersonRequest request){
        var person = new PersonMapper().apply(request);
        service.create(person);
    }
}
