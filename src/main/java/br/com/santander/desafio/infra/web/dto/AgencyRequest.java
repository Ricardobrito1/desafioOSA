package br.com.santander.desafio.infra.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AgencyRequest(
        @NotBlank
        String name,
        @NotNull
        Long posX,
        @NotNull
        Long posY
)
{
}
