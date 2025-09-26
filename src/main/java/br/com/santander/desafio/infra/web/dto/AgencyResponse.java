package br.com.santander.desafio.infra.web.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

@Builder
public record AgencyResponse(

        String agency,

        String distance
) {
}
