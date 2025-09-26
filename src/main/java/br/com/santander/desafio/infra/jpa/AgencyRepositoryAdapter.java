package br.com.santander.desafio.infra.jpa;


import br.com.santander.desafio.application.mappers.AgencyMapper;
import br.com.santander.desafio.domain.model.Agency;
import br.com.santander.desafio.domain.model.Point;
import br.com.santander.desafio.domain.port.AgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AgencyRepositoryAdapter implements AgencyRepository {
    private final AgencyJpaRepository agencyJpaRepository;

    @Override
    public Agency save(Agency agency) {
        AgencyEntity agencyEntity = agencyJpaRepository.save(AgencyMapper.toEntity(agency));
        return AgencyMapper.fromEntity(agencyEntity);
    }

    @Override
    public List<Agency> listAllAgencies() {
        return agencyJpaRepository.findAll().stream()
                .map(AgencyMapper::fromEntity)
                .toList();
    }

    @Override
    public Optional<Agency> findById(UUID id) {
        return agencyJpaRepository.findById(id).map(AgencyMapper::fromEntity);
    }


}

