package com.nexorape.safework.service.IAM.domain.services.user;

import com.nexorape.safework.service.IAM.domain.model.aggregates.User;
import com.nexorape.safework.service.IAM.domain.model.commands.user.SignInCommand;
import com.nexorape.safework.service.IAM.domain.model.commands.user.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    /**
     * Handle sign in command
     * @param command the {@link SignInCommand} command
     * @return an {@link Optional} of {@link ImmutablePair} of {@link User} and {@link String}
     */
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);

    /**
     * Handle sign up command
     * @param command the {@link SignUpCommand} command
     * @return an {@link Optional} of {@link User} entity
     */
    Optional<User> handle(SignUpCommand command);
}
