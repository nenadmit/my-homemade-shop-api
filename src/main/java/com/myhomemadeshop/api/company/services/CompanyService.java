package com.myhomemadeshop.api.company.services;

import com.myhomemadeshop.api.company.domain.entity.Company;
import com.myhomemadeshop.api.company.domain.entity.CompanyProfile;
import com.myhomemadeshop.api.company.domain.mapper.CompanyMapper;
import com.myhomemadeshop.api.company.domain.mapper.CompanyProfileMapper;
import com.myhomemadeshop.api.company.repo.CompanyProfileRepository;
import com.myhomemadeshop.api.company.repo.CompanyRepository;
import com.myhomemadeshop.api.company.web.dtos.CompanyProfileRequestDto;
import com.myhomemadeshop.api.company.web.dtos.CompanyRequestDto;
import com.myhomemadeshop.api.company.web.dtos.CompanyResponseDto;
import com.myhomemadeshop.api.infrastructure.exception.ConflictException;
import com.myhomemadeshop.api.infrastructure.exception.EntityNotFoundException;
import com.myhomemadeshop.api.user.domain.User;
import com.myhomemadeshop.api.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

  private final CompanyRepository repository;
  private final CompanyProfileRepository profileRepository;
  private final UserRepository userRepository;
  private final CompanyMapper companyMapper;
  private final CompanyProfileMapper profileMapper;

  public CompanyResponseDto persist(CompanyRequestDto companyRequestDto) {

    User user = findUserById(companyRequestDto.getUserId());
    Company company = companyMapper.mapToEntity(companyRequestDto);
    repository.save(company);

    return companyMapper.mapToResponseDto(company);
  }

  public List<CompanyResponseDto> findAll(Pageable pageable) {

    List<Company> elements = repository.findAll(pageable).getContent();

    return companyMapper.mapToResponseDto(elements);
  }

  public CompanyResponseDto findById(Long id) {

    Company company = findEntityById(id);

    return companyMapper.mapToResponseDto(company);
  }

  public Company findEntityById(Long id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(Company.class, id));
  }

  public CompanyProfileRequestDto createCompanyProfile(
      Long companyId, CompanyProfileRequestDto dto) {

    Company company = findEntityById(companyId);
    if(company.getCompanyProfile() != null){
      throw new ConflictException("Company already has an profile, use PUT method to update it.");
    }

    CompanyProfile profile = profileMapper.mapToEntity(dto);
    profile.setCompany(company);

    profileRepository.save(profile);

    return profileMapper.mapToResponseDto(profile);

  }

  public CompanyProfileRequestDto findCompanyProfile(Long companyId) {

    Company company = findEntityById(companyId);

    return profileMapper.mapToResponseDto(company.getCompanyProfile());
  }

  private User findUserById(Long userId) {

    return userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException(User.class,userId));
  }

}
