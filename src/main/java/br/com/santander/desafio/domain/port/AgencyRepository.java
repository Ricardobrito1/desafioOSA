package br.com.santander.desafio.domain.port;

import br.com.santander.desafio.domain.model.Agency;

import java.util.List;

public interface AgencyRepository {

    void save(Agency agency);
    List<Agency>listAllAgencys();

    boolean exists(String id);
}
