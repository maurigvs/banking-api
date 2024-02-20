package br.com.maurigvs.customerapi.controller;

import br.com.maurigvs.customerapi.dto.CompanyRequest;
import br.com.maurigvs.customerapi.mapper.CompanyMapper;
import br.com.maurigvs.customerapi.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
