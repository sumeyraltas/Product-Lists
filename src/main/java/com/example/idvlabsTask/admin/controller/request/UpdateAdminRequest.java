package com.example.idvlabsTask.admin.controller.request;

import com.example.idvlabsTask.admin.entity.Admin;

public record UpdateAdminRequest(
        String email
) {
    public Admin toDomainEntity() {
        return new Admin(email);
    }

}