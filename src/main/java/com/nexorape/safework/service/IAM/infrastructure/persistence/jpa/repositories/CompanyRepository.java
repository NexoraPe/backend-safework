package com.nexorape.safework.service.IAM.infrastructure.persistence.jpa.repositories;


// CUSTOM IMPORTS
import com.nexorape.safework.service.IAM.domain.model.aggregates.Company;
import com.nexorape.safework.service.IAM.domain.model.valueobjects.company.RegistrationCode;


// DEFAULT
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByRegistrationCode(RegistrationCode registrationCode);
}