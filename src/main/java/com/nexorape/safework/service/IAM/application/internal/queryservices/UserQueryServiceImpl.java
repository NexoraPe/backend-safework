//package com.nexorape.safework.service.IAM.application.internal.queryservices;
//
//
//import com.nexorape.safework.service.IAM.domain.model.aggregates.User;
//import com.nexorape.safework.service.IAM.domain.model.queries.GetAllUsersQuery;
//import com.nexorape.safework.service.IAM.domain.model.queries.GetUserByEmailQuery;
//import com.nexorape.safework.service.IAM.domain.model.queries.GetUserByUUIDQuery;
//import com.nexorape.safework.service.IAM.domain.services.UserQueryService;
//import com.nexorape.safework.service.IAM.infrastructure.persistence.jpa.repositories.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserQueryServiceImpl implements UserQueryService {
//    private final UserRepository userRepository;
//
//    public UserQueryServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public Optional<User> handle(GetUserByEmailQuery query) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<User> handle(GetAllUsersQuery query) {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public Optional<User> handle(GetUserByUUIDQuery query){
//
//        return Optional.empty();
//    }
//
//
//}
