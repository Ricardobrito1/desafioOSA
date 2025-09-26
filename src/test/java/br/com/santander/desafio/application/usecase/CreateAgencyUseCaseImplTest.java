package br.com.santander.desafio.application.usecase;


import br.com.santander.desafio.domain.model.Agency;
import br.com.santander.desafio.domain.model.Point;
import br.com.santander.desafio.infra.jpa.AgencyRepositoryAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateAgencyUseCaseImplTest {
    @Mock AgencyRepositoryAdapter adapter;
    @InjectMocks CreateBankAgencyUseCaseImpl usecase;

    @Test
    void devePersistirEDevolverAgency() {
        var req = Agency.builder().name("X").pos(Point.builder().x(1L).y(2L).build()).build();
        var saved = req.builder().distance(0.0).build();

        when(adapter.save(req)).thenReturn(saved);

        var out = usecase.execute(req);

        assertThat(out).isSameAs(saved);
        verify(adapter).save(req);
    }

}
