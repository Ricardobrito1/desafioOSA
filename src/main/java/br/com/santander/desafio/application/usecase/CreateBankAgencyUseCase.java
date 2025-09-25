package br.com.santander.desafio.application.usecase;


import br.com.santander.desafio.domain.model.Agency;
import br.com.santander.desafio.domain.model.Point;
import br.com.santander.desafio.domain.port.AgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateBankAgencyUseCase {
    private final AgencyRepository rep;
    public void exec(String id, int x, int y){
        if (rep.exists(id))throw new IllegalArgumentException("Agência already created");
        rep.save(new Agency(id, new Point(x, y)));
    }
}
