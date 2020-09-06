package com.myhomemadeshop.api.company.web.controllers;

import com.myhomemadeshop.api.company.services.CompanyService;
import com.myhomemadeshop.api.company.web.dtos.CompanyProfileRequestDto;
import com.myhomemadeshop.api.company.web.dtos.CompanyRequestDto;
import com.myhomemadeshop.api.company.web.dtos.CompanyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    @ResponseStatus(OK)
    public List<CompanyResponseDto> findAll(Pageable pageable){
        return companyService.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public CompanyResponseDto presistCompany(@Valid @RequestBody CompanyRequestDto companyRequestDto){
        return companyService.persist(companyRequestDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public CompanyResponseDto findByName(@PathVariable Long id){
        return companyService.findById(id);
    }

    @GetMapping("/{companyId}/profile")
    @ResponseStatus(OK)
    public CompanyProfileRequestDto findCompanyProfile(@PathVariable Long companyId){
        return companyService.findCompanyProfile(companyId);
    }

    @PostMapping("/{companyId}/profile")
    @ResponseStatus(CREATED)
    public CompanyProfileRequestDto createCompanyProfile(@PathVariable Long companyId, @Valid @RequestBody CompanyProfileRequestDto dto){
        return companyService.createCompanyProfile(companyId,dto);
    }
}
