package br.com.maurigvs.banking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maurigvs.banking.model.Account;
import br.com.maurigvs.banking.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    
    private final AccountService accountService;

    @PostMapping("/open")
    public ResponseEntity<PostResponse> postAccount(@RequestBody PostRequest request){
        Account account = accountService.openAccount(
            request.getTaxId(), 
            request.getName(), 
            request.getSurname()
        );
        PostResponse response = new PostResponse(account.getKeyCode().toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @Getter
    @Setter
    static class PostRequest {

        private String taxId;
        private String name;
        private String surname;
    }

    @AllArgsConstructor
    @Getter
    static class PostResponse {

        private String keyCode;
    }
}