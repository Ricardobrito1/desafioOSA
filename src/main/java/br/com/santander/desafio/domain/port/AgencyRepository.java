package br.com.santander.desafio.domain.port;

import br.com.santander.desafio.domain.model.Agency;
import br.com.santander.desafio.infra.jpa.AgencyEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgencyRepository {

    Agency save(Agency agency);
    List<Agency> listAllAgencies();

    Optional<Agency> findById(UUID id);

}
