package com.example.idvlabsTask.authentication.controller.request;


import com.example.idvlabsTask.authentication.entity.User;
import javax.validation.constraints.NotBlank;


public record LoginRequest(
        @NotBlank
        String username,
        @NotBlank
        String password

) {
        public User toDomainEntity() {
                return new User(username,  password);
        }

}