package com.example.idvlabsTask.authentication.controller.request;

import com.example.idvlabsTask.authentication.entity.User;
public record UpdateUserRequest(
        String username,
        String password
) {
        public User toDomainEntity() {
            return new User(username, password);
        }
}
