package br.com.santander.desafio.infra.web;
import br.com.santander.desafio.domain.model.Agency;
import br.com.santander.desafio.domain.model.Point;
import br.com.santander.desafio.domain.port.in.CalculateDistanceUseCase;
import br.com.santander.desafio.domain.port.in.CreateBankAgencyUseCase;
import br.com.santander.desafio.infra.web.dto.AgencyRequest;
import br.com.santander.desafio.infra.web.dto.AgencyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.context.annotation.Import; // se quiser importar SecurityConfig
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import br.com.santander.desafio.infra.security.SecurityConfig;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;

@Import(SecurityConfig.class)
@WebMvcTest(controllers = OSAController.class)
class OSAControllerWebTest {

    @Autowired ObjectMapper om;
    @Autowired org.springframework.test.web.servlet.MockMvc mvc;

    @MockBean CreateBankAgencyUseCase createUseCase;
    @MockBean CalculateDistanceUseCase distanceUseCase;

    // GET público
    @Test
    void getDistance_deveRetornar200_eJson() throws Exception {
        var a = Agency.builder().name("A").pos(Point.builder().x(0L).y(1L).build()).distance(1.0).build();
        var b = Agency.builder().name("B").pos(Point.builder().x(3L).y(4L).build()).distance(5.0).build();
        when(distanceUseCase.execute(anyLong(), anyLong())).thenReturn(List.of(a, b));

        mvc.perform(get("/desafio/distance")
                        .param("x","0").param("y","0")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    // POST protegido
    @Test
    void postCreate_deveRetornar201() throws Exception {

        var req = new AgencyRequest("Agência 01", 1L, 2L);


        when(createUseCase.execute(any(Agency.class))).thenReturn(
                Agency.builder()
                        .name("Agência 01")
                        .pos(Point.builder().x(1L).y(2L).build())
                        .distance(0.0)
                        .build()
        );

        mvc.perform(post("/desafio/create")
                        .with(httpBasic("user","senha123"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.agency").value("Agência 01"))
                .andExpect(jsonPath("$.distance").value("0,00"));

        verify(createUseCase).execute(any(Agency.class));
    }


}