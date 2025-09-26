package br.com.santander.desafio.infra.web;


import br.com.santander.desafio.application.mappers.AgencyMapper;
import br.com.santander.desafio.domain.model.Agency;
import br.com.santander.desafio.domain.port.in.CalculateDistanceUseCase;
import br.com.santander.desafio.domain.port.in.CreateBankAgencyUseCase;
import br.com.santander.desafio.infra.web.dto.AgencyRequest;
import br.com.santander.desafio.infra.web.dto.AgencyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
