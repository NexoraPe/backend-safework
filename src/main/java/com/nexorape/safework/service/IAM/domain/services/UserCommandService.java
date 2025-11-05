package com.nexorape.safework.service.IAM.domain.services;

import com.nexorape.safework.service.IAM.domain.model.aggregates.User;
import com.nexorape.safework.service.IAM.domain.model.commands.*;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(CreateUserCommand command);

    Optional<User> handle(LogInUserCommand command);

    Optional<User> handle(UpdateUserPersonalInfoCommand command);

    Optional<User> handle(UpdateUserProfilePictureCommand command);

    Optional<User> handle(UpdateUserPasswordCommand command);
}
