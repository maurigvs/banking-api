package com.maurigvs.bank.accountapi.service;

import com.maurigvs.bank.accountapi.model.Consumer;
import com.maurigvs.bank.accountapi.repository.ConsumerRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository repository;

    public ConsumerServiceImpl(ConsumerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Consumer consumer) {
        repository.save(consumer);
    }
}
