package com.example.idvlabsTask.admin.controller.response;

import com.example.idvlabsTask.admin.entity.Admin;

public record AdminResponse(
        Long id,
        String name,
        String surname,
        String email
) {
    public AdminResponse(Admin admin) {
        this(
                admin.getId(),
                admin.getName(),
                admin.getSurname(),
                admin.getEmail()
        );
    }
}