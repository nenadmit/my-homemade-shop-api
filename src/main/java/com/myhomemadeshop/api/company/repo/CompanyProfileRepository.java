package com.myhomemadeshop.api.company.repo;

import com.myhomemadeshop.api.company.domain.entity.CompanyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyProfileRepository extends JpaRepository<CompanyProfile,Long> {

    Optional<CompanyProfile> findCompanyProfileByCompanyId(Long companyId);
}
