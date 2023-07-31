package com.example.idvlabsTask.authentication.configuration;

import com.example.idvlabsTask.admin.entity.Admin;
import com.example.idvlabsTask.admin.repository.AdminRepository;
import com.example.idvlabsTask.authentication.entity.Authority;
import com.example.idvlabsTask.authentication.entity.User;
import com.example.idvlabsTask.authentication.repository.UserRepository;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserTablePopulator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;

    @PostConstruct
    public void populateDatabase() {
        if (!userRepository.existsByUsername("user")) {
            User user = new User("user", passwordEncoder.encode("user") );
            user.getAuthorities().add(new Authority("USER"));
            userRepository.save(user);
        }
        if (!userRepository.existsByUsername("admin")) {
            Admin admin = new Admin();
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setName("admin");
            admin.setSurname("admin");
            admin.setEmail("admin");
            User user = new User("admin", passwordEncoder.encode("admin"));
            user.getAuthorities().add(new Authority("ADMIN"));
            user.setAdmin(admin);
            adminRepository.save(admin);
            userRepository.save(user);
        }
    }

}