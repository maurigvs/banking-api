package br.com.maurigvs.customerapi.controller;

import br.com.maurigvs.customerapi.dto.CompanyRequest;
import br.com.maurigvs.customerapi.model.Company;
import br.com.maurigvs.customerapi.service.CompanyService;
import br.com.maurigvs.customerapi.util.Utils;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
@AutoConfigureWebMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CompanyControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompanyService service;

    private static final String URL_PATH = "/company";

    @Test
    void should_return_Created_when_post_company_successfully() throws Exception {
        var companyRequest = new CompanyRequest(
                "Contoso",
                "Contoso Services Inc.",
                "29382687000159",
                "07/04/2004",
                "finance@contoso.com",
                "+351654358130");

        var jsonRequest = Utils.ofJson(companyRequest);

        mvc.perform(post(URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());

        verify(service, times(1)).create(any(Company.class));
        verifyNoMoreInteractions(service);
    }
}