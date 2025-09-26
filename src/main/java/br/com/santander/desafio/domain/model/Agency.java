package br.com.santander.desafio.domain.model;

import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Agency{

    private UUID id;
    private String name;
    private Point pos;

    private Double distance;

}
