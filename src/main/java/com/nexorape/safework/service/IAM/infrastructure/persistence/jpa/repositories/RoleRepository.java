package com.nexorape.safework.service.IAM.infrastructure.persistence.jpa.repositories;

import com.nexorape.safework.service.IAM.domain.model.entities.Role;
import com.nexorape.safework.service.IAM.domain.model.valueobjects.Roles;

import java.util.Optional;

public interface RoleRepository {
    /**
     * This method is responsible for finding the role by name.
     * @param name The role name.
     * @return The role object.
     */
    Optional<Role> findByName(Roles name);

    /**
     * This method is responsible for checking if the role exists by name.
     * @param name The role name.
     * @return True if the role exists, false otherwise.
     */
    boolean existsByName(Roles name);
}
