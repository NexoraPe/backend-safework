package com.nexorape.safework.service.IAM.infrastructure.persistence.jpa.repositories;

import com.nexorape.safework.service.IAM.domain.model.aggregates.User;
import com.nexorape.safework.service.IAM.domain.model.valueobjects.user.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * This method is responsible for finding the user by username.
     * @param emailAddress The email.
     * @return The user object.
     */
    Optional<User> findByEmail(EmailAddress emailAddress);

    /**
     * This method is responsible for checking if the user exists by username.
     * @param emailAddress The email.
     * @return True if the user exists, false otherwise.
     */
    boolean existsByEmail(EmailAddress emailAddress);
}
