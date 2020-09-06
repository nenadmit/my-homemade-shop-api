package com.myhomemadeshop.api.company.domain.mapper;

import com.myhomemadeshop.api.company.domain.entity.CompanyProfile;
import com.myhomemadeshop.api.company.web.dtos.CompanyProfileRequestDto;
import com.myhomemadeshop.api.infrastructure.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface CompanyProfileMapper
    extends EntityMapper<CompanyProfile, CompanyProfileRequestDto, CompanyProfileRequestDto> {}
