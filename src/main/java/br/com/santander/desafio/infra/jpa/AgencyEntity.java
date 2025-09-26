package br.com.santander.desafio.infra.jpa;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


@Entity @Table(name="agency")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AgencyEntity {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(name = "name", unique = true)
    String name;
    @Column(name="position_x")
    private Long posX;
    @Column(name="position_y")
    private Long posY;
}
