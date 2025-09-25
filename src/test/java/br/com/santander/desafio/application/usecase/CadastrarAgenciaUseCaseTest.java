package br.com.santander.desafio.application.usecase;


import br.com.santander.desafio.domain.port.AgencyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class CadastrarAgenciaUseCaseTest {

    private final AgencyRepository repo = Mockito.mock(AgencyRepository.class);
    private final CreateBankAgencyUseCase useCase = new CreateBankAgencyUseCase(repo);

    @Test
    void deveCadastrarQuandoNaoExiste() {
        when(repo.exists("1")).thenReturn(false);

        useCase.exec("1", -2, 2);

        verify(repo, times(1)).save(any());
    }

    @Test
    void deveFalharQuandoIdDuplicado() {
        when(repo.exists("1")).thenReturn(true);

        var ex = assertThrows(IllegalArgumentException.class,
                () -> useCase.exec("1", -2, 2));

        assertEquals("Agência already created", ex.getMessage());
        verify(repo, never()).save(any());
    }
}
