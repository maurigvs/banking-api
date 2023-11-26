package br.com.maurigvs.banking.model.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.maurigvs.banking.model.entity.Transaction;
import lombok.Getter;

@Getter
public class TransactionResponse {

    private final String description;

    private final Double amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private final ZonedDateTime dateTime;

    public TransactionResponse(Transaction t) {
        this.description = t.getDescription();
        this.amount = t.getAmount();
        this.dateTime = t.getDateTime();
    }
}