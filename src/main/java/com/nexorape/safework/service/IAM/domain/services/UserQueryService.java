package com.nexorape.safework.service.IAM.domain.services;

import com.nexorape.safework.service.IAM.domain.model.aggregates.User;
import com.nexorape.safework.service.IAM.domain.model.queries.GetAllUsersQuery;
import com.nexorape.safework.service.IAM.domain.model.queries.GetUserByEmailQuery;
import com.nexorape.safework.service.IAM.domain.model.queries.GetUserByUUIDQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {

    Optional<User> handle(GetUserByEmailQuery query);

    List<User> handle(GetAllUsersQuery query);

    Optional<User> handle(GetUserByUUIDQuery query);
}
