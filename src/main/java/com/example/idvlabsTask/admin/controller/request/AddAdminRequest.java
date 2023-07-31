package com.example.idvlabsTask.admin.controller.request;


import com.example.idvlabsTask.admin.entity.Admin;

public record AddAdminRequest(
        String name,
        String surname,
        String email,
        String password
) {

    public Admin toDomainEntity() {
        return new Admin(name, surname, email,password);

    }


}