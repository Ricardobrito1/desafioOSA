package br.com.santander.desafio.infra.jpa;

import jakarta.persistence.*;
import lombok.*;


@Entity @Table(name="agency")
@Getter @Setter @NoArgsConstructor
public class AgencyEntity {
    @Id private String id;
    @Column(name="position_x") private int posX;
    @Column(name="position_y") private int posY;
}
