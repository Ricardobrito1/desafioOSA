package br.com.santander.desafio.application.usecase;


import br.com.santander.desafio.domain.model.Agency;
import br.com.santander.desafio.domain.model.Point;
import br.com.santander.desafio.domain.port.out.AgencyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateDistanceUseCaseImplTest {

    @Mock
    AgencyRepository repo;
    @InjectMocks
    CalculateDistanceUseCaseImpl usecase;

    private Agency agency(String name, long x, long y) {
        return Agency.builder()
                .name(name)
                .pos(Point.builder().x(x).y(y).build())
                .build();
    }

    @Test
    void deveLancarQuandoNaoHaAgencias() {
        when(repo.listAllAgencies()).thenReturn(List.of());

        assertThatThrownBy(() -> usecase.execute(0L, 0L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Nenhuma agência cadastrada");
    }

    @Test
    void deveOrdenarPorDistanciaMenorParaMaior_ePopularDistance() {
        // user point (0,0)
        when(repo.listAllAgencies()).thenReturn(List.of(
                agency("A", 10, 0),  // dist 10
                agency("B",  3, 4),  // dist 5
                agency("C",  0, 1)   // dist 1
        ));

        var out = usecase.execute(0L, 0L);

        assertThat(out).extracting(Agency::getName)
                .containsExactly("C", "B", "A"); // 1, 5, 10

        assertThat(out.get(0).getDistance()).isCloseTo(1.0, within(1e-9));
        assertThat(out.get(1).getDistance()).isCloseTo(5.0, within(1e-9));
        assertThat(out.get(2).getDistance()).isCloseTo(10.0, within(1e-9));

        // opcional: capturar o que saiu do repo e garantir que o setDistance foi chamado
        // (não precisa se seu modelo é simples)
    }

}