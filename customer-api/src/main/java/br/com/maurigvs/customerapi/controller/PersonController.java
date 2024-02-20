package br.com.maurigvs.customerapi.controller;

import br.com.maurigvs.customerapi.dto.PersonRequest;
import br.com.maurigvs.customerapi.mapper.PersonMapper;
import br.com.maurigvs.customerapi.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
