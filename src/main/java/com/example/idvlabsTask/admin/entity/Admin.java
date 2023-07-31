package com.example.idvlabsTask.admin.entity;


import com.example.idvlabsTask.authentication.entity.User;
import com.example.idvlabsTask.common.entity.BaseEntity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin extends BaseEntity {

    private  String name;
    private  String surname;
    @Email
    private String email;
    private String password;
    
    @OneToOne(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    public Admin(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public Admin(String email) {
        this.email = email;
    }

    public void update(Admin updateAdmin) {
        this.email = updateAdmin.email;
    }
}