package br.com.santander.desafio.infra.web;


import br.com.santander.desafio.application.mappers.AgencyMapper;
import br.com.santander.desafio.application.usecase.CalculateDistanceUseCase;
import br.com.santander.desafio.application.usecase.CreateBankAgencyUseCase;
import br.com.santander.desafio.domain.model.Agency;
import br.com.santander.desafio.infra.web.dto.AgencyRequest;
import br.com.santander.desafio.infra.web.dto.AgencyResponse;
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
    public AgencyResponse create (
            @Valid
            @RequestBody
            AgencyRequest agencyRequest
    ){
        Agency agency = createUseCase.execute(AgencyMapper.fromRequest(agencyRequest));
        return AgencyMapper.toResponse(agency);
    }


    @GetMapping("/distance")
    public List<AgencyResponse>distanceList(
            @RequestParam Long x,
            @RequestParam Long y
    ){
        return distanceUseCase.execute(x,y)
                .stream()
                .map(AgencyMapper :: toResponse)
                .toList();

    }
}
