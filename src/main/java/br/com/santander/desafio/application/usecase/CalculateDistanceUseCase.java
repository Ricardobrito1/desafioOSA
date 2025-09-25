package br.com.santander.desafio.application.usecase;


import br.com.santander.desafio.domain.model.*;
import br.com.santander.desafio.domain.port.AgencyRepository;
import br.com.santander.desafio.domain.service.DistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculateDistanceUseCase {
    private final AgencyRepository rep;
    private final DistanceService service;
    public List<Distance> result(int x, int y){
        var p = new Point(x,y);
        var agencies = rep.listAllAgencys();

        if (agencies.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma agência cadastrada");
        }

        boolean exist = agencies.stream()
                .anyMatch(a -> a.pos().x() == x && a.pos().y() == y);
        if (!exist) {
            throw new IllegalArgumentException(
                    "Nenhuma agência encontrada com as coordenadas (" + x + "," + y + "), tente novamente"
            );
        }

        return agencies.stream()
                .map(d -> new Distance(d.id(), service.calculate(p, d.pos())))
                .sorted()
                .toList();
    }


}

