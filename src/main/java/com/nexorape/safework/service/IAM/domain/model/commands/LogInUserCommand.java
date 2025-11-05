package com.nexorape.safework.service.IAM.domain.model.commands;

public record LogInUserCommand(String email, String password) {
    public LogInUserCommand{
        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("Email is empty");
        }

        if(password == null || password.isBlank()){
            throw new IllegalArgumentException("Password is empty");
        }
    }
}
