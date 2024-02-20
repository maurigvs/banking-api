package com.maurigvs.bank.accountapi.service;

import com.maurigvs.bank.accountapi.model.Commercial;
import com.maurigvs.bank.accountapi.repository.CommercialRepository;
import org.springframework.stereotype.Service;

@Service
class CommercialServiceImpl implements CommercialService {

    private final CommercialRepository repository;

    public CommercialServiceImpl(CommercialRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Commercial commercial) {
        repository.save(commercial);
    }
}
