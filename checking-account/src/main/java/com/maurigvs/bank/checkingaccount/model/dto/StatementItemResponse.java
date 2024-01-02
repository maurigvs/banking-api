package com.maurigvs.bank.checkingaccount.model.dto;

public record StatementItemResponse (
        String dateTime,
        String description,
        Double amount) {
}