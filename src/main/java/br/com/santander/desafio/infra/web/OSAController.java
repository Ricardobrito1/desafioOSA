package br.com.santander.desafio.infra.web;


import br.com.santander.desafio.application.usecase.CalculateDistanceUseCase;
import br.com.santander.desafio.application.usecase.CreateBankAgencyUseCase;
import br.com.santander.desafio.infra.web.dto.AgencyRequest;
import br.com.santander.desafio.infra.web.dto.DistanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


@RestController @RequestMapping("/desafio") @RequiredArgsConstructor
public class OSAController {
    private final CreateBankAgencyUseCase createUseCase;
    private final CalculateDistanceUseCase distanceUseCase;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create (@Valid @RequestBody AgencyRequest agencyRequest){
        createUseCase.exec(agencyRequest.id(), agencyRequest.posX(), agencyRequest.posY());
    }


    @GetMapping("/distance")
    public List<DistanceResponse>distanceList(@RequestParam int x, @RequestParam int y){
        return distanceUseCase.result(x,y).stream().map(r -> new DistanceResponse("Agency_"+r.agencyId(), String.format("%.2f", r.value())))
                .toList();

    }
}
