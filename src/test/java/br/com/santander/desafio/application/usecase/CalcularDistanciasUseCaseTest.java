package br.com.santander.desafio.application.usecase;

import br.com.santander.desafio.domain.model.Agency;
import br.com.santander.desafio.domain.model.Point;
import br.com.santander.desafio.domain.port.AgencyRepository;
import br.com.santander.desafio.domain.service.DistanceService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalcularDistanciasUseCaseTest {

    private final AgencyRepository repo = mock(AgencyRepository.class);
    private final DistanceService distance = new DistanceService();
    private final CalculateDistanceUseCase useCase = new CalculateDistanceUseCase(repo, distance);

    @Test
    void deveFalharQuandoNaoHaAgencias() {
        when(repo.listAllAgencys()).thenReturn(List.of());

        var ex = assertThrows(IllegalArgumentException.class,
                () -> useCase.result(-5, -2));

        assertEquals("Nenhuma agência cadastrada", ex.getMessage());
    }

    @Test
    void deveFalharQuandoCoordenadasNaoEncontradas() {
        when(repo.listAllAgencys()).thenReturn(List.of(
                new Agency("1", new Point(-2, 2)),
                new Agency("2", new Point(10, 4))
        ));

        var ex = assertThrows(IllegalArgumentException.class,
                () -> useCase.result(999, 888)); // coordenadas inexistentes

        assertTrue(ex.getMessage().contains("Nenhuma agência encontrada nas coordenadas"));
    }

    @Test
    void deveCalcularEDeverOrdenarPorDistancia() {
        when(repo.listAllAgencys()).thenReturn(List.of(
                new Agency("1", new Point(-2, 2)),
                new Agency("2", new Point(10, 4)),
                new Agency("3", new Point(10, -7))
        ));

        var lista = useCase.result(-5, -2);

        // ordenado crescente
        assertEquals("1", lista.get(0).agencyId());
        assertTrue(lista.get(0).value() <= lista.get(1).value());
        assertTrue(lista.get(1).value() <= lista.get(2).value());
    }
}