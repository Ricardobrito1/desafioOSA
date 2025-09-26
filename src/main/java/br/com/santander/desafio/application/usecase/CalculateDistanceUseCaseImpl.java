package br.com.santander.desafio.application.usecase;


import br.com.santander.desafio.domain.model.Agency;
import br.com.santander.desafio.domain.model.Point;
import br.com.santander.desafio.domain.port.in.CalculateDistanceUseCase;
import br.com.santander.desafio.domain.port.out.AgencyRepository;
import br.com.santander.desafio.domain.util.DistanceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculateDistanceUseCaseImpl implements CalculateDistanceUseCase {
    public final AgencyRepository agencyRepository;

    public List<Agency> execute(Long x, Long y) {
        Point point = Point.builder()
                .y(y)
                .x(x)
                .build();
        List<Agency> agencies = agencyRepository.listAllAgencies();

        if (agencies.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma agência cadastrada");
        }

        return agencies.stream()
                .peek(agency -> agency.setDistance(DistanceUtil.calculate(agency.getPos(), point)))
                .sorted(Comparator.comparingDouble(Agency::getDistance))
                .toList();
    }


}

