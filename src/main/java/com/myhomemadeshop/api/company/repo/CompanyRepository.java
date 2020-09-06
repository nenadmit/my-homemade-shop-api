package com.myhomemadeshop.api.company.repo;

import com.myhomemadeshop.api.company.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {

    Optional<Company> findByName(String name);
    List<Company> findAllByNameLike(String name);
}
