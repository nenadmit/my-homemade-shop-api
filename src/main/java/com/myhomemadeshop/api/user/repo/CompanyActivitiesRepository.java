package com.myhomemadeshop.api.user.repo;

import com.myhomemadeshop.api.company.domain.entity.CompanyActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CompanyActivitiesRepository extends JpaRepository<CompanyActivity,Long> {

    Optional<CompanyActivity> findByName(String name);
}
