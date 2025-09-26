package br.com.santander.desafio.domain.model;

import lombok.Builder;

@Builder
public record Point(Long x, Long y) {
}
