package br.com.maurigvs.customer.controller;

import br.com.maurigvs.customer.dto.CompanyRequest;
import br.com.maurigvs.customer.mapper.CompanyMapper;
import br.com.maurigvs.customer.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postCompany(@RequestBody @Valid CompanyRequest request){
        var company = new CompanyMapper().apply(request);
        service.create(company);
    }
}
