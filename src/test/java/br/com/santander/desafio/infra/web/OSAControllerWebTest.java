package br.com.santander.desafio.infra.web;

import br.com.santander.desafio.application.usecase.CreateBankAgencyUseCase;
import br.com.santander.desafio.application.usecase.CalculateDistanceUseCase;
import br.com.santander.desafio.infra.security.SecurityConfig;
import br.com.santander.desafio.infra.web.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SecurityConfig.class)
@WebMvcTest(controllers = OSAController.class)
class OSAControllerWebTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper om;

    @MockBean
    CreateBankAgencyUseCase cadastrar;
    @MockBean
    CalculateDistanceUseCase calcular;

    @Test
    void getDistanciaSemAuth_deveRetornar200() throws Exception {
        when(calcular.result(anyInt(), anyInt())).thenReturn(List.of()); // ou uma lista com 1 item

        mvc.perform(get("/desafio/distance")
                        .param("x", "-5")
                        .param("y", "-2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));}


        @Test
        void postCadastrar_semAuth_deveRetornar401 () throws Exception {
            var body = om.writeValueAsString(new AgencyRequest("1", -2, 2));
            mvc.perform(post("/desafio/create").contentType(MediaType.APPLICATION_JSON).content(body))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void postCadastrar_comAuth_deveRetornar201 () throws Exception {
            var body = om.writeValueAsString(new AgencyRequest("1", -2, 2));
            mvc.perform(post("/desafio/create")
                            .with(httpBasic("user", "senha123"))
                            .contentType(MediaType.APPLICATION_JSON).content(body))
                    .andExpect(status().isCreated());
        }



}