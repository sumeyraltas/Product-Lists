package com.example.idvlabsTask.authentication.controller.responses;
import com.example.idvlabsTask.authentication.entity.User;

public record UserQueryModel(
        Long id,
        String username
) {
    public UserQueryModel(User user) {
        this(
                user.getId(),
                user.getUsername()
        );
    }
}

