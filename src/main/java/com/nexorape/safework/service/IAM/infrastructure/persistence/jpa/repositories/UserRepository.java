package com.nexorape.safework.service.IAM.infrastructure.persistence.jpa.repositories;

import com.nexorape.safework.service.IAM.domain.model.aggregates.User;
import com.nexorape.safework.service.shared.domain.model.valueobjects.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUUID(UserId  userId);
}
