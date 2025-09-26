package br.com.santander.desafio.application.usecase;


import br.com.santander.desafio.domain.model.Agency;
import br.com.santander.desafio.domain.port.in.CreateBankAgencyUseCase;
import br.com.santander.desafio.infra.jpa.AgencyRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateBankAgencyUseCaseImpl implements CreateBankAgencyUseCase {
    private final AgencyRepositoryAdapter agencyRepositoryAdapter;
    public Agency execute(Agency agency){
       return agencyRepositoryAdapter.save(agency);
    }
}
