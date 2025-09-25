package br.com.santander.desafio.infra.jpa;


import br.com.santander.desafio.domain.model.Agency;
import br.com.santander.desafio.domain.model.Point;
import br.com.santander.desafio.domain.port.AgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AgencyRepositoryAdapter implements AgencyRepository {
    private final AgencyJpaRepository agencyJpaRepository;

    @Override public void save(Agency agency){
        var ae = new AgencyEntity();
        ae.setId(agency.id());
        ae.setPosY(agency.pos().y());
        ae.setPosX(agency.pos().x());
        agencyJpaRepository.save(ae);

    }

    @Override public List<Agency> listAllAgencys(){
        return agencyJpaRepository.findAll().stream()
                .map(a -> new Agency(a.getId(), new Point(a.getPosX(), a.getPosY()))).collect(Collectors.toList());
    }


    @Override public boolean exists(String id){ return agencyJpaRepository.existsById(id); }


}

