//package com.nexorape.safework.service.IAM.application.internal.commandservices;
//
//import com.nexorape.safework.service.IAM.domain.model.aggregates.User;
//import com.nexorape.safework.service.IAM.domain.model.commands.*;
//import com.nexorape.safework.service.IAM.domain.services.UserCommandService;
//import com.nexorape.safework.service.IAM.infrastructure.persistence.jpa.repositories.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserCommandServiceImpl implements UserCommandService {
//    private final UserRepository userRepository;
//
//
//    public UserCommandServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public Optional<User> handle(CreateUserCommand command){
//
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<User> handle(LogInUserCommand command){
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<User> handle(UpdateUserPersonalInfoCommand command){
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<User> handle(UpdateUserProfilePictureCommand command){
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<User> handle(UpdateUserPasswordCommand command){
//        return Optional.empty();
//    }
//}
//