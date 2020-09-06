package com.myhomemadeshop.api.company.domain.mapper;

import com.myhomemadeshop.api.company.domain.entity.Company;
import com.myhomemadeshop.api.company.web.dtos.CompanyRequestDto;
import com.myhomemadeshop.api.company.web.dtos.CompanyResponseDto;
import com.myhomemadeshop.api.infrastructure.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyMapper extends EntityMapper<Company, CompanyRequestDto, CompanyResponseDto> {
}
