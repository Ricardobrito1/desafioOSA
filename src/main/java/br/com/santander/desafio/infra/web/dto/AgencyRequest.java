package br.com.santander.desafio.infra.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AgencyRequest(@NotBlank String id, @NotNull Integer posX, @NotNull Integer posY) {
}
