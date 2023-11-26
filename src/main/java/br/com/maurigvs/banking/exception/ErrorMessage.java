package br.com.maurigvs.banking.exception;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorMessage {
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private final ZonedDateTime timestamp = ZonedDateTime.now();
    
    private Integer status;
    
    private String error;
    
    private String message;
}