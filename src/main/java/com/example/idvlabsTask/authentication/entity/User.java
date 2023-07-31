package com.example.idvlabsTask.authentication.entity;

import com.example.idvlabsTask.admin.entity.Admin;
import com.example.idvlabsTask.common.entity.BaseEntity;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;


@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {

    @Column(unique=true)
    private String username;
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = true)
    private Admin admin;
    //Farklı kullanıcılar eklenebilir.

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id")
    )

    private List<Authority> authorities = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public <E> User(String username, String password, List<Authority> authority) {
        super();
        this.username = username;
        this.password = password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void update(User updated) {
        this.username = updated.username;
        this.password = updated.password;
    }
}