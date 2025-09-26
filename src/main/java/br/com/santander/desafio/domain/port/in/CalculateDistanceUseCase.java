package br.com.santander.desafio.domain.port.in;

import br.com.santander.desafio.domain.model.Agency;

import java.util.List;

public interface CalculateDistanceUseCase {
     List<Agency> execute(Long x, Long y);
}
