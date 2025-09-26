package br.com.santander.desafio.domain.port.in;

import br.com.santander.desafio.domain.model.Agency;

public interface CreateBankAgencyUseCase {
    Agency execute(Agency agency);
}
