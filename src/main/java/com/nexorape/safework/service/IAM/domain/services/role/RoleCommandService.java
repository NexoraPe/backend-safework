package com.nexorape.safework.service.IAM.domain.services.role;

import com.nexorape.safework.service.IAM.domain.model.commands.role.SeedRolesCommand;

public interface RoleCommandService {
    /**
     * Handle seed roles command
     * @param command the {@link SeedRolesCommand} command
     *
     */
    void handle(SeedRolesCommand command);
}
