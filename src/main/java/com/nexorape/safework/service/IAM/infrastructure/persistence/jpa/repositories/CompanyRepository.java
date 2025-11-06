package com.nexorape.safework.service.IAM.infrastructure.persistence.jpa.repositories;

import com.nexorape.safework.service.IAM.domain.model.aggregates.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByRegistrationCode(String registrationCode);

    boolean existsByRegistrationCode(String registrationCode);
}