package com.example.idvlabsTask.authentication.entity;

import com.example.idvlabsTask.common.entity.BaseEntity;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@NoArgsConstructor
public class Authority extends BaseEntity implements GrantedAuthority {
    private String authority;

    @ManyToMany(mappedBy = "authorities")
    public List<User> users = new ArrayList<>();

    public Authority(String authority) {
        this.authority = authority;
    }
}