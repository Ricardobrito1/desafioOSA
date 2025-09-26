package br.com.santander.desafio.application.mappers;

import br.com.santander.desafio.domain.model.Agency;
import br.com.santander.desafio.domain.model.Distance;
import br.com.santander.desafio.domain.model.Point;
import br.com.santander.desafio.domain.util.DistanceUtil;
import br.com.santander.desafio.infra.jpa.AgencyEntity;
import br.com.santander.desafio.infra.web.dto.AgencyRequest;
import br.com.santander.desafio.infra.web.dto.AgencyResponse;

public class AgencyMapper {

    public static AgencyEntity toEntity(Agency agency){
        return AgencyEntity.builder()
                .posX(agency.getPos().x())
                .posY(agency.getPos().y())
                .name(agency.getName())
                .build();
    }

    public static Agency fromEntity(AgencyEntity agencyEntity){
        return Agency.builder()
                .id(agencyEntity.getId())
                .pos(Point.builder()
                        .x(agencyEntity.getPosX())
                        .y(agencyEntity.getPosY())
                        .build())
                .name(agencyEntity.getName())
                .build();
    }

    public static Agency fromRequest(AgencyRequest agencyRequest){

        return Agency.builder()
                .pos(Point.builder()
                        .x(agencyRequest.posX())
                        .y(agencyRequest.posY())
                        .build())
                .name(agencyRequest.name())
                .build();

    }

    public static AgencyResponse toResponse(Agency agency){
        return AgencyResponse.builder()
                .agency(agency.getName())
                .distance(DistanceUtil.getRoundedDistance(agency.getDistance()))
                .build();
    }
}
